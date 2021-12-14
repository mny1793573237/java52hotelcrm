package com.yjxxt.hotel.mapper;

import com.yjxxt.hotel.base.BaseMapper;
import com.yjxxt.hotel.bean.RoomType;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface RoomTypeMapper extends BaseMapper<RoomType,Integer> {
    @MapKey("")
    List<Map<String, Object>> selectRoomTypes();
}