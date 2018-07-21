package com.opopto.zdsetmealfood.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opopto.zdsetmealfood.dao.ZdFoodMapper;
import com.opopto.zdsetmealfood.dao.ZdSetmealItemMapper;
import com.opopto.zdsetmealfood.dao.ZdSetmealMapper;
import com.opopto.zdsetmealfood.entity.ZdSetmeal;
import com.opopto.zdsetmealfood.entity.ZdSetmealExample;
import com.opopto.zdsetmealfood.entity.ZdSetmealItem;
import com.opopto.zdsetmealfood.entity.ZdSetmealItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetmealService {

    @Autowired
    private ZdSetmealMapper setmealMapper;
    @Autowired
    private ZdSetmealItemMapper setmealItemMapper;
    @Autowired
    private ZdFoodMapper foodMapper;

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

    public Object list(){
        //TODO 优化点-查询
        ZdSetmealExample example = new ZdSetmealExample();
        example.createCriteria().andDelFlagEqualTo(0);
        List<ZdSetmeal> setmealList = setmealMapper.selectByExample(example);

        JSONArray array = new JSONArray();
        for(ZdSetmeal setmeal : setmealList){
            JSONObject object = (JSONObject) JSONObject.toJSON(setmeal);
            ZdSetmealItemExample itemExample = new ZdSetmealItemExample();
            itemExample.createCriteria().andSetmealCodeEqualTo(setmeal.getCode()).andDelFlagEqualTo(0);
            object.put("items", setmealItemMapper.selectByExample(itemExample));
            array.add(object);
        }
        return array;
    }

}
