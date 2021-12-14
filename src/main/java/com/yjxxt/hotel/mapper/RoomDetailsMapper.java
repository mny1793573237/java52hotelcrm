package com.yjxxt.hotel.mapper;

import com.yjxxt.hotel.base.BaseMapper;
import com.yjxxt.hotel.bean.RoomDetails;

import java.util.List;
import java.util.Map;

public interface RoomDetailsMapper extends BaseMapper<RoomDetails,Integer> {

    RoomDetails selectByRoomId(Integer roomId);



}