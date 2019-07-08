<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui.form小例子</title>
    <link rel="stylesheet" href="${request.contextPath}/layui/css/layui.css" media="all">
    <script type="text/javascript" src="${request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${request.contextPath}/js/jquery.cookie.js"></script>
</head>
<body>
<div class="layui-container">
    <div class="layui-row">
        <div class="layui-col-xs6 layui-col-sm6 layui-col-md9">
            一个白色背景
        </div>
        <div class="layui-col-xs6 layui-col-sm6 layui-col-md3">
            <div class="layui-form"> <!-- 提示：如果你不想用form，你可以换成div等任何一个普通元素 -->
                <div class="layui-form-item">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-block">
                        <input type="text" name="username" placeholder="请输入用户名" autocomplete="off" class="layui-input" lay-verify="required">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">密码</label>
                    <div class="layui-input-block">
                        <input type="password" name="password" placeholder="请输入"密码 autocomplete="off" class="layui-input" lay-verify="required">
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit="" lay-filter="login">登录</button>
                        <#--<button type="reset" class="layui-btn layui-btn-primary">重置</button>-->
                    </div>
                </div>
                <!-- 更多表单结构排版请移步文档左侧【页面元素-表单】一项阅览 -->
            </div>
        </div>
    </div>
</div>
<script src="${request.contextPath}/layui/layui.js"></script>
<script>
    layui.use(['form','layer'], function(){
        var form = layui.form;
        var layer = layui.layer;
        form.on('submit(login)', function(data){
            var param = {
                userName: data.field.username,
                password: data.field.password
            };
            $.ajax('http://localhost:8005/education/api/v1/user/login', {
                dataType: 'json',//服务器返回json格式数据
                type: 'post',//HTTP请求类型
                timeout: 12000,//超时时间设置为10秒；
                data: JSON.stringify(param),
                headers: {'Content-Type': 'application/json'},
                success: function (data) {
                    // console.log(data);
                    $.cookie("token", data.authorization);
                    window.location.href = 'demo';
                },
                error: function (xhr, type, errorThrown) {
                    layer.msg("登录失败");
                }
            })
        });
    });
</script>
</body>
</html>