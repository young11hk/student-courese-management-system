package com.shanzhu.sc.dto;

import lombok.Data;

@Data
public class Timetable {

    /**
     * id
     */
    private Integer id;

    /**
     * week id
     */
    private Integer weekId;

    /**
     * 专业
     */
    private String profession;

    /**
     * 班级
     */
    private String grade;

    /**
     * 学年
     */
    private Integer year;

    /**
     * 学期
     */
    private Integer term;

    /**
     * 周数
     */
    private Integer weekNum;

}
