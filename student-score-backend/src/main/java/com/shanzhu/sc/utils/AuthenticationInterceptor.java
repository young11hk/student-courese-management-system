package com.shanzhu.sc.utils;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.shanzhu.sc.dto.User;
import com.shanzhu.sc.service.Upload.impl.UploadServiceImpl;
import com.shanzhu.sc.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AuthenticationInterceptor implements HandlerInterceptor {
    private final Log logger = LogFactory.getLog(UploadServiceImpl.class);

    @Autowired
    UserService userService;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    // Redis 中 Token 的 Key 前缀
    private static final String TOKEN_PREFIX = "sms:token:";

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        //通过所有OPTION请求
        if (httpServletRequest.getMethod().toUpperCase().equals("OPTIONS")) {
            return true;
        }

        String token = httpServletRequest.getHeader("Authorization");// 从 http 请求头中取出 token
        System.out.println("🔍 抓到的 Token 是: [" + token + "]");
        String refreshToken = httpServletRequest.getHeader("freshToken");// 从 http 请求头中取出 token
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        //2.遍历
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            //通过请求头的名称获取请求头的值
            String value = httpServletRequest.getHeader(name);
            System.out.println(name + "----" + value);
        }
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        // 获取 token 中的 用户信息
        if (token == null) {
            throw new RuntimeException("登录信息过期");
        }

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        String userValue = null;
        try {
            userValue = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("level", (userValue).substring(0, 1));
        map.put("id", (userValue).substring(1));

        // ====== Redis 缓存逻辑 ======
        User user = null;
        String cacheKey = TOKEN_PREFIX + token;

        // 1. 先从 Redis 获取用户
        try {
            user = (User) redisTemplate.opsForValue().get(cacheKey);
        } catch (Exception e) {
            logger.warn("Redis 获取用户失败，使用数据库查询: " + e.getMessage());
        }

        // 2. 如果 Redis 没有，从数据库查询
        if (user == null) {
            user = userService.findUser(map);
            if (user == null) {
                throw new RuntimeException("用户不存在，请重新登录");
            }
            // 3. 写入 Redis 缓存，1小时过期
            try {
                redisTemplate.opsForValue().set(cacheKey, user, 1, TimeUnit.HOURS);
                logger.info("用户信息已缓存到 Redis: " + cacheKey);
            } catch (Exception e) {
                logger.warn("Redis 缓存用户失败: " + e.getMessage());
            }
        } else {
            logger.info("从 Redis 获取用户信息: " + user.getUsername());
        }
        // ====== Redis 缓存结束 ======

        System.out.println("🔍 抓到的 Token 是: [" + token + "]");

        // 添加 null 检查
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Token 为空");
        }

        Date oldTime = JWT.decode(token).getExpiresAt();

        // refreshToken 可能为空，只在不为空时检查
        long oldDiff = oldTime.getTime() - new Date().getTime();
        long refreshDiff = Long.MAX_VALUE;
        if (refreshToken != null && !refreshToken.isEmpty()) {
            Date refreshTime = JWT.decode(refreshToken).getExpiresAt();
            refreshDiff = refreshTime.getTime() - new Date().getTime();
        }

        if (oldDiff <= 0) {
            if (refreshDiff <= 0) {
                logger.error("=== token 已过期, 请重新登录 ===");
                // 删除 Redis 缓存
                try {
                    redisTemplate.delete(cacheKey);
                } catch (Exception e) {
                    logger.warn("Redis 删除Token失败: " + e.getMessage());
                }
                httpServletResponse.sendError(401);
                return false;
            }
        }
        String newToken = userService.getToken(user, 60 * 60 * 1000);
        String newRefToken = userService.getToken(user, 24 * 60 * 60 * 1000);
        // 更新token
        httpServletResponse.setHeader("Authorization", newToken);
        httpServletResponse.setHeader("freshToken", newRefToken);

        //检查有没有需要用户权限的注解
//    if (method.isAnnotationPresent(UserLoginToken.class)) {  // 是否使用@UserLoginToken注解
//      UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
//      if (userLoginToken.required()) {
        // 执行认证
        if (token == null) {
            throw new RuntimeException("=== 无token，请重新登录 ===");
        }
        // 利用用户密码，解密验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            logger.error("=== token验证失败 ===");
            // 删除 Redis 缓存
            try {
                redisTemplate.delete(cacheKey);
            } catch (Exception ex) {
                logger.warn("Redis 删除Token失败: " + ex.getMessage());
            }
            httpServletResponse.sendError(401);
            return false;
//          throw new RuntimeException("401");
        }
//        return true;
//      }
//    }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
