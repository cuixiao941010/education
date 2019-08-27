<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>日志管理</title>
    <link rel="stylesheet" href="${request.contextPath}/layui/css/layui.css">
    <script type="text/javascript" src="${request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${request.contextPath}/js/jquery.cookie.js"></script>
    <style type="text/css">
        .container {
            display: inline-block;
            position: relative;
            margin: 0 5px 0 0;
        }

    </style>
</head>
<body>
<form class="layui-form">

    <div class="container">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名：</label>
            <div class="layui-input-block">
                <input type="text" name="userName" placeholder="请输入用户名" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="container">
        <div class="layui-form-item">
            <label class="layui-form-label">操作类型：</label>
            <div class="layui-input-block">
                <select name="operation" lay-filter="operation">
                    <option value="登录">登录</option>
                    <option value="其他">其他</option>
                </select>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="layui-form-item">
            <label class="layui-form-label">操作时间：</label>
            <div class="layui-input-block">
                <input id="createAt" type="text" name="createAt" class="layui-input">
            </div>
        </div>
    </div>

    <div class="container">
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="*">查询</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </div>
</form>

<table id="tabledemo" lay-filter="test"></table>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="delete">删除</button>
    </div>
</script>

<script src="${request.contextPath}/layui/layui.js"></script>
<script>
        layui.use(['layer', 'table', 'form', 'laydate'], function(){
            var table = layui.table;
            var layer = layui.layer;
            var form = layui.form;
            var laydate = layui.laydate;
            table.render({
                elem: '#tabledemo'
                // ,toolbar: '#toolbarDemo'
                ,height: 312
                ,width: 1000
                ,url: 'http://localhost:8005/education/api/v1/log' //数据接口
                ,headers: {Authorization: $.cookie("token")}
                ,parseData: function (res) {
                    return {
                        "code": 0,
                        "msg": "OK",
                        "count": res.records,
                        "data": res.data
                    };
                }
                ,page: true //开启分页
                ,cols: [[ //表头
                    {field: 'id', title: 'ID', width: '20%', sort: true, fixed: 'left', align:'center'}
                    ,{field: 'userName', title: '用户名', width:'20%', align:'center'}
                    ,{field: 'operation', title: '操作类型', width:'20%', align:'center'}
                    ,{field: 'ip', title: 'IP地址', width:'20%', align:'center'}
                    ,{field: 'createAt', title: '操作时间', width: '20%', align:'center'}
                ]]
            });

            // table.on('tool(test)', function (obj) {
            //     // var checkStatus = table.checkStatus(obj.config.id);
            //     console.log(obj);
            //     switch (obj.event) {
            //         case 'delete':
            //             $.ajax('http://localhost:8005/education/api/v1/user/delete' + '?userId=' + obj.data.id, {
            //                 dataType: 'json',//服务器返回json格式数据
            //                 type: 'put',//HTTP请求类型
            //                 timeout: 12000,//超时时间设置为10秒；
            //                 headers: {'Content-Type': 'application/json','Authorization': $.cookie("token")},
            //                 success: function (data) {
            //                     // layer.msg("删除成功");
            //                     table.reload('tabledemo')
            //                 },
            //                 complete: function() {
            //                     // layer.close(loadIndex);
            //                 },
            //                 error: function (xhr, type, errorThrown) {
            //                     if (xhr.status == 500) {
            //                         layer.msg(xhr.responseJSON.message);
            //                     }
            //                 }
            //             })
            //     };
            //
            // });

            form.on('submit(*)', function(data){
                var param = {};
                if (data.field.userName != "") {
                    param["userName"] = data.field.userName;
                }
                if (data.field.operation != "") {
                    param["operation"] = data.field.operation;
                }
                if (data.field.createAt != "") {
                    param["createAt"] = data.field.createAt;
                }
                table.reload('tabledemo', {
                    where: param
                    ,page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    //重置where参数，避免上次查询的where参数提交
                    ,done: function(res, curr, count){
                        this.where={};
                    }
                });
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            });

            laydate.render({
                elem: '#createAt' //指定元素
            });

        });

</script>
</body>
</html>