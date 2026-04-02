package com.shanzhu.sc.domain;

import lombok.Data;

/**
 * 教师课程任命表实体类
 *
 * @author: ShanZhu
 * @date: 2024-01-08
 */
@Data
public class TeacherCourse {

    /**
     * id
     */
    private Integer id;

    /**
     * 教师id
     */
    private String teacherId;

    /**
     * 教师用户名
     */
    private String username;

    /**
     * 课程名
     */
    private String name;

    /**
     * 专业
     */
    private String profession;

    /**
     * 班级
     */
    private String grade;

    /**
     * 学期
     */
    private Integer term;

    /**
     * 课程id
     */
    private String courseId;

    private CourseInfo courseInfo;

}
