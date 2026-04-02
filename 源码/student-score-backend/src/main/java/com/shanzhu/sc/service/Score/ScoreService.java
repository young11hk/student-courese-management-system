package com.shanzhu.sc.service.Score;

import com.shanzhu.sc.dto.Course;
import com.shanzhu.sc.dto.Score;
import com.shanzhu.sc.utils.PagingResult;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * 分数信息 业务服务层
 *
 * @author: ShanZhu
 * @date: 2024-01-08
 */
public interface ScoreService {

    /**
     * 根据班级等信息获取课程信息
     *
     * @param rowBounds 分页条件
     * @param condition 查询条件
     * @return 课程信息
     */
    PagingResult<Course> getCourseList(RowBounds rowBounds, Map<String, Object> condition);

    /**
     * 录入成绩
     *
     * @param list 成绩列表
     */
    void addEntry(List<Score> list);

    /**
     * 获取导出成绩到Excel的数据
     *
     * @param condition 查询条件
     * @return 成绩列表
     */
    List<Course> getExportList(Map<String, Object> condition);

    /**
     * 根据成绩划分等级（不及格、及格、优秀）
     *
     * @param condition 查询条件
     * @return 成绩信息
     */
    List<Map<String, Object>> getUserNum(Map<String, Object> condition);

    /**
     * 学生用户根据id获取总学分和总绩点
     *
     * @param condition 查询条件
     * @return 成绩信息
     */
    Map<String, Object> getUserTotal(Map<String, Object> condition);
}
