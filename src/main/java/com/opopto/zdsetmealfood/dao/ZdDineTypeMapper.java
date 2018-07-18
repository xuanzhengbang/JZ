package com.opopto.zdsetmealfood.dao;

import com.opopto.zdsetmealfood.entity.ZdDineType;
import com.opopto.zdsetmealfood.entity.ZdDineTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZdDineTypeMapper {
    int countByExample(ZdDineTypeExample example);

    int deleteByExample(ZdDineTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZdDineType record);

    int insertSelective(ZdDineType record);

    List<ZdDineType> selectByExample(ZdDineTypeExample example);

    ZdDineType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZdDineType record, @Param("example") ZdDineTypeExample example);

    int updateByExample(@Param("record") ZdDineType record, @Param("example") ZdDineTypeExample example);

    int updateByPrimaryKeySelective(ZdDineType record);

    int updateByPrimaryKey(ZdDineType record);

    List<ZdDineType> listAll();
}