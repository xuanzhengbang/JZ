package com.opopto.zdsetmealfood.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.opopto.zdsetmealfood.entity.ZdFood;
import com.opopto.zdsetmealfood.entity.ZdFoodType;
import com.opopto.zdsetmealfood.entity.ZdSetmeal;
import com.opopto.zdsetmealfood.entity.ZdSetmealItem;
import com.opopto.zdsetmealfood.helper.ServiceParamHelper;
import com.opopto.zdsetmealfood.service.SetmealService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@CommonsLog
@ResponseBody
public class SetmealController extends BaseApiController {

    @Autowired
    private SetmealService setmealService;

    @RequestMapping(value = "/setmeal/create", method = RequestMethod.POST)
    public Object create(JSONObject requestJson){
        JSONObject result = ServiceParamHelper.createSuccessResultJSONObject();
        ZdSetmeal setmeal = new ZdSetmeal();
        setmeal.setPrice(requestJson.getBigDecimal("price"));
        setmeal.setBookDate(requestJson.getString("bookDate"));
        setmeal.setPlace(requestJson.getString("place"));
        setmeal.setDineType(requestJson.getString("dineType"));
        setmeal.setNum(requestJson.getString("num"));
        List<ZdSetmealItem> itemList = JSONArray.parseArray(requestJson.getJSONArray("item").toJSONString(),ZdSetmealItem.class);

        setmealService.create(setmeal, itemList);

        return result;
    }

    @RequestMapping(value = "/setmeal/list", method = RequestMethod.GET)
    public Object list(){
        JSONObject result = ServiceParamHelper.createSuccessResultJSONObject();

        result.put("data", setmealService.list());

        return result;
    }

    @RequestMapping(value = "/setmeal", method = RequestMethod.GET)
    public ModelAndView setmeal(@RequestParam(defaultValue = "1")Integer pageNum, @RequestParam(defaultValue = "5")Integer pageSize, HttpServletRequest request){
        Object user = request.getSession().getAttribute("userLogin");
        ModelAndView model = new ModelAndView();

        if(user == null){
            model.setViewName("login");
        } else {
            //PageHelper.startPage(pageNum, pageSize);
            List<ZdSetmeal> setmeals = setmealService.list();
            //PageInfo<ZdSetmeal> info = new PageInfo<ZdSetmeal>(setmeals);

            model.addObject("setmeals",setmeals);
            //model.addObject("total", info.getPages());
            model.addObject("total", 1);
            model.addObject("pageNum", pageNum);
            model.setViewName("setmeal");
        }

        return model;
    }

}
