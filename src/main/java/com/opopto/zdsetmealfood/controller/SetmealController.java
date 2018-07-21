package com.opopto.zdsetmealfood.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opopto.zdsetmealfood.entity.ZdSetmeal;
import com.opopto.zdsetmealfood.entity.ZdSetmealItem;
import com.opopto.zdsetmealfood.helper.ServiceParamHelper;
import com.opopto.zdsetmealfood.service.SetmealService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CommonsLog
@ResponseBody
@RequestMapping("setmeal")
public class SetmealController extends BaseApiController {

    @Autowired
    private SetmealService setmealService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(JSONObject requestJson){
        JSONObject result = ServiceParamHelper.createSuccessResultJSONObject();
        ZdSetmeal setmeal = new ZdSetmeal();
        setmeal.setPrice(requestJson.getBigDecimal("price"));
        setmeal.setBookDate(requestJson.getDate("bookDate"));
        setmeal.setPlaceCode(requestJson.getString("placeCode"));
        setmeal.setDineTypeCode(requestJson.getString("dineTypeCode"));
        setmeal.setNum(requestJson.getString("num"));
        List<ZdSetmealItem> itemList = JSONArray.parseArray(requestJson.getJSONArray("item").toJSONString(),ZdSetmealItem.class);

        setmealService.create(setmeal, itemList);

        return result;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object list(){
        JSONObject result = ServiceParamHelper.createSuccessResultJSONObject();

        result.put("data", setmealService.list());

        return result;
    }


}
