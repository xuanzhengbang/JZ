package com.opopto.zdsetmealfood.controller;

import com.alibaba.fastjson.JSONObject;
import com.opopto.zdsetmealfood.entity.ZdFood;
import com.opopto.zdsetmealfood.helper.ServiceParamHelper;
import com.opopto.zdsetmealfood.service.ZdFoodService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CommonsLog
@Controller
@ResponseBody
@RequestMapping("food")
public class FoodController extends BaseApiController {

    @Autowired
    private ZdFoodService foodService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(String name, String typeCode){
        ZdFood food = new ZdFood();
        food.setName(name);
        food.setTypeCode(typeCode);
        food.setCode("f_" + System.currentTimeMillis());
        foodService.create(food);
        return ServiceParamHelper.createSuccessResultJSONObject();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object list(){
        JSONObject result = ServiceParamHelper.createSuccessResultJSONObject();
        List<ZdFood> foodList = foodService.list();
        result.put("data", foodList);
        return result;
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public Object query(String type){
        JSONObject result = ServiceParamHelper.createSuccessResultJSONObject();
        if(StringUtils.isEmpty(type)){
            return result;
        }
        Map<String,String> param = new HashMap<>();

        param.put("type", type);

        List<ZdFood> foodList = foodService.query(param);
        result.put("data", foodList);
        return result;
    }

}
