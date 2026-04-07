package com.shanzhu.sc.mapper.User;

import com.github.pagehelper.PageRowBounds;
import com.shanzhu.sc.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TeacherMapper {

    void addTeacher(User user);

    void delete(@Param("ids") List<Integer> ids);

    void update(User user);

    List<User> getTeacherList(PageRowBounds rowBounds, @Param("condition") Map<String, Object> condition);

    User getUserById(@Param("id") String id);

    Integer checkCodeCount();
}
