package com.yjxxt.hotel.controller;

import com.yjxxt.hotel.base.BaseController;
import com.yjxxt.hotel.base.ResultInfo;
import com.yjxxt.hotel.bean.User;
import com.yjxxt.hotel.model.UserModel;
import com.yjxxt.hotel.query.UserQuery;
import com.yjxxt.hotel.service.UserService;
import com.yjxxt.hotel.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping("login")
    @ResponseBody
    public ResultInfo userLogin (String userName, String userPwd){
        ResultInfo resultInfo = new ResultInfo();
        /* try{*/
        // 调用Service层的登录方法，得到返回的用户对象
        UserModel userModel = userService.userLogin(userName, userPwd);
        resultInfo.setResult(userModel);
        /**
         * 登录成功后，有两种处理：
         * 1. 将用户的登录信息存入 Session （ 问题：重启服务器，Session 失效，客户端
         需要重复登录 ）
         * 2. 将用户信息返回给客户端，由客户端（Cookie）保存
         */
        return resultInfo;
    }

    @PostMapping("updatePwd")
    @ResponseBody
    public ResultInfo updatePwd(HttpServletRequest req, String oldPassword, String newPassword, String confirmPwd) {
        System.out.println(oldPassword);
        System.out.println(newPassword);
        System.out.println(confirmPwd);
        ResultInfo resultInfo = new ResultInfo();
        // 获取userId
        int userId = LoginUserUtil.releaseUserIdFromCookie(req);
        userService.changeUserPwd(userId, oldPassword, newPassword, confirmPwd);
        return resultInfo;
    }

    @RequestMapping("toPasswordPage")
    public String updatePwd() {
        return "user/password";
    }


    @RequestMapping("index")
    public String index() {
        return "user/user";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(UserQuery userQuery) {
        return userService.findUserByParams(userQuery);
    }


    @RequestMapping("addOrUpdatePage")
    public String addOrUpdatePage(Integer id, Model model) {
        if(id!=null){
            User user = userService.selectByPrimaryKey(id);
            model.addAttribute("user",user);
        }
        return "user/add_update";
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultInfo save(User user) {
        ResultInfo resultInfo = new ResultInfo();
        userService.addUser(user);
        //返回目标数据对象
        return success("用户添加成功");
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(User user) {
        ResultInfo resultInfo = new ResultInfo();
        userService.changeUser(user);
        //返回目标数据对象
        return success("用户修改成功");
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer [] ids) {
        ResultInfo resultInfo = new ResultInfo();
        userService.removeUserIds(ids);
        //返回目标数据对象
        return success("批量删除ok");
    }


}
