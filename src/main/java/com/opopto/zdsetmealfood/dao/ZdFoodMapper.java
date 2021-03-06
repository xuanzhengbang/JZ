package com.opopto.zdsetmealfood.dao;

import com.opopto.zdsetmealfood.entity.ZdFood;
import com.opopto.zdsetmealfood.entity.ZdFoodExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

public interface ZdFoodMapper {
    int countByExample(ZdFoodExample example);

    int deleteByExample(ZdFoodExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZdFood record);

    int insertSelective(ZdFood record);

    List<ZdFood> selectByExample(ZdFoodExample example);

    ZdFood selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZdFood record, @Param("example") ZdFoodExample example);

    int updateByExample(@Param("record") ZdFood record, @Param("example") ZdFoodExample example);

    int updateByPrimaryKeySelective(ZdFood record);

    int updateByPrimaryKey(ZdFood record);

    ZdFood selectByName(@Param("name") String name);

    List<ZdFood> query(@Param("type") String type, @Param("foodName") String foodName);

    List<ZdFood> listAndTypeName();

}