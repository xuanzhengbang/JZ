package com.opopto.zdsetmealfood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class KeepalivedContorller {

    @RequestMapping(value = "/keepalived")
    public Object alived(){
        return "ok";
    }

}
