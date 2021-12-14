package com.yjxxt.hotel.controller;

import com.yjxxt.hotel.base.BaseController;

import com.yjxxt.hotel.bean.User;
import com.yjxxt.hotel.service.UserService;
import com.yjxxt.hotel.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class IndexController extends BaseController {


    @Autowired
    private UserService userService;


    /**
     * 登录页面
     *
     * @return
     */
    @RequestMapping("index")
    public String index() {
        return "index";
    }



    /**
     * 后台资源页面
     *
     * @return
     */
    @RequestMapping("main")
    public String main(HttpServletRequest req) {
        //获取当前用户的信息
        int userId = LoginUserUtil.releaseUserIdFromCookie(req);
        //根据用户的id查询用户信息
        User user = userService.selectByPrimaryKey(userId);
        //存储
        req.setAttribute("user",user);
        //转发
        return "main";
    }


    /**
     * 欢迎页面
     *
     * @return
     */

    @RequestMapping("welcome")
    public String welcome() {
        return "welcome";
    }

}
