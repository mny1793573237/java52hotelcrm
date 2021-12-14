package com.yjxxt.hotel.controller;

import com.yjxxt.hotel.base.BaseController;
import com.yjxxt.hotel.base.ResultInfo;
import com.yjxxt.hotel.bean.RoomType;
import com.yjxxt.hotel.bean.User;
import com.yjxxt.hotel.query.RoomTypeQuery;
import com.yjxxt.hotel.service.RoomTypeService;
import com.yjxxt.hotel.service.UserService;
import com.yjxxt.hotel.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("room_type")
public class RoomTypeController extends BaseController {
    @Autowired
    private RoomTypeService roomTypeService;
    @Autowired
    private UserService userService;

    @RequestMapping("index")
    public String index(){
        return  "roomType/room_type";
    }
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> sayList(RoomTypeQuery roomTypeQuery){
        //调用方法获取数据
        Map<String, Object> map = roomTypeService.queryRoomTypeByParams(roomTypeQuery);
        //map--->json
        //返回目标map
        return map;
    }
    @RequestMapping("addOrUpdateDialog")
    public String addOrUpdate(Integer id, Model model){
        //判断
        if(id!=null){
            //查询用户信息
            RoomType roomType = roomTypeService.selectByPrimaryKey(id);
            //存储
            model.addAttribute("roomType",roomType);
        }
        return "roomType/add_update";
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultInfo save(HttpServletRequest req, RoomType roomType){
        roomTypeService.addRoomType(roomType);
        return success("添加成功了");
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(RoomType roomType){
        //添加操作
        roomTypeService.changeRoomType(roomType);
        //返回目标对象
        return success("修改成功了");
    }
    @RequestMapping("dels")
    @ResponseBody
    public ResultInfo deletes(Integer[] ids){
        //添加操作
        roomTypeService.removeRoomTypeIds(ids);
        //返回目标对象
        return success("批量删除成功了");
    }
    @RequestMapping("roomTypes")
    @ResponseBody
    public List<Map<String, Object>> roomTypes(){
        //添加操作
        return roomTypeService.queryRoomTypes();
    }

}
