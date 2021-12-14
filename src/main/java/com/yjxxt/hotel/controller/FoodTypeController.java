package com.yjxxt.hotel.controller;

import com.yjxxt.hotel.base.BaseController;
import com.yjxxt.hotel.base.ResultInfo;
import com.yjxxt.hotel.bean.FoodType;
import com.yjxxt.hotel.bean.RoomType;
import com.yjxxt.hotel.query.FoodTypeQuery;
import com.yjxxt.hotel.query.RoomTypeQuery;
import com.yjxxt.hotel.service.FoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("food_type")
public class FoodTypeController extends BaseController {
    @Autowired
    private FoodTypeService foodTypeService;


    @RequestMapping("index")
    public String index(){
        return  "foodType/food_type";
    }
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> sayList(FoodTypeQuery foodTypeQuery){
        //调用方法获取数据
        Map<String, Object> map = foodTypeService.queryFoodTypeByParams(foodTypeQuery);
        //map--->json
        //返回目标map
        return map;
    }
    @RequestMapping("addOrUpdateDialog")
    public String addOrUpdate(Integer id, Model model){
        //判断
        if(id!=null){
            //查询用户信息
            FoodType foodType = foodTypeService.selectByPrimaryKey(id);
            //存储
            model.addAttribute("foodType",foodType);
        }
        return "foodType/add_update";
    }

    @RequestMapping("save")
    @ResponseBody
    public ResultInfo save(HttpServletRequest req, FoodType foodType){
        foodTypeService.addFoodType(foodType);
        return success("添加成功了");
    }
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(FoodType foodType){
        //添加操作
        foodTypeService.changeFoodType(foodType);
        //返回目标对象
        return success("修改成功了");
    }
    @RequestMapping("dels")
    @ResponseBody
    public ResultInfo deletes(Integer[] ids){
        //添加操作
        foodTypeService.removeFoodTypeIds(ids);
        //返回目标对象
        return success("批量删除成功了");
    }

}
