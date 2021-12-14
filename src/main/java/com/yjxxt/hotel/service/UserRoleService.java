package com.yjxxt.hotel.service;

import com.yjxxt.hotel.base.BaseService;
import com.yjxxt.hotel.bean.UserRole;
import com.yjxxt.hotel.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserRoleService extends BaseService<UserRole,Integer> {

    @Resource
    private UserRoleMapper userRoleMapper;


}
