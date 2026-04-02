package com.shanzhu.sc.service.Course;

import com.shanzhu.sc.dto.Course;
import com.shanzhu.sc.utils.PagingResult;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * 课程信息 业务服务层
 *
 * @author: ShanZhu
 * @date: 2024-01-08
 */
public interface CourseService {

    /**
     * 新增课程信息
     *
     * @param course 课程信息
     */
    void addCourse(Course course);

    /**
     * 删除课程信息
     *
     * @param ids 课程id列表
     */
    void delete(List<Long> ids);

    /**
     * 修改课程信息
     *
     * @param course 课程信息
     */
    void update(Course course);

    /**
     * 查询课程信息
     *
     * @param rowBounds 分页信息
     * @param condition 查询条件
     * @return 课程信息列表
     */
    PagingResult<Course> getCourseList(RowBounds rowBounds, Map<String, Object> condition);

    /**
     * 根据专业、学期获取课程列表
     *
     * @param condition 查询条件
     * @return 课程信息列表
     */
    List<Course> getCourseByMap(Map<String, Object> condition);
}

