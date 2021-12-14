package com.yjxxt.hotel.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.hotel.base.BaseService;
import com.yjxxt.hotel.bean.RoomType;
import com.yjxxt.hotel.mapper.RoomTypeMapper;
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
public class RoomTypeService extends BaseService<RoomType,Integer> {
    @Resource
    private RoomTypeMapper roomTypeMapper;

    public Map<String,Object> queryRoomTypeByParams(RoomTypeQuery roomTypeQuery){
        //实例
        Map<String,Object> map=new HashMap<String,Object>();
        //实例化分页单位
        PageHelper.startPage(roomTypeQuery.getPage(),roomTypeQuery.getLimit());
        //开始分页
        PageInfo<RoomType> plist=new PageInfo<RoomType>(selectByParams(roomTypeQuery));
        //准备数据
        map.put("code",0);
        map.put("msg","success");
        map.put("count",plist.getTotal());
        map.put("data",plist.getList());
        //返回map
        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void addRoomType(RoomType roomType){
        //验证
        checkRoomTypeParam(roomType.getRoomTypeId(),roomType.getRoomType(),roomType.getRoomRemark());
        roomType.setCreateTime(new Date());
        roomType.setUpdateTime(new Date());
        roomType.setIsValid(1);
        //createDate,updateDate,分配时间
        //添加是否成功
        AssertUtil.isTrue(insertSelective(roomType)<1,"添加失败了");
    }
    private void checkRoomTypeParam(String roomTypeId, String roomType, String roomRemark) {
        AssertUtil.isTrue(StringUtils.isBlank(roomTypeId),"请输入房间编号");
        AssertUtil.isTrue(StringUtils.isBlank(roomType),"请输入房间类型");
        AssertUtil.isTrue(StringUtils.isBlank(roomRemark),"请输入房间信息");
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void changeRoomType(RoomType roomType){
        //验证
        RoomType temp = selectByPrimaryKey(roomType.getId());
        AssertUtil.isTrue(temp==null,"待修改的记录不存在");
        checkRoomTypeParam(roomType.getRoomTypeId(),roomType.getRoomType(),roomType.getRoomRemark());

        //设定默认值
        roomType.setUpdateTime(new Date());
        //修改是否成功
        AssertUtil.isTrue(updateByPrimaryKeySelective(roomType)<1,"修改失败了");
    }
    //批量删除
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeRoomTypeIds(Integer [] ids){
        //验证
        AssertUtil.isTrue(ids==null || ids.length==0,"请选择要删除的数据");
        //删除是否成功
        AssertUtil.isTrue(deleteBatch(ids)!=ids.length,"批量删除失败了");
    }
    public List<Map<String, Object>> queryRoomTypes() {
        return roomTypeMapper.selectRoomTypes();
    }
}
