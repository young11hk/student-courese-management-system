package com.shanzhu.sc.service.Profession;

import com.shanzhu.sc.domain.Profession;

import java.util.List;


/**
 * 专业信息 业务服务层
 *
 * @author: ShanZhu
 * @date: 2024-01-08
 */
public interface ProfessionService {

    /**
     * 获取专业
     *
     * @return 专业列表
     */
    List<Profession> getProfessionList();
}
