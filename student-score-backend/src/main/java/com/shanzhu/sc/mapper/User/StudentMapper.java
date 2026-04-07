package com.shanzhu.sc.mapper.User;

import com.github.pagehelper.PageRowBounds;
import com.shanzhu.sc.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    void addStudent(User user);

    void delete(@Param("ids") List<String> ids);

    void update(User user);

    List<User> getStudentList(PageRowBounds rowBounds, @Param("condition") Map<String, Object> condition);

    List<User> getStudentTree();

    List<String> getGradeByProfession(@Param("code") String code);

    Integer checkCodeCount(@Param("condition") Map<String, Object> condition);

    User getUserById(@Param("id") String id);

    User getStudentById(@Param("id") String id);
}
