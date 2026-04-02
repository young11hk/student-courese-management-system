package com.shanzhu.sc.controller.User;

import com.shanzhu.sc.dto.User;
import com.shanzhu.sc.service.User.UserService;
import com.shanzhu.sc.utils.PassToken;
import com.shanzhu.sc.utils.UserLoginToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 用户相关 控制层
 *
 * @author: ShanZhu
 * @date: 2024-01-08
 */
@RestController
@UserLoginToken
@RequestMapping("/api/sms/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/login")
    @PassToken
    public User getStudentInfo(@RequestParam Map<String, Object> condition) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", condition.get("username").toString());
        map.put("password", condition.get("password").toString());
        map.put("level", condition.get("level"));
        User user = userService.getStudentInfo(map);
        if (user == null) {
            return null;
        }
        String token = userService.getToken(user, 24 * 60 * 60 * 1000);
        String refreshToken = userService.getToken(user, 24 * 60 * 60 * 1000); // 有效期一天
        user.setToken(token);
        user.setRefreshToken(refreshToken);
        return user;
    }

    @GetMapping("/edit/password")
    public boolean update(@RequestParam Map<String, Object> condition) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", condition.get("username").toString());
        map.put("password", condition.get("password").toString());
        map.put("passwordAgain", condition.get("passwordAgain").toString());
        ;
        map.put("level", condition.get("level").toString());
        return userService.update(map);
    }

    @GetMapping("/getTree")
    public List<Object> getTree() {
        return userService.getTree();
    }
}
