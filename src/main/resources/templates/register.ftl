<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="0">
    <title>register</title>
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/layui/css/layui.css" />
</head>

<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space10">
        <div class="layui-col-md4">

        </div>
        <div class="layui-col-md4">
            <form action="" class="layui-form">
                <div class="layui-row" style="margin-top: 15%">
                    <div class="layui-form-item">
                        <div class="layui-col-md4" align="center">
                            <#--<label class="layui-form-label">用户名：</label>-->
                        </div>
                        <div class="layui-col-md8">
                            <input id="username" type="text" name="username" class="layui-input" placeholder="输入用户名" lay-verify="required">
                        </div>
                    </div>
                </div>
                <div class="layui-row">
                    <div class="layui-form-item">
                        <div class="layui-col-md4" align="center">
                            <#--<label class="layui-form-label">密码：</label>-->
                        </div>
                        <div class="layui-col-md8">
                            <input id="password" type="password"  name="password" class="layui-input" placeholder="输入密码" lay-verify="required">
                        </div>
                    </div>
                </div>
                <div class="layui-row">
                <div class="layui-form-item">
                <#--<div class="layui-col-md4" align="center">-->
                <#--</div>-->
                    <div class="layui-col-md8">
                        <input id="email" type="text"  name="email" class="layui-input" placeholder="输入电子邮箱" lay-verify="email">
                    </div>
                </div>
                </div>
                <div class="layui-row">
                    <div class="layui-form-item">
                    <#--<div class="layui-col-md4" align="center">-->
                    <#--</div>-->
                        <div class="layui-col-md8">
                            <input id="mobile" type="text"  name="mobile" class="layui-input" placeholder="输入联系方式" lay-verify="required|phone|number">
                        </div>
                    </div>
                </div>
                <div class="layui-row" style="text-align: center">
                    <div class="layui-col-md8 layui-col-md-offset4">
                        <a class="layui-btn" id="register" data-type="reload" style="width: 100%;background-color: #ee7700;color: white;" lay-submit lay-filter="register">注册</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="${request.contextPath}/js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${request.contextPath}/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script>
    layui.use(['form','layer'], function(){
        var form = layui.form;
        var layer = layui.layer;
        form.on('submit(register)', function(data){
            var param = {
                userName: data.field.username,
                password: data.field.password,
                email: data.field.email,
                mobile: data.field.mobile
            };
            $.ajax('http://localhost:8005/education/api/v1/user/register', {
                dataType: 'json',//服务器返回json格式数据
                type: 'post',//HTTP请求类型
                timeout: 12000,//超时时间设置为10秒；
                data: JSON.stringify(param),
                headers: {'Content-Type': 'application/json'},
                success: function (data) {
                    console.log("注册成功");
                    layer.msg("注册成功");
                    setTimeout(function () {
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    },1000)
                },
                complete: function() {
                    // layer.close(loadIndex);
                },
                error: function (xhr, type, errorThrown) {
                    console.log("注册失败");
                    if (xhr.status == 500) {
                        layer.msg(xhr.responseJSON.message);
                    }
                }
            });
        });
    });
</script>
</body>

</html>
