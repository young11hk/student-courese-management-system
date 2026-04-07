package com.shanzhu.sc.dto;

import lombok.Data;

@Data
public class Score {

    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 课程名
     */
    private String name;

    /**
     * 分数
     */
    private String scoreByUser;

    /**
     * 绩点
     */
    private String pointByUser;

    /**
     * 学分
     */
    private String creditsByUser;

    /**
     * 总分
     */
    private Integer score;

    /**
     * 学分
     */
    private String credits;

    /**
     * 学期
     */
    private Integer term;

    /**
     * 届时
     */
    private Integer year;

    /**
     * 学号
     */
    private String no;

    /**
     * 学生账号id
     */
    private String studentId;

    /**
     * 课程id
     */
    private String courseId;

}
