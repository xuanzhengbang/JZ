package com.opopto.zdsetmealfood.controller;

import com.opopto.zdsetmealfood.entity.ZdUser;
import com.opopto.zdsetmealfood.service.ZdUserService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@CommonsLog
@ResponseBody
@RequestMapping("user")
public class UserController extends BaseApiController{

    @Autowired
    private ZdUserService userService;

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        log.info("username:" + username + ",password: " + password);
        ZdUser user = userService.login(username, password);
        log.info("查询结果：" + user);
        if(user!=null) {
            session.setAttribute("userLogin", user);
            try {
                response.sendRedirect("/");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "redirect:/list";
        }
        try {
            response.sendRedirect("/login");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    public Object logout(HttpServletRequest request, HttpServletResponse response){
        try {
            request.logout();
            request.getSession().invalidate();
            response.sendRedirect("/login");
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
