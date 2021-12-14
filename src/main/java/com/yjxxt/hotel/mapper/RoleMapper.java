package com.yjxxt.hotel.mapper;

import com.yjxxt.hotel.base.BaseMapper;
import com.yjxxt.hotel.bean.Role;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends BaseMapper<Role,Integer> {
    @MapKey("")
    List<Map<String, Object>> selectRoles(Integer userId);
}