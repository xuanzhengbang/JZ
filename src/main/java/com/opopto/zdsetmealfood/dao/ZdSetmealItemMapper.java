package com.opopto.zdsetmealfood.dao;

import com.opopto.zdsetmealfood.entity.ZdSetmealItem;
import com.opopto.zdsetmealfood.entity.ZdSetmealItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZdSetmealItemMapper {
    int countByExample(ZdSetmealItemExample example);

    int deleteByExample(ZdSetmealItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZdSetmealItem record);

    int insertSelective(ZdSetmealItem record);

    List<ZdSetmealItem> selectByExample(ZdSetmealItemExample example);

    ZdSetmealItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZdSetmealItem record, @Param("example") ZdSetmealItemExample example);

    int updateByExample(@Param("record") ZdSetmealItem record, @Param("example") ZdSetmealItemExample example);

    int updateByPrimaryKeySelective(ZdSetmealItem record);

    int updateByPrimaryKey(ZdSetmealItem record);
}