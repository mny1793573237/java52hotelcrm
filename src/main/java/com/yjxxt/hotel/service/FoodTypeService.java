package com.yjxxt.hotel.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.hotel.base.BaseService;
import com.yjxxt.hotel.bean.FoodType;
import com.yjxxt.hotel.bean.RoomType;
import com.yjxxt.hotel.mapper.FoodTypeMapper;
import com.yjxxt.hotel.query.FoodTypeQuery;
import com.yjxxt.hotel.query.RoomTypeQuery;
import com.yjxxt.hotel.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class FoodTypeService extends BaseService<FoodType,Integer> {
    @Resource
    private FoodTypeMapper foodTypeMapper;
    public Map<String,Object> queryFoodTypeByParams(FoodTypeQuery foodTypeQuery){
        //实例
        Map<String,Object> map=new HashMap<String,Object>();
        //实例化分页单位
        PageHelper.startPage(foodTypeQuery.getPage(),foodTypeQuery.getLimit());
        //开始分页
        PageInfo<FoodType> plist=new PageInfo<FoodType>(selectByParams(foodTypeQuery));
        //准备数据
        map.put("code",0);
        map.put("msg","success");
        map.put("count",plist.getTotal());
        map.put("data",plist.getList());
        //返回map
        return map;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void addFoodType(FoodType foodType){
        //验证
        checkFoodTypeParam(foodType.getFoodTypeName(),foodType.getFoodIntro(),foodType.getIsHave());
        foodType.setCreateTime(new Date());
        foodType.setUpdateTime(new Date());
        foodType.setIsValid(1);
        //createDate,updateDate,分配时间
        //添加是否成功
        AssertUtil.isTrue(insertSelective(foodType)<1,"添加失败了");
    }
    private void checkFoodTypeParam(String foodTypeName, String foodIntro, String isHave) {
        AssertUtil.isTrue(StringUtils.isBlank(foodTypeName),"请输入餐饮类型名字");
        AssertUtil.isTrue(StringUtils.isBlank(foodIntro),"请输入食物功效");
        AssertUtil.isTrue(StringUtils.isBlank(isHave),"请输入是否有");
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void changeFoodType(FoodType foodType){
        //验证
        FoodType temp = selectByPrimaryKey(foodType.getId());
        AssertUtil.isTrue(temp==null,"待修改的记录不存在");
        checkFoodTypeParam(foodType.getFoodTypeName(),foodType.getFoodIntro(),foodType.getIsHave());
        //设定默认值
        foodType.setUpdateTime(new Date());
        //修改是否成功
        AssertUtil.isTrue(updateByPrimaryKeySelective(foodType)<1,"修改失败了");
    }

    //批量删除
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeFoodTypeIds(Integer [] ids){
        //验证
        AssertUtil.isTrue(ids==null || ids.length==0,"请选择要删除的数据");
        //删除是否成功
        AssertUtil.isTrue(deleteBatch(ids)!=ids.length,"批量删除失败了");
    }
}
