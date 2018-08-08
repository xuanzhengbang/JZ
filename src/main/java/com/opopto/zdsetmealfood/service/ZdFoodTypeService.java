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


    public ZdFoodType create(ZdFoodType type){

        ZdFoodTypeExample example = new ZdFoodTypeExample();
        example.createCriteria()
                .andDelFlagEqualTo(0)
                .andNameEqualTo(type.getName());
        List<ZdFoodType> check = foodTypeMapper.selectByExample(example);
        if(!check.isEmpty()){
            return check.get(0);
        }
        foodTypeMapper.insertSelective(type);
        return type;
    }

    public List<ZdFoodType> list(){
        ZdFoodTypeExample example = new ZdFoodTypeExample();
        example.createCriteria().andDelFlagEqualTo(0);
        List<ZdFoodType> list = foodTypeMapper.selectByExample(example);
        return list;
    }

    public void delete(Integer id){
        ZdFoodType foodType = new ZdFoodType();
        foodType.setId(id);
        foodType.setDelFlag(1);
        foodTypeMapper.updateByPrimaryKeySelective(foodType);
    }

}
