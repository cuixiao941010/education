<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>开始使用layui</title>
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
            <label class="layui-form-label">学历</label>
            <div class="layui-input-block" style="width: 80px">
                <select name="education" lay-filter="education">
                    <option value=""></option>
                    <option value="1">一本</option>
                    <option value="2">二本</option>
                </select>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="layui-form-item">
            <label class="layui-form-label">学历</label>
            <div class="layui-input-block" style="width: 80px">
                <select name="subject" lay-filter="subject">
                    <option value=""></option>
                    <option value="1">理科</option>
                    <option value="2">文科</option>
                </select>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="layui-form-item">
            <label class="layui-form-label">学校名称</label>
            <div class="layui-input-block">
                <input type="text" name="schoolName" placeholder="请输入" autocomplete="off" class="layui-input">
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
        <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
        <button class="layui-btn layui-btn-sm" lay-event="delete">删除</button>
        <button class="layui-btn layui-btn-sm" lay-event="update">编辑</button>
    </div>
</script>

<script src="${request.contextPath}/layui/layui.js"></script>
<script>
    var educationEnum = {1:"一本",2:"二本"};
    var subjectEnum = {1:"理科",2:"文科"};
        layui.use(['layer', 'table', 'form'], function(){
            var table = layui.table;
            var layer = layui.layer;
            var form = layui.form;
            table.render({
                elem: '#tabledemo'
                ,toolbar: '#toolbarDemo'
                ,height: 312
                ,width: 1000
                ,url: 'http://localhost:8005/education/api/v1/university' //数据接口
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
                    {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
                    ,{field: 'schoolNo', title: '学校编号', width:80}
                    ,{field: 'schoolName', title: '学校名称', width:150}
                    ,{field: 'planNumber', title: '计划数', width:80, sort: true}
                    ,{field: 'lowScore', title: '最低分数', width: 80, sort: true}
                    ,{field: 'submitNumber', title: '提交数', width: 80, sort: true}
                    ,{field: 'signNumber', title: '签名数', width: 80, sort: true}
                    ,{field: 'education', title: '学历', width: 80, templet: function (d) {
                            return educationEnum[d.education];
                        }}
                    ,{field: 'artsSciences', title: '学科', width: 80, templet: function (d) {
                            return subjectEnum[d.artsSciences];
                        }}
                    ,{field: 'examAt', title: '时间', width: 135, sort: true}
                ]]
            });

            table.on('toolbar(test)', function (obj) {
                // var checkStatus = table.checkStatus(obj.config.id);
                switch (obj.event) {
                    case 'add':
                        layer.msg('添加');
                        break;
                    case 'delete':
                        layer.msg('删除');
                        break;
                    case 'update':
                        layer.msg('编辑');
                        break;
                };

            });

            form.on('submit(*)', function(data){
                var param = {};
                if (data.field.education != "") {
                    param["education"] = data.field.education;
                }
                if (data.field.subject != "") {
                    param["artsSciences"] = data.field.subject;
                }
                if (data.field.schoolName != "") {
                    param["schoolName"] = data.field.schoolName;
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

        });

</script>
</body>
</html>