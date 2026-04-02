package com.shanzhu.sc.service.User;

import com.shanzhu.sc.dto.User;
import com.shanzhu.sc.utils.PagingResult;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;


/**
 * 管理员 业务服务层
 *
 * @author: ShanZhu
 * @date: 2024-01-08
 */
public interface AdminService {

    void add(User user);

    void delete(List<Integer> ids);

    void update(User user);

    PagingResult<User> getAdminList(RowBounds rowBounds, Map<String, Object> condition);
}
