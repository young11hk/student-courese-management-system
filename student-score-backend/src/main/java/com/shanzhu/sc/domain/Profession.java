package com.shanzhu.sc.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 专业实体类
 *
 */
@Data
public class Profession implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 专业id
     */
    private Integer id;

    /**
     * 专业名
     */
    private String name;
}
