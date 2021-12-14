package com.yjxxt.hotel.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.hotel.base.BaseService;
import com.yjxxt.hotel.bean.RoomDetails;
import com.yjxxt.hotel.bean.RoomType;
import com.yjxxt.hotel.mapper.RoomDetailsMapper;
import com.yjxxt.hotel.query.RoomDetailsQuery;
import com.yjxxt.hotel.query.RoomTypeQuery;
import com.yjxxt.hotel.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomDetailsService extends BaseService<RoomDetails,Integer> {
    @Resource
    private RoomDetailsMapper roomDetailsMapper;

    public Map<String,Object> queryRoomDetailsByParams(RoomDetailsQuery roomDetailsQuery){
        //实例
        Map<String,Object> map=new HashMap<String,Object>();
        //实例化分页单位
        PageHelper.startPage(roomDetailsQuery.getPage(),roomDetailsQuery.getLimit());
        //开始分页
        PageInfo<RoomDetails> plist=new PageInfo<RoomDetails>(selectByParams(roomDetailsQuery));
        //准备数据
        map.put("code",0);
        map.put("msg","success");
        map.put("count",plist.getTotal());
        map.put("data",plist.getList());
        //返回map
        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addRoomDetails(RoomDetails roomDetails){
        AssertUtil.isTrue(roomDetails.getRoomArea()<0,"房间大小有问题");
        AssertUtil.isTrue(roomDetails.getRoomPrice()<0,"房间价格有问题");
        AssertUtil.isTrue(StringUtils.isBlank(roomDetails.getRoomIntro()),"房间介绍有问题");
        AssertUtil.isTrue(StringUtils.isBlank(roomDetails.getRoomType()),"房间类型有问题");
        RoomDetails temp=roomDetailsMapper.selectByRoomId(roomDetails.getRoomId());
        AssertUtil.isTrue(temp!=null,"房间已存在");
        roomDetails.setIsValid(1);
        roomDetails.setRoomImg("/images/"+roomDetails.getRoomImg());
        roomDetails.setCreateTime(new Date());
        roomDetails.setUpdateTime(new Date());
        AssertUtil.isTrue(insertSelective(roomDetails)<1,"添加失败");
    }
    public RoomDetails selectById(Integer id) {
        return roomDetailsMapper.selectByRoomId(id);
    }

    public void changeRoomDetails(RoomDetails roomDetails) {
        AssertUtil.isTrue(roomDetails.getRoomArea()<0,"房间大小有问题");
        AssertUtil.isTrue(roomDetails.getRoomPrice()<0,"房间价格有问题");
        AssertUtil.isTrue(StringUtils.isBlank(roomDetails.getRoomIntro()),"房间介绍有问题");
        AssertUtil.isTrue(StringUtils.isBlank(roomDetails.getRoomType()),"房间类型有问题");
        System.out.println(roomDetails);
        if(StringUtils.isBlank(roomDetails.getRoomImg())){
            RoomDetails temp=roomDetailsMapper.selectByRoomId(roomDetails.getRoomId());
            roomDetails.setRoomImg(temp.getRoomImg());
        }else {
            roomDetails.setRoomImg("/images/"+roomDetails.getRoomImg());
        }
        roomDetails.setUpdateTime(new Date());
        AssertUtil.isTrue(updateByPrimaryKeySelective(roomDetails)<1,"添加失败");
    }
    //批量删除
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeRoomDetailsIds(Integer [] ids){
        //验证
        AssertUtil.isTrue(ids==null || ids.length==0,"请选择要删除的数据");
        //删除是否成功
        AssertUtil.isTrue(deleteBatch(ids)!=ids.length,"批量删除失败了");
    }


}
