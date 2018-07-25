package com.opopto.zdsetmealfood.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.opopto.zdsetmealfood.entity.ZdFood;
import com.opopto.zdsetmealfood.entity.ZdFoodType;
import com.opopto.zdsetmealfood.helper.ServiceParamHelper;
import com.opopto.zdsetmealfood.service.ZdFoodTypeService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CommonsLog
@Controller
@ResponseBody
public class FoodTypeController extends BaseApiController {

    @Autowired
    private ZdFoodTypeService foodTypeService;

    @RequestMapping(value = "/foodType/create", method = RequestMethod.POST)
    public Object create(String name,String desc){
        JSONObject result = ServiceParamHelper.createSuccessResultJSONObject();
        ZdFoodType type = new ZdFoodType();
        type.setName(name);
        type.setDesc(desc);
        type.setCode("ft_" + System.currentTimeMillis());
        type = foodTypeService.create(type);
        result.put("data", type);
        return result;
    }

    @RequestMapping(value = "/foodType", method = RequestMethod.GET)
    public ModelAndView foodType(HttpServletRequest request){
        Object user = request.getSession().getAttribute("userLogin");
        ModelAndView model = new ModelAndView();
        if(user == null){
            model.setViewName("login");
        } else {
            List<ZdFoodType> foodTypes = foodTypeService.list();
            model.addObject("foodTypes",foodTypes);
            model.setViewName("foodType");
        }
        return model;
    }

}
