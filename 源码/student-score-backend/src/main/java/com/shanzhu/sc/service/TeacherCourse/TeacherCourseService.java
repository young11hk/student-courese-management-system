package com.shanzhu.sc.service.TeacherCourse;

import com.shanzhu.sc.domain.TeacherCourse;
import com.shanzhu.sc.dto.Course;

import java.util.List;
import java.util.Map;

/**
 * 教师课程 业务服务层
 *
 * @author: ShanZhu
 * @date: 2024-01-08
 */
public interface TeacherCourseService {

    /**
     * 新增教师课程
     *
     * @param list 课程信息
     */
    void add(List<TeacherCourse> list);

    /**
     * 删除教师课程
     *
     * @param ids 课程id
     */
    void delete(List<Integer> ids);

    /**
     * 修改教师课程
     *
     * @param teacherCourse 教师课程
     */
    void update(TeacherCourse teacherCourse);

    /**
     * 根据教师id获取教师课程信息
     *
     * @param id 教师id
     * @return 教师课程信息
     */
    List<TeacherCourse> getCourseListById(String id);

    /**
     * 根据教师id获取教师用户所管理的专业、班级、课程名等信息
     *
     * @param teacherId 教师id
     * @return 结果
     */
    List<Map<String, Object>> getProfessionInfo(String teacherId);

    /**
     * 管理员用户获取所有的班级和课程，渲染成下拉框选择
     *
     * @return 结果
     */
    List<Map<String, Object>> getProfessionInfoByAdmin();

    /**
     * 课程表中获取课程详细信息
     *
     * @param condition 查询条件
     * @return 课程信息
     */
    Course getCourseInfo(Map<String, Object> condition);
}
