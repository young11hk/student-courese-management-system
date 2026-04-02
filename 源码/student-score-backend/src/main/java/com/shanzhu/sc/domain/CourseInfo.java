package com.shanzhu.sc.domain;

import lombok.Data;

/**
 * 课程具体安排
 *
 * @author: ShanZhu
 * @date: 2024-01-08
 */
@Data
public class CourseInfo {

    /**
     * id
     */
    private Integer id;

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 周数 start
     */
    private Integer start;

    /**
     * 周数 end
     */
    private Integer end;

    /**
     * 教室
     */
    private String room;

    /**
     * 专业
     */
    private String profession;

}
