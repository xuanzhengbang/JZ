package com.opopto.zdsetmealfood.service;

import com.opopto.zdsetmealfood.dao.*;
import com.opopto.zdsetmealfood.entity.*;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@CommonsLog
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
        example.setOrderByClause("id");
        List<ZdSetmeal> setmealList = setmealMapper.selectByExample(example);

        return setmealList;
    }

    public List<ZdSetmealItem> listItemByCode(String code){
        ZdSetmealItemExample itemExample = new ZdSetmealItemExample();
        itemExample.createCriteria()
                .andSetmealCodeEqualTo(code)
                .andDelFlagEqualTo(0);
        return setmealItemMapper.selectByExample(itemExample);
    }

    public ZdSetmeal getByCode(String code){
        ZdSetmeal setmeal = setmealMapper.selectByCode(code);
        setmeal.setItems(listItemByCode(code));
        return setmeal;
    }

    public ZdSetmeal getByStandardName(String standardName){
        ZdSetmeal setmeal = setmealMapper.selectByStandardName(standardName);
        return setmeal;
    }

    public void deleteById(Integer id){
        int  setmeal = setmealMapper.deleteByPrimaryKey(id);
//        setmeal.setDelFlag(1);
//        setmealMapper.updateByPrimaryKeySelective(setmeal);
//        setmealItemMapper.deleteBySetmealCode(setmeal.getCode());
    }

    public List<ZdSetmeal> listAllByOrder(){
        return setmealMapper.listAllSetmeal();
    }

    public Map<String, Object> getStandardName(Integer price){
        List<Map<String, Object>> maps = setmealMapper.getStandardName(price);
        log.info(" 获取列表： " +maps);
        Map<String, Object> result = new HashMap<>();
        if(maps==null||maps.isEmpty()){
            result.put("standardName", price + "元套餐1");
            result.put("t", "0");
        } else {
            Integer t = ((Long) maps.get(0).get("t")).intValue();
            if(maps.size() == t){
                result.put("standardName", price + "元套餐" + (t+1));
                result.put("t", t+1);
            } else {
                List<Integer> ts = maps.stream().map(m->((Long)(m.get("t"))).intValue()).collect(Collectors.toList());
                log.info(ts);
                for(int i=1; i<=t; i++){
                    if(!ts.contains(i)){
                        result.put("standardName", price + "元套餐" + i);
                        result.put("t", i);
                        break;
                    }
                }
            }
        }
        return result;
    }

}
