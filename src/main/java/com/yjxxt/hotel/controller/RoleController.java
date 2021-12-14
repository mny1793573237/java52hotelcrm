package com.yjxxt.hotel.controller;

import com.yjxxt.hotel.base.BaseController;
import com.yjxxt.hotel.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("findRoles")
    @ResponseBody
    public List<Map<String, Object>> sayRoles(Integer userId) {

        return roleService.findRoles(userId);
    }
}