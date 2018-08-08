package com.opopto.zdsetmealfood.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.opopto.zdsetmealfood.entity.*;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@CommonsLog
@ResponseBody
public class SetmealController extends BaseApiController {

    @Autowired
    private SetmealService setmealService;

    @RequestMapping(value = "/setmeal/create", method = RequestMethod.POST)
    public Object create(String bookDate, String place, String dineType, Integer num, Integer price, String[] foods){
        JSONObject result = ServiceParamHelper.createSuccessResultJSONObject();
        ZdSetmeal setmeal = new ZdSetmeal();
        setmeal.setPrice(BigDecimal.valueOf(price));
        setmeal.setBookDate(bookDate);
        setmeal.setPlace(place);
        setmeal.setDineType(dineType);
        setmeal.setNum(num+"");
        List<ZdSetmealItem> itemList = new ArrayList<>();
        for(String s : foods){
            ZdSetmealItem item = new ZdSetmealItem();
            item.setFoodName(s);
            item.setCreateTime(new Date());
            itemList.add(item);
        }

        setmealService.create(setmeal, itemList);

        return result;
    }

    @RequestMapping(value = "/setmeal/list", method = RequestMethod.GET)
    public Object list(){
        JSONObject result = ServiceParamHelper.createSuccessResultJSONObject();

        List<ZdSetmeal> setmeals = setmealService.list();
        result.put("data", setmeals);
        result.put("total", setmeals.size());

        return result;
    }

    @RequestMapping(value = "/setmeal", method = RequestMethod.GET)
    public ModelAndView setmeal(@RequestParam(defaultValue = "1")Integer pageNum, @RequestParam(defaultValue = "5")Integer pageSize, HttpServletRequest request){
        Object user = request.getSession().getAttribute("userLogin");
        ModelAndView model = new ModelAndView();

        if(user == null){
            model.setViewName("login");
        } else {
            PageHelper.startPage(pageNum, pageSize);
            List<ZdSetmeal> setmeals = setmealService.list();
            PageInfo<ZdSetmeal> info = new PageInfo<ZdSetmeal>(setmeals);
            List<ZdSetmeal> setmealList = new ArrayList<>();
            for(int i=0; i<setmeals.size();i++){
                ZdSetmeal temp = setmeals.get(i);
                temp.setItems(setmealService.listItemByCode(temp.getCode()));
                setmealList.add(temp);
            }
            model.addObject("setmeals",setmealList);
            model.addObject("total", info.getPages());
            model.addObject("pageNum", pageNum);
            model.setViewName("setmeal");
        }

        return model;
    }

}
