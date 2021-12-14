package com.yjxxt.hotel;


import com.alibaba.fastjson.JSON;
import com.yjxxt.hotel.base.ResultInfo;
import com.yjxxt.hotel.exceptions.NoLoginException;
import com.yjxxt.hotel.exceptions.ParamsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception ex) {


        if(ex instanceof NoLoginException){
            ModelAndView mav=new ModelAndView("redirect:/index");
            return  mav;
        }
        //实例化对象
        ModelAndView mav = new ModelAndView("error");
        //存储数据
        mav.addObject("code", 400);
        mav.addObject("msg", "系统异常，请稍后再试...");
        //判断
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            ResponseBody responseBody = handlerMethod.getMethod().getDeclaredAnnotation(ResponseBody.class);
            if(responseBody == null){
                //返回视图
                if(ex instanceof ParamsException) {
                    ParamsException pe = (ParamsException) ex;
                    mav.addObject("code", pe.getCode());
                    mav.addObject("msg", pe.getMsg());
                    return mav;

                }
            }else{
                //返回json
                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setCode(300);
                resultInfo.setMsg("系统异常，请重试！");

                //如果捕捉的是自定义异常
                if(ex instanceof ParamsException){
                    ParamsException pe = (ParamsException) ex;
                    resultInfo.setCode(pe.getCode());
                    resultInfo.setMsg(pe.getMsg());
                }
                // 设置响应类型和编码格式 （响应JSON格式）
                resp.setContentType("application/json;charset=utf-8");
                // 得到输出流
                PrintWriter pw = null;
                try {
                    pw = resp.getWriter();
                    pw.write(JSON.toJSONString(resultInfo));
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(pw!=null){
                        pw.close();
                    }
                }
                return null;
            }

        }
        return mav;


    }
}
