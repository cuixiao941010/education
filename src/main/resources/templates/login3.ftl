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
    <title>login</title>
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/layui/css/layui.css" />
</head>

<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space10" style="height:100px; background-image: url(${request.contextPath}/img/headbk.jpg)">
        <div style="text-align: center">
            <span style="font-size: 30px; margin-top: 15%">XX系统</span>
        </div>
    </div>
    <div class="layui-row layui-col-space10" style="height: 500px; background-image: url(${request.contextPath}/img/loginbk3.jpg)">
        <div class="layui-col-md7">

        </div>
        <div class="layui-col-md3">
            <form action="" class="layui-form">
                <div class="layui-row" style="margin-top: 30%">
                    <div class="layui-form-item">
                        <div class="layui-col-md4" align="center">
                            <#--<label class="layui-form-label">用户名：</label>-->
                           <img src="${request.contextPath}/img/logNametou.png" width="34" height="34"/>
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
                            <img src="${request.contextPath}/img/pwdtou.png" width="34" height="34"/>
                        </div>
                        <div class="layui-col-md8">
                            <input id="password" type="password"  name="password" class="layui-input" placeholder="输入密码" lay-verify="required">
                        </div>
                    </div>
                </div>
                <div class="layui-row" style="text-align: center">
                    <div class="layui-col-md8 layui-col-md-offset4">
                        <a class="layui-btn" id="login" data-type="reload" style="width: 100%;background-color: #ee7700;color: white;" lay-submit lay-filter="login">登录</a>
                    </div>
                </div>
                <div class="layui-row" style="text-align: center; margin-top:10px">
                    <div class="layui-col-md8 layui-col-md-offset4">
                        <a class="layui-btn" id="register" data-type="reload" style="width: 100%;background-color: #ee7700;color: white;" lay-btn lay-filter="register" onclick="register()">注册</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="layui-row layui-col-space10" style="height: 95px; text-align: center; background-color: #CC9933; color: #FFFFFF;">
        <p>@2019-07-27 cuixiao@shangweiec.com</p>
        <p>联系邮箱：cuixiao@shangweiec.com</p>
        <p>联系电话：17731000796</p>
    </div>
</div>
<script src="${request.contextPath}/js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${request.contextPath}/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="${request.contextPath}/js/jquery.cookie.js"></script>
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
                    // console.log($.cookie("token"));
                    // alert("登录成功");
                    window.location.href = 'demo';
                },
                complete: function() {
                    // layer.close(loadIndex);
                },
                error: function (xhr, type, errorThrown) {
                    if (xhr.status == 500) {
                        layer.msg(xhr.responseJSON.message);
                    }
                }
            });
        });

        // form.on('submit(register)', function(data){
        //     layer.open({
        //         type: 2,
        //         content: 'register',
        //         shade: false,
        //         area: ['350px', '400px'],
        //         title: '注册'
        //     });
        // });

        window.register = function() {
            layer.open({
                type: 2,
                content: 'register',
                shade: false,
                area: ['350px', '400px'],
                title: '注册'
            });
        }
    });
</script>
</body>

</html>
