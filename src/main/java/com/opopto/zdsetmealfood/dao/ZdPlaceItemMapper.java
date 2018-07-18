package com.opopto.zdsetmealfood.dao;

import com.opopto.zdsetmealfood.entity.ZdPlaceItem;
import com.opopto.zdsetmealfood.entity.ZdPlaceItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZdPlaceItemMapper {
    int countByExample(ZdPlaceItemExample example);

    int deleteByExample(ZdPlaceItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZdPlaceItem record);

    int insertSelective(ZdPlaceItem record);

    List<ZdPlaceItem> selectByExample(ZdPlaceItemExample example);

    ZdPlaceItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZdPlaceItem record, @Param("example") ZdPlaceItemExample example);

    int updateByExample(@Param("record") ZdPlaceItem record, @Param("example") ZdPlaceItemExample example);

    int updateByPrimaryKeySelective(ZdPlaceItem record);

    int updateByPrimaryKey(ZdPlaceItem record);
}