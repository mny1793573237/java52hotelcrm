<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <div class="layui-form-item layui-row layui-col-xs12">
        <#--ID-->
        <input type="hidden" name="id" value="${(roomType.id)!}">

        <label class="layui-form-label">房间编号</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input" lay-verify="required"
                   name="roomTypeId" id="roomTypeId"  value="${(roomType.roomTypeId)!}" placeholder="请输入房间编号">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">房间类型</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="roomType"
                   id="roomType" value="${(roomType.roomType)!}" placeholder="请输入房间类型">
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">房间信息</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input"  name="roomRemark"
                   id="roomRemark" value="${(roomType.roomRemark)!}" placeholder="请输入房间信息">
        </div>
    </div>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateRoomType">
                确认
            </button>
            <button class="layui-btn layui-btn-lg layui-btn-normal" id="closeBtn">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/js/roomType/add_update.js"></script>
</body>
</html>