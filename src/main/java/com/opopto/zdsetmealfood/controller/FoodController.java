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
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CommonsLog
@Controller
@ResponseBody
public class FoodController extends BaseApiController {

    @Autowired
    private ZdFoodService foodService;

    @RequestMapping(value = "/food/create", method = RequestMethod.POST)
    public Object create(String name, String typeCode){
        ZdFood food = new ZdFood();
        food.setName(name);
        food.setTypeCode(typeCode);
        food.setCode("f_" + System.currentTimeMillis());
        foodService.create(food);
        return ServiceParamHelper.createSuccessResultJSONObject();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView list(){

        ModelAndView model = new ModelAndView();
        List<ZdFood> foodList = foodService.list();
        model.addObject("foods",foodList);
        model.setViewName("index");
        return model;
    }

    @RequestMapping(value = "/food/query", method = RequestMethod.GET)
    public ModelAndView query(String type){
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        if(StringUtils.isEmpty(type)){
            return model;
        }
        List<ZdFood> foodList = foodService.query(type);
        model.addObject("foods",foodList);
        return model;
    }

}
