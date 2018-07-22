package com.opopto.zdsetmealfood.controller;

import com.alibaba.fastjson.JSONObject;
import com.opopto.zdsetmealfood.entity.ZdFoodType;
import com.opopto.zdsetmealfood.helper.ServiceParamHelper;
import com.opopto.zdsetmealfood.service.ZdFoodTypeService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@CommonsLog
@Controller
@ResponseBody
@RequestMapping("foodType")
public class FoodTypeController extends BaseApiController {

    @Autowired
    private ZdFoodTypeService foodTypeService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(String name){
        JSONObject result = ServiceParamHelper.createSuccessResultJSONObject();
        ZdFoodType type = new ZdFoodType();
        type.setName(name);
        type.setCode("ft_" + System.currentTimeMillis());
        type = foodTypeService.create(type);
        result.put("data", type);
        return result;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object list(){
        JSONObject result = ServiceParamHelper.createSuccessResultJSONObject();
        List<ZdFoodType> foodTypeList = foodTypeService.list();
        result.put("data", foodTypeList);
        return result;
    }

}
