package com.opopto.zdsetmealfood.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opopto.zdsetmealfood.dao.*;
import com.opopto.zdsetmealfood.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SetmealService {

    @Autowired
    private ZdSetmealMapper setmealMapper;
    @Autowired
    private ZdSetmealItemMapper setmealItemMapper;
    @Autowired
    private ZdFoodMapper foodMapper;
    @Autowired
    private ZdPlaceMapper placeMapper;
    @Autowired
    private ZdDineTypeMapper dineTypeMapper;

    public void create(ZdSetmeal setmeal, List<ZdSetmealItem> items){
        //TODO  批量插入、批量查询优化点
        String code = "s_" + System.currentTimeMillis();
        setmeal.setCode(code);
        setmeal.setCreateUid("0000");
        setmealMapper.insertSelective(setmeal);

        for(ZdSetmealItem item : items){
            item.setSetmealCode(code);
            item.setFoodCode(foodMapper.selectByName(item.getFoodName()).getCode());
            setmealItemMapper.insertSelective(item);
        }
    }

    public List<ZdSetmeal> list(){
        //TODO 优化点-查询
        ZdSetmealExample example = new ZdSetmealExample();
        example.createCriteria()
                .andDelFlagEqualTo(0);
        List<ZdSetmeal> setmealList = setmealMapper.selectByExample(example);


        List<ZdSetmeal> result = new ArrayList<>();

        for(ZdSetmeal setmeal : setmealList){
            ZdSetmealItemExample itemExample = new ZdSetmealItemExample();
            itemExample.createCriteria()
                    .andSetmealCodeEqualTo(setmeal.getCode())
                    .andDelFlagEqualTo(0);
            setmeal.setItems(setmealItemMapper.selectByExample(itemExample));
            result.add(setmeal);
        }

        return result;
    }

}
