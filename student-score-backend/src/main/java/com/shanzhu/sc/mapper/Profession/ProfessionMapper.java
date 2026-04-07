package com.shanzhu.sc.mapper.Profession;

import com.shanzhu.sc.domain.Profession;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProfessionMapper {

    List<Profession> getProfessionList();


    Integer checkProfessionCount(@Param("name") String name);


    void addProfession(Profession profession);


    void deleteProfession(@Param("id") Integer id);


    Profession getProfessionIdByName(@Param("name") String name);
}
