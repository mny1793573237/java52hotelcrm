<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <div class="layui-form-item layui-row layui-col-xs12">
        <#--ID-->
        <input type="hidden" name="id" value="${(foodType.id)!}">

        <label class="layui-form-label">餐饮类型名字</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="foodTypeName" id="foodTypeName"  value="${(foodType.foodTypeName)!}" placeholder="请输入餐饮类型名字">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">食物功效</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="foodIntro"
                   id="foodIntro" value="${(foodType.foodIntro)!}" placeholder="请输入食物功效">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">是否有</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="isHave"
                   id="isHave" value="${(foodType.isHave)!}" placeholder="请输入是否还有">
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateFoodType">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/foodType/add_update.js"></script>
</body>
</html>