package com.opopto.zdsetmealfood.service;

import com.opopto.zdsetmealfood.dao.ZdFoodTypeMapper;
import com.opopto.zdsetmealfood.entity.ZdFoodType;
import com.opopto.zdsetmealfood.entity.ZdFoodTypeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZdFoodTypeService {

    @Autowired
    private ZdFoodTypeMapper foodTypeMapper;


    public boolean create(ZdFoodType type){
        foodTypeMapper.insertSelective(type);
        return true;
    }

    public List<ZdFoodType> list(){
        ZdFoodTypeExample example = new ZdFoodTypeExample();
        example.createCriteria().andDelFlagEqualTo(0);
        List<ZdFoodType> list = foodTypeMapper.selectByExample(example);
        return list;
    }


}
