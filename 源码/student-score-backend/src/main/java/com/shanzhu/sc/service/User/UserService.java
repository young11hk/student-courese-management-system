package com.shanzhu.sc.service.User;

import com.shanzhu.sc.dto.User;

import java.util.List;
import java.util.Map;

/**
 * 用户相关 业务服务层
 *
 * @author: ShanZhu
 * @date: 2024-01-08
 */
public interface UserService {

    User getStudentInfo(Map<String, Object> condition);

    boolean update(Map<String, Object> condition);

    List<Object> getTree();

    String getToken(User user, long time);

    User findUser(Map<String, Object> condition);
}
