package com.shanzhu.sc.mapper.Timetable;

import com.shanzhu.sc.dto.Timetable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TimetableMapper {

    void add(Timetable timetable);

    List<Timetable> getTimetable(@Param("condition") Map<String, Object> condition);

    Integer checkCount(@Param("condition") Map<String, Object> condition);

    void deleteTimeTable(@Param("condition") Map<String, Object> condition);
}
