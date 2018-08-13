package com.opopto.zdsetmealfood.service;

import com.opopto.zdsetmealfood.dao.ZdFoodMapper;
import com.opopto.zdsetmealfood.entity.ZdFood;
import com.opopto.zdsetmealfood.entity.ZdFoodExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ZdFoodService {

    @Autowired
    private ZdFoodMapper foodMapper;


    public ZdFood create(ZdFood food){
        ZdFood check = foodMapper.selectByName(food.getName());
        if(check != null){
            return check;
        }
        foodMapper.insertSelective(food);
        return food;
    }

    public List<ZdFood> list(){
        ZdFoodExample example = new ZdFoodExample();
        example.createCriteria().andDelFlagEqualTo(0);
        example.setOrderByClause("id");
        List<ZdFood> list = foodMapper.selectByExample(example);
        return list;
    }

    public List<ZdFood> query(String type, String foodName){
        List<ZdFood> list = foodMapper.query(type, foodName);
        return list;
    }

    public List<ZdFood> listAndTypeName(){
        return foodMapper.listAndTypeName();
    }

    public void delete(Integer id){
        ZdFood food = new ZdFood();
        food.setId(id);
        food.setDelFlag(1);
        foodMapper.updateByPrimaryKeySelective(food);
    }

}
