package com.opopto.zdsetmealfood.controller;

import com.alibaba.fastjson.JSONObject;
import com.opopto.zdsetmealfood.helper.ServiceParamHelper;
import com.opopto.zdsetmealfood.service.ZdPlaceService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CommonsLog
@ResponseBody
@RequestMapping("place")
public class PlaceController extends BaseApiController{

    @Autowired
    private ZdPlaceService placeService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Object list(){
        JSONObject result = ServiceParamHelper.createSuccessResultJSONObject();
        result.put("data", placeService.list());
        return result;
    }



}
