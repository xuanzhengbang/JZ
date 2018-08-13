package com.opopto.zdsetmealfood.dao;

import com.opopto.zdsetmealfood.entity.ZdSetmeal;
import com.opopto.zdsetmealfood.entity.ZdSetmealExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ZdSetmealMapper {
    int countByExample(ZdSetmealExample example);

    int deleteByExample(ZdSetmealExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZdSetmeal record);

    int insertSelective(ZdSetmeal record);

    List<ZdSetmeal> selectByExample(ZdSetmealExample example);

    ZdSetmeal selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZdSetmeal record, @Param("example") ZdSetmealExample example);

    int updateByExample(@Param("record") ZdSetmeal record, @Param("example") ZdSetmealExample example);

    int updateByPrimaryKeySelective(ZdSetmeal record);

    int updateByPrimaryKey(ZdSetmeal record);


    ZdSetmeal selectByCode(@Param("code") String code);

    ZdSetmeal selectByStandardName(@Param("standardName") String standardName);


    List<ZdSetmeal> listAllSetmeal();

    Map<String, Object> getStandardName(Integer price);

}