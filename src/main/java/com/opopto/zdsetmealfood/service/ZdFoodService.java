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


    public boolean create(ZdFood food){
        foodMapper.insertSelective(food);
        return true;
    }

    public List<ZdFood> list(){
        ZdFoodExample example = new ZdFoodExample();
        example.createCriteria().andDelFlagEqualTo(0);
        List<ZdFood> list = foodMapper.selectByExample(example);
        return list;
    }

    public List<ZdFood> query(String name){
        List<ZdFood> list = foodMapper.query(name);
        return list;
    }

}
