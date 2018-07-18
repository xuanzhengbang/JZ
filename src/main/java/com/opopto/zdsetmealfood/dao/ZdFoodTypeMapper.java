package com.opopto.zdsetmealfood.dao;

import com.opopto.zdsetmealfood.entity.ZdFoodType;
import com.opopto.zdsetmealfood.entity.ZdFoodTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZdFoodTypeMapper {
    int countByExample(ZdFoodTypeExample example);

    int deleteByExample(ZdFoodTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZdFoodType record);

    int insertSelective(ZdFoodType record);

    List<ZdFoodType> selectByExample(ZdFoodTypeExample example);

    ZdFoodType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZdFoodType record, @Param("example") ZdFoodTypeExample example);

    int updateByExample(@Param("record") ZdFoodType record, @Param("example") ZdFoodTypeExample example);

    int updateByPrimaryKeySelective(ZdFoodType record);

    int updateByPrimaryKey(ZdFoodType record);
}