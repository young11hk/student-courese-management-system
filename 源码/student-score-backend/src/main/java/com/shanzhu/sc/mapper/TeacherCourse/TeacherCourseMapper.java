package com.shanzhu.sc.mapper.TeacherCourse;

import com.shanzhu.sc.domain.TeacherCourse;
import com.shanzhu.sc.dto.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TeacherCourseMapper {

    void add(TeacherCourse teacherCourse);

    void delete(@Param("ids") List<Integer> ids);

    void update(TeacherCourse teacherCourse);

    List<TeacherCourse> getCourseListById(@Param("id") String id);

    List<TeacherCourse> getGradeInfoByMap(@Param("condition") Map<String, Object> condition);

    String getTeacherId(@Param("condition") Map<String, Object> condition);

    Course getCourseInfo(@Param("condition") Map<String, Object> condition);
}
