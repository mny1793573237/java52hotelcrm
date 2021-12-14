<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
    <style type="text/css">
        .layui-upload-img{width: 92px;height: 92px;}
    </style>
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <div class="layui-form-item layui-row layui-col-xs12">
        <#--营销机会的ID-->
        <input type="hidden" name="id" value="${(roomDetails.id)!}">
        <#--营销机会的分配人id-->
<#--        <input type="hidden" name="man" value="${(saleChance.assignMan)!}">-->
        <label class="layui-form-label">房间号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="roomId"
                   id="roomId" value="${(roomDetails.roomId?c)!}" placeholder="请输入房间号">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">房间大小</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="roomArea"
                   lay-verify="required"  value="${(roomDetails.roomArea?c)!}" placeholder="请输入房间大小">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">房间类型</label>
        <div class="layui-input-block">
            <select name="roomType" id="roomType">
                <option value="">请选择</option>
            </select>
        </div>
    </div>
    <#--<div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">房间类型</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="roomType"
                   id="roomType" value="${(roomDetails.roomType)!}" placeholder="请输入房间类型">
        </div>
    </div>-->
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">房间介绍</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="roomIntro"
                   lay-verify="required"  value="${(roomDetails.roomIntro)!}" placeholder="请输入房间介绍">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">房间价格</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" name="roomPrice"
                   lay-verify="required"  value="${(roomDetails.roomPrice?c)!}" placeholder="请输入房间价格">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">房间状态</label>
        <div class="layui-input-block">
            <select name="isUsed" id="isUsed">
                <option value="0">可用</option>
                <option value="1">已使用</option>
                <option value="-1">不可用</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12 layui-upload" align="center">
            <button type="button" class="layui-btn" id="test1">上传图片</button>
            <div class="layui-upload-list">
                <img class="layui-upload-img" id="demo1">
                <p id="demoText"></p>
            </div>
            <div style="width: 95px;">
                <div class="layui-progress layui-progress-big" lay-showpercent="yes" lay-filter="demo">
                    <div class="layui-progress-bar" lay-percent=""></div>
                </div>
            </div>

        <a name="list-progress"> </a>

        <div style="margin-top: 10px;">
            <!-- 示例-970 -->
        </div>
    </div>

    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateRoomDetails">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <input type="hidden" name="roomImg" value="">
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/roomDetails/add_update.js"></script>
</body>
</html>