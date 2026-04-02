package com.shanzhu.sc.mapper.Score;

import com.github.pagehelper.PageRowBounds;
import com.shanzhu.sc.dto.Course;
import com.shanzhu.sc.dto.Score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ScoreMapper {

    List<Course> getCourseByMap(PageRowBounds rowBounds, @Param("condition") Map<String, Object> condition);

    List<Course> getCourseByAdmin(PageRowBounds rowBounds, @Param("condition") Map<String, Object> condition);

    List<Course> getCourseByStudent(PageRowBounds rowBounds, @Param("condition") Map<String, Object> condition);

    Course getScoreById(@Param("condition") Map<String, Object> condition);

    void addEntry(Score score);

    void updateEntry(Score score);

    Integer checkCount(@Param("condition") Map<String, Object> condition);

    List<Course> getExportList(@Param("condition") Map<String, Object> condition);

    List<Course> getExportListByAdmin(@Param("condition") Map<String, Object> condition);

    List<Course> getExportListByStudent(@Param("condition") Map<String, Object> condition);

    List<Course> getStudentTotal(@Param("condition") Map<String, Object> condition);
}
