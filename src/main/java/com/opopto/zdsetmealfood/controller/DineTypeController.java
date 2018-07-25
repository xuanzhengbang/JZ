package com.opopto.zdsetmealfood.controller;

import com.alibaba.fastjson.JSONObject;
import com.opopto.zdsetmealfood.entity.ZdDineType;
import com.opopto.zdsetmealfood.helper.EncryptHelper;
import com.opopto.zdsetmealfood.helper.ServiceParamHelper;
import com.opopto.zdsetmealfood.service.ZdDineTypeService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 *  订餐类型
 */

@CommonsLog
@Controller
@ResponseBody
public class DineTypeController extends BaseApiController {

    @Autowired
    private ZdDineTypeService dineTypeService;

    @RequestMapping(value = "/dineType/create", method = RequestMethod.POST)
    public Object create(String name, String desc){
        JSONObject result = ServiceParamHelper.createSuccessResultJSONObject();
        ZdDineType dineType = new ZdDineType();
        dineType.setName(name);
        dineType.setCode("dt_" + System.currentTimeMillis());
        dineType.setDesc(desc);
        dineType.setDelFlag(0);
        dineType = dineTypeService.create(dineType);
        result.put("data", dineType);
        return result;
    }

    @RequestMapping(value = "/dineType", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request){
        Object user = request.getSession().getAttribute("userLogin");
        ModelAndView model = new ModelAndView();
        if(user == null){
            model.setViewName("login");
        } else {
            model.addObject("dineTypes",dineTypeService.list());
            model.setViewName("dineType");
        }
        return model;
    }

}
