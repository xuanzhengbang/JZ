package com.opopto.zdsetmealfood.dao;

import com.opopto.zdsetmealfood.entity.ZdPlace;
import com.opopto.zdsetmealfood.entity.ZdPlaceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZdPlaceMapper {
    int countByExample(ZdPlaceExample example);

    int deleteByExample(ZdPlaceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZdPlace record);

    int insertSelective(ZdPlace record);

    List<ZdPlace> selectByExample(ZdPlaceExample example);

    ZdPlace selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZdPlace record, @Param("example") ZdPlaceExample example);

    int updateByExample(@Param("record") ZdPlace record, @Param("example") ZdPlaceExample example);

    int updateByPrimaryKeySelective(ZdPlace record);

    int updateByPrimaryKey(ZdPlace record);
}