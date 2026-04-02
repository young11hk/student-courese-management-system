package com.shanzhu.sc.service.Profession.impl;

import com.shanzhu.sc.domain.Profession;
import com.shanzhu.sc.mapper.Profession.ProfessionMapper;
import com.shanzhu.sc.service.Profession.ProfessionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProfessionServiceImpl implements ProfessionService {

    @Resource
    private ProfessionMapper professionMapper;

    @Override
    public List<Profession> getProfessionList() {
        return professionMapper.getProfessionList();
    }
}
