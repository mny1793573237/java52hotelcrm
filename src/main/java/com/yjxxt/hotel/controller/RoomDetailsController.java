package com.yjxxt.hotel.controller;

import com.yjxxt.hotel.base.BaseController;
import com.yjxxt.hotel.base.ResultInfo;
import com.yjxxt.hotel.bean.RoomDetails;
import com.yjxxt.hotel.bean.RoomType;
import com.yjxxt.hotel.query.RoomDetailsQuery;
import com.yjxxt.hotel.query.RoomTypeQuery;
import com.yjxxt.hotel.service.RoomDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("room_details")
public class RoomDetailsController extends BaseController {
    @Autowired
    private RoomDetailsService roomDetailsService;
    @RequestMapping("index")
    public String index(){
        return  "roomDetails/room_details";
    }
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> sayList(RoomDetailsQuery roomDetailsQuery){
        //调用方法获取数据
        Map<String, Object> map = roomDetailsService.queryRoomDetailsByParams(roomDetailsQuery);
        //map--->json
        //返回目标map
        return map;
    }
    @RequestMapping("addOrUpdateDialog")
    public String addOrUpdate(Integer id, Model model){
        System.out.println(id);
        //判断
        if(id!=null){
            //查询用户信息
            RoomDetails roomDetails = roomDetailsService.selectByPrimaryKey(id);

            System.out.println(roomDetails);
            //存储
            model.addAttribute("roomDetails",roomDetails);
        }
        return "roomDetails/add_update";
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultInfo sayList(RoomDetails roomDetails){
        System.out.println(roomDetails);
        roomDetailsService.addRoomDetails(roomDetails);
        //调用方法获取数据
        return success("添加成功");
    }
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(RoomDetails roomDetails){
        //添加操作
        roomDetailsService.changeRoomDetails(roomDetails);
        //返回目标对象
        return success("修改成功了");
    }
    @RequestMapping("dels")
    @ResponseBody
    public ResultInfo deletes(Integer[] ids){
        //添加操作
        roomDetailsService.removeRoomDetailsIds(ids);
        //返回目标对象
        return success("删除成功了");
    }



}
