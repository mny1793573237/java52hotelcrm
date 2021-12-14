package com.yjxxt.hotel.mapper;

import com.yjxxt.hotel.base.BaseMapper;
import com.yjxxt.hotel.bean.UserRole;

public interface UserRoleMapper extends BaseMapper<UserRole,Integer> {
    int countUserRoleNum(Integer userId);

    int deleteUserRoleByUserId(Integer userId);
}