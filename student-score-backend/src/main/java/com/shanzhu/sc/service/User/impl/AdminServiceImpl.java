package com.shanzhu.sc.service.User.impl;

import com.github.pagehelper.PageRowBounds;
import com.shanzhu.sc.dto.User;
import com.shanzhu.sc.mapper.User.AdminMapper;
import com.shanzhu.sc.service.User.AdminService;
import com.shanzhu.sc.utils.PagingResult;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

  @Resource
  private AdminMapper adminMapper;

  @Override
  public void add(User user) {
    int num = adminMapper.checkCodeCount();
    String username = "980502" + Integer.toString(num);
    user.setUsername(username);
    user.setPassword("123456");
    adminMapper.add(user);
  }

  @Override
  public void
  delete(List<Integer> ids) {
    adminMapper.delete(ids);
  }

  @Override
  public void update(User user) {
    adminMapper.update(user);
  }

  @Override
  public PagingResult<User> getAdminList(RowBounds rowBounds, Map<String, Object> condition) {
    PageRowBounds pageRowBounds = new PageRowBounds(rowBounds.getOffset(), rowBounds.getLimit());
    List<User> StudentInfoList = adminMapper.getAdminList(pageRowBounds, condition);
    return new PagingResult<>(StudentInfoList, pageRowBounds.getTotal());
  }
}
