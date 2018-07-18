package com.opopto.zdsetmealfood.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opopto.zdsetmealfood.dao.ZdPlaceItemMapper;
import com.opopto.zdsetmealfood.dao.ZdPlaceMapper;
import com.opopto.zdsetmealfood.entity.ZdPlace;
import com.opopto.zdsetmealfood.entity.ZdPlaceExample;
import com.opopto.zdsetmealfood.entity.ZdPlaceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZdPlaceService {

    @Autowired
    private ZdPlaceMapper placeMapper;
    @Autowired
    private ZdPlaceItemMapper placeItemMapper;


    public Object list(){
        JSONArray array = new JSONArray();

        ZdPlaceExample placeExample = new ZdPlaceExample();
        placeExample.createCriteria().andDelFlagEqualTo(0);
        List<ZdPlace> placeList = placeMapper.selectByExample(placeExample);
        for(ZdPlace place : placeList){
            List<ZdPlaceItem> placeItemList =  placeItemMapper.selectByPlaceCode(place.getCode());
            JSONObject object = (JSONObject) JSONObject.toJSON(place);
            object.put("items", placeItemList);
            array.add(object);
        }
        return array;
    }

    public void create(ZdPlace place){
        placeMapper.insertSelective(place);
    }

    public void createItem(ZdPlaceItem item){
        placeItemMapper.insertSelective(item);
    }

}
