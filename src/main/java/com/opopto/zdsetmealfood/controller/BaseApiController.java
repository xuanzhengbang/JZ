package com.opopto.zdsetmealfood.controller;

import com.alibaba.fastjson.JSONException;
import com.opopto.zdsetmealfood.exception.ServiceException;
import com.opopto.zdsetmealfood.helper.ServiceParamHelper;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

@CommonsLog
@ResponseBody
public class BaseApiController {

    @ExceptionHandler(ServiceException.class)
    public Object handle(ServiceException e) {
        log.error("发生异常，错误信息 : "+ e.getMessage());
        String errMsg = e.getMessage();
        if(errMsg.contains("errCode")) {
            return errMsg;
        } else {
            return   ServiceParamHelper.createResultJSONObject(102, e.getMessage());
        }
    }

    @ExceptionHandler(JSONException.class)
    public Object handle(JSONException e) {
        log.error("请求的Json格式参数有误 : " + e.getMessage());
        return ServiceParamHelper.createFailResultJSONObject();
    }

    @ExceptionHandler(NullPointerException.class)
    public Object handle(NullPointerException e) {
        log.error("空指针异常 : " + e.getMessage());
        return ServiceParamHelper.createFailResultJSONObject();
    }

    @ExceptionHandler(SQLException.class)
    public Object handle(SQLException e) {
        log.error("SQL执行出錯 : " + e.getMessage());
        return ServiceParamHelper.createFailResultJSONObject();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Object handle(IllegalArgumentException e) {
        log.error("非法参数异常 : " + e.getMessage());
        return ServiceParamHelper.createFailResultJSONObject();
    }

    @ExceptionHandler(RuntimeException.class)
    public Object handle(RuntimeException e) {
        log.error("未知的运行时错误 : " + e.getMessage());
        return ServiceParamHelper.createFailResultJSONObject();
    }

    @ExceptionHandler(Exception.class)
    public Object handle(Exception e) {
        log.error("发生异常，错误信息 : "+ e.getMessage());
        return ServiceParamHelper.createFailResultJSONObject();
    }

}
