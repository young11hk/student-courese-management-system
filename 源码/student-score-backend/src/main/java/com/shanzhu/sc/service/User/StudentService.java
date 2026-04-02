package com.shanzhu.sc.service.User;

import com.shanzhu.sc.dto.User;
import com.shanzhu.sc.utils.PagingResult;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * 学生账号 业务服务层
 *
 * @author: ShanZhu
 * @date: 2024-01-08
 */
public interface StudentService {

    void addStudent(User user);

    void delete(List<String> ids);

    void update(User user);

    PagingResult<User> getStudentList(RowBounds rowBounds, Map<String, Object> condition);
}
