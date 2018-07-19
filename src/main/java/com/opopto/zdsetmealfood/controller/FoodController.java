package com.opopto.zdsetmealfood.controller;

import com.opopto.zdsetmealfood.entity.ZdFood;
import com.opopto.zdsetmealfood.service.ZdFoodService;
import javafx.geometry.Pos;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    }

}
