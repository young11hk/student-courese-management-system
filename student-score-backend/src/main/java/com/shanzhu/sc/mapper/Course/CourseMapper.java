package com.shanzhu.sc.mapper.Course;

import com.github.pagehelper.PageRowBounds;
import com.shanzhu.sc.dto.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CourseMapper {

    void addCourse(Course course);

    void delete(@Param("ids") List<Long> ids);

    void update(Course course);

    List<Course> getCourseList(PageRowBounds rowBounds, @Param("condition") Map<String, Object> condition);

    String checkCodeCount(@Param("condition") Map<String, Object> condition);

    List<Course> getCourseByMap(@Param("condition") Map<String, Object> condition);

    Course getCourseById(@Param("courseId") String courseId);
}
