package com.opopto.zdsetmealfood.dao;

import com.opopto.zdsetmealfood.entity.ZdUser;
import com.opopto.zdsetmealfood.entity.ZdUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZdUserMapper {
    int countByExample(ZdUserExample example);

    int deleteByExample(ZdUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ZdUser record);

    int insertSelective(ZdUser record);

    List<ZdUser> selectByExample(ZdUserExample example);

    ZdUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ZdUser record, @Param("example") ZdUserExample example);

    int updateByExample(@Param("record") ZdUser record, @Param("example") ZdUserExample example);

    int updateByPrimaryKeySelective(ZdUser record);

    int updateByPrimaryKey(ZdUser record);
}