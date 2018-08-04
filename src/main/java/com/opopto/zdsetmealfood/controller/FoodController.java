package com.opopto.zdsetmealfood.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.opopto.zdsetmealfood.entity.ZdFood;
import com.opopto.zdsetmealfood.entity.ZdFoodType;
import com.opopto.zdsetmealfood.helper.ServiceParamHelper;
import com.opopto.zdsetmealfood.service.ZdFoodService;
import com.opopto.zdsetmealfood.service.ZdFoodTypeService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CommonsLog
@Controller
@ResponseBody
public class FoodController extends BaseApiController {

    @Autowired
    private ZdFoodService foodService;
    @Autowired
    private ZdFoodTypeService foodTypeService;

    @RequestMapping(value = "/food/create", method = RequestMethod.POST)
    public Object create(String name, String typeCode, String desc){
        JSONObject result = ServiceParamHelper.createSuccessResultJSONObject();
        ZdFood food = new ZdFood();
        food.setName(name);
        food.setTypeCode(typeCode);
        food.setDesc(desc);
        food.setCode("f_" + System.currentTimeMillis());
        food = foodService.create(food);
        result.put("data", food);
        return result;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request){
        Object user = request.getSession().getAttribute("userLogin");
        ModelAndView model = new ModelAndView();
        List<ZdFood> foodList = foodService.list();
        if(user == null){
            model.setViewName("login");
        } else {
            model.addObject("foods",foodList);
            model.setViewName("index");
        }

        return model;
    }

    @RequestMapping(value = "/food/query.json", method = RequestMethod.GET)
    public Object queryJson(@RequestParam(required = false) String type, @RequestParam(required = false) String foodName){
        JSONObject result = ServiceParamHelper.createSuccessResultJSONObject();
        if(type=="*"){
            type = null;
        }
        if(foodName == "*"){
            foodName = null;
        }
        List<ZdFood> foods = foodService.query(type, foodName);
        result.put("data", foods);
        result.put("total", foods.size());
        return result;
    }

    @RequestMapping(value = "/food/query", method = RequestMethod.GET)
    public ModelAndView query(@RequestParam(required = false)String type, @RequestParam(required = false) String foodName, HttpServletRequest request){
        Object user = request.getSession().getAttribute("userLogin");
        ModelAndView model = new ModelAndView();
        if(StringUtils.isEmpty(type)||type=="*"){
            type = null;
        }

        if(foodName=="*")
            foodName = null;
        List<ZdFood> foodList = foodService.query(type,foodName);
        if(user == null){
            model.setViewName("login");
        } else {
            model.addObject("foods",foodList);
            model.setViewName("index");
        }
        return model;
    }

    @RequestMapping(value = "/food/list", method = RequestMethod.GET)
    public Object listFood(String type, HttpServletRequest request){
        Object user = request.getSession().getAttribute("userLogin");
        JSONObject result = ServiceParamHelper.createSuccessResultJSONObject();
        if(user == null){
            return ServiceParamHelper.createResultJSONObject(443,"Permission denied");
        } else {
            List<ZdFood> foodList = foodService.query(type,null);
            result.put("data", foodList);
            result.put("total", foodList.size());
            return result;
        }
    }

    @RequestMapping(value = "/food", method = RequestMethod.GET)
    public ModelAndView food(@RequestParam(defaultValue = "1")Integer pageNum, @RequestParam(defaultValue = "5")Integer pageSize, HttpServletRequest request){
        Object user = request.getSession().getAttribute("userLogin");
        ModelAndView model = new ModelAndView();

        if(user == null){
            model.setViewName("login");
        } else {
            PageHelper.startPage(pageNum, pageSize);
            List<ZdFood> foodList = foodService.listAndTypeName();
            PageInfo<ZdFood> info = new PageInfo<ZdFood>(foodList);
            List<ZdFoodType> types = foodTypeService.list();
            model.addObject("foods",foodList);
            model.addObject("types", types);
            model.addObject("total", info.getPages());
            model.addObject("pageNum", pageNum);
            model.setViewName("food");
        }
        return model;
    }

}
