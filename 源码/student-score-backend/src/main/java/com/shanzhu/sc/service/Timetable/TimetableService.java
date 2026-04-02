package com.shanzhu.sc.service.Timetable;

import com.shanzhu.sc.domain.CourseInfo;
import com.shanzhu.sc.domain.WeekCourse;

import java.util.List;
import java.util.Map;

/**
 * 课程表 业务服务层
 *
 * @author: ShanZhu
 * @date: 2024-01-08
 */
public interface TimetableService {

    /**
     * 新增课程表
     *
     * @param list 课程信息
     */
    void add(List<WeekCourse> list);

    /**
     * 管理员用户获取课程表
     *
     * @param condition 查询条件
     * @return 课程表数据
     */
    List<WeekCourse> getTimetable(Map<String, Object> condition);

    /**
     * 学生用户获取课程表
     *
     * @param condition 查询条件
     * @return 结果
     */
    List<WeekCourse> getTimetableByStudent(Map<String, Object> condition);

    /**
     * 教师用户获取课程表
     *
     * @param condition 查询条件
     * @return 课程表数据
     */
    List<WeekCourse> getTimetableByTeacher(Map<String, Object> condition);

    /**
     * 新增课程具体安排（周数等）
     *
     * @param courseInfo 课程信息
     */
    void updateCourseInfo(CourseInfo courseInfo);
}
