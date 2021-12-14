package com.yjxxt.hotel.mapper;

import com.yjxxt.hotel.base.BaseMapper;
import com.yjxxt.hotel.bean.User;

public interface UserMapper extends BaseMapper<User,Integer> {

    User selectUserByName(String userName);
}