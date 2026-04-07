package com.shanzhu.sc.mapper.CourseInfo;

import com.shanzhu.sc.domain.CourseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper

public interface CourseInfoMapper {

  void addCourseInfo(CourseInfo courseInfo);

  void updateCourseInfo(CourseInfo courseInfo);
  void deleteInfo(@Param("id") String id);

  void deleteCourseStu(@Param("id") String id);

  void deleteCourseTec(@Param("id") String id);

  CourseInfo getInfo(@Param("condition") Map<String, Object> condition);
}
