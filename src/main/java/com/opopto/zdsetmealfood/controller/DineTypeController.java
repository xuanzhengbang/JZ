package com.opopto.zdsetmealfood.controller;

import com.alibaba.fastjson.JSONObject;
import com.opopto.zdsetmealfood.helper.ServiceParamHelper;
import com.opopto.zdsetmealfood.service.ZdDineTypeService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  订餐类型
 */

@CommonsLog
@Controller
@ResponseBody
@RequestMapping("dineType")
public class DineTypeController extends BaseApiController {

    @Autowired
    private ZdDineTypeService dineTypeService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Object list(){
        JSONObject result = ServiceParamHelper.createSuccessResultJSONObject();
        result.put("data", dineTypeService.list());
        return result;

    }

}
