layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    /**
     * 营销机会列表展示
     */
    var  tableIns = table.render({
        elem: '#roomDetailsList', // 表格绑定的ID
        url : ctx + '/room_details/list', // 访问数据的地址
        cellMinWidth : 95,
        page : true, // 开启分页
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "roomDetailsList",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'编号',fixed:"true"},
            {field: 'roomId', title: '房间号',align:"center"},
            {field: 'roomArea', title: '房间大小',  align:'center'},
            {field: 'roomType', title: '房间类型',  align:'center'},
            {field: 'roomIntro', title: '房间介绍', align:'center'},
            {field: 'roomPrice', title: '房间价格', align:'center'},
            {field: 'isUsed', title: '房间状态', align:'center',templet:function(d){
                    return formatterState(d.isUsed);
                }},
            // , {field: 'roomImg', title: '预览图片', align: 'center',sort:false,
            //     templet:function (d) {
            //         return '<div onclick="photograph(this)"><img src='+ctx+d.roomImg+'></div>'
            //     }},

            {field: 'roomImg', title: '附件', align: 'center',sort:false,
                templet:function (d) {
                    if(d.roomImg == ''||d.roomImg==null){
                        return '';
                    }else {
                        return '<div style=\'color:#00F\' onclick=\'photograph(this)\'><a href =\'#\'> <div style=\'color:#009668\'>查看</div></a>><img src=' + d.roomImg + '></div>'
                    }
                }},



            {title: '操作', templet:'#roomDetailsListBar',fixed:"right",align:"center", minWidth:150}
        ]]
    });

    function formatterImg(roomImg){
        if(roomImg == ''||roomImg==null){
            return '';
        }else {
            return '<div style="color:#0000ff" name="showImg"><a href =""#"> <div style="color:#009668">查看</div></a><img src='+ ctx+ roomImg + '></div>'
        }
    }

    /**
     * onclick="photograph(this)
     * 格式化分配状态
     *  0 - 未分配
     *  1 - 已分配
     *  其他 - 未知
     * @param state
     * @returns {string}
     */
    function formatterState(state){
        if(state==0) {
            return "<div style='color: black'>未使用</div>";
        } else if(state==1) {
            return "<div style='color: green'>已使用</div>";
        } else {
            return "<div style='color: red'>未知</div>";
        }
    }





    /*实现搜索功能，页面重载*/
    $(".search_btn").click(function(){
        //这里以搜索为例
        // tableIns.reload({
        //     where: { //设定异步数据接口的额外参数，任意设
        //         customerName: $("input[name=customerName]").val(),
        //         createMan:$("input[name=createMan]").val(),
        //         state:$("#state").val()
        //     }
        //     ,page: {
        //         curr: 1 //重新从第 1 页开始
        //     }
        // });

        table.reload("roomDetailsList",{
            where: { //设定异步数据接口的额外参数，任意设
                roomType: $("input[name=roomType]").val(),
                roomIntro:$("input[name=roomIntro]").val(),
                roomArea:$("input[name=roomArea]").val()

            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    });


    /*绑定头部工具栏*/
    //触发事件
    table.on('toolbar(roomDetailss)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        console.log(checkStatus);
        console.log(checkStatus.data);
        switch(obj.event){
            case 'add':
                //layer.msg('添加');
                addOrUpdateRoomDetailsDialog();
                break;
            case 'del':
                //layer.msg('删除');
                deleteRoomDetails(checkStatus.data);
                break;
        };
    });

    function  deleteRoomDetails(data){
        //判断
        if(data.length == 0){
            layer.msg("请选择删除的数据");
            return ;
        }



        //console.log(ids.toString());

        layer.confirm("你确定要删除这些数据吗?",{
            btn:["确认","取消"],
        },function(index){
            //关闭询问框
            layer.close(index);
            //收集数据
            //收集数据
            var ids=[];
            //遍历
            for(var x in data){
                ids.push(data[x].id);
            }
            //发送请求删除数据
            //发送ajax删除
            $.ajax({
                type:"post",
                url:ctx+"/room_details/dels",
                data:{"ids":ids.toString()},
                dataType:"json",
                success:function(result){
                    if(result.code==200){
                        layer.msg("删除OK",{icon : 5 });
                        //重新加载一下数据
                        tableIns.reload();
                    }else{
                        //提示
                        layer.msg(result.msg);
                    }
                }
            });
        });



    }

    /**
     * 添加修改函数
     * @param roomDetailsId
     */
    function  addOrUpdateRoomDetailsDialog(id){
        var title="<h3>客房详情-添加</h3>";
        var url=ctx+"/room_details/addOrUpdateDialog";

        //判断 非空即为true
        if(id){
            url=url+"?id="+id;
        }
        /*弹出层*/
        layui.layer.open({
            title:title,
            content:url,
            type:2,//ifream
            area:["500px","640px"],
            maxmin:true
        })
    }
    /*绑定行内工具栏*/
    //工具条事件
    table.on('tool(roomDetailss)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        console.log(data.id+"--->"+data);
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

        if(layEvent === 'del'){ //删除
            layer.confirm("你确定要删除这些数据吗?",{
                btn:["确认","取消"],
            },function(index){
                //关闭询问框
                layer.close(index);
                //发送ajax删除
                $.ajax({
                    type:"post",
                    url:ctx+"/room_details/dels",
                    data:{"ids":data.id},
                    dataType:"json",
                    success:function(result){
                        if(result.code==200){
                            layer.msg("删除OK",{icon : 1});
                            //重新加载一下数据
                            tableIns.reload();
                        }else{
                            //提示
                            layer.msg(result.msg,{icon : 1});
                        }
                    }
                });
            });
        } else if(layEvent === 'edit'){ //编辑
            addOrUpdateRoomDetailsDialog(data.id);
        }
    });
});

