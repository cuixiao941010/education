<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>login</title>
    <script type="text/javascript" src="${request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${request.contextPath}/js/jquery.cookie.js"></script>
    <style type="text/css">
        *{
            margin: 0;
            padding: 0;
        }
        #wrap {
            height: 719px;
            width: 100;
            <#--background-image: url("${request.contextPath}/img/background.jpg");-->
            background: darkgrey;
            background-repeat: no-repeat;
            background-position: center center;
            position: relative;
        }
        #head {
            height: 120px;
            width: 100;
            background-color: #66CCCC;
            text-align: center;
            position: relative;
        }
        #foot {
            width: 100;
            height: 126px;
            background-color: #CC9933;
            position: relative;
        }
        #wrap .logGet {
            height: 408px;
            width: 368px;
            position: absolute;
            background-color: #FFFFFF;
            top: 20%;
            right: 15%;
        }
        .logC button {
            width: 100%;
            height: 45px;
            background-color: #ee7700;
            border: none;
            color: white;
            font-size: 18px;
        }
        .logGet .logD.logDtip .p1 {
            display: inline-block;
            font-size: 28px;
            margin-top: 30px;
            width: 86%;
        }
        #wrap .logGet .logD.logDtip {
            width: 86%;
            border-bottom: 1px solid #ee7700;
            margin-bottom: 60px;
            margin-top: 0px;
            margin-right: auto;
            margin-left: auto;
        }
        .logGet .lgD img {
            position: absolute;
            top: 12px;
            left: 8px;
        }
        .logGet .lgD input {
            width: 100%;
            height: 42px;
            text-indent: 2.5rem;
        }
        #wrap .logGet .lgD {
            width: 86%;
            position: relative;
            margin-bottom: 30px;
            margin-top: 30px;
            margin-right: auto;
            margin-left: auto;
        }
        #wrap .logGet .logC {
            width: 86%;
            margin-top: 0px;
            margin-right: auto;
            margin-bottom: 0px;
            margin-left: auto;
        }


        .title {
            font-family: "宋体";
            color: #FFFFFF;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);  /* 使用css3的transform来实现 */
            font-size: 36px;
            height: 40px;
            width: 30%;
        }

        .copyright {
            font-family: "宋体";
            color: #FFFFFF;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);  /* 使用css3的transform来实现 */
            height: 60px;
            width: 40%;
            text-align:center;
        }


        #foot .copyright .img {
            width: 100%;
            height: 24px;
            position: relative;
        }
        .copyright .img .icon {
            display: inline-block;
            width: 24px;
            height: 24px;
            margin-left: 22px;
            vertical-align: middle;
            background-repeat: no-repeat;
            vertical-align: middle;
            margin-right: 5px;
        }

        .copyright .img .icon1 {
            display: inline-block;
            width: 24px;
            height: 24px;
            margin-left: 22px;
            vertical-align: middle;
            background-repeat: no-repeat;
            vertical-align: middle;
            margin-right: 5px;
        }
        .copyright .img .icon2 {
            display: inline-block;
            width: 24px;
            height: 24px;
            margin-left: 22px;
            vertical-align: middle;
            background-repeat: no-repeat;
            vertical-align: middle;
            margin-right: 5px;
        }
        #foot .copyright p {
            height: 24px;
            width: 100%;
        }
    </style>
</head>

<body>
<div class="header" id="head">
    <div class="title">随便做系统</div>

</div>

<div class="wrap" id="wrap">
    <div class="logGet">
        <!-- 头部提示信息 -->
        <div class="logD logDtip">
            <p class="p1">登录</p>
        </div>
        <!-- 输入框 -->
        <div class="lgD">
            <img src="${request.contextPath}/img/logName.jpg" width="20" height="20" alt=""/>
            <input type="text" placeholder="输入用户名" id="username"/>
        </div>
        <div class="lgD">
            <img src="${request.contextPath}/img/pwd.jpg" width="20" height="20" alt=""/>
            <input type="text" placeholder="输入用户密码" id="password"/>
        </div>
        <div class="logC">
            <button onclick="loginFnc()">登 录</button>
        </div>
    </div>
</div>

<div class="footer" id="foot">
    <div class="copyright">
        <p>Copyright © 2018 Qunar.com Inc. All Rights Reserved.</p>
        <div class="img">
            <i class="icon"></i><span>联系邮箱：xxxxxx.com</span>
        </div>

        <div class="img">
            <i class="icon1"></i><span>联系地址：xxxxx</span>
        </div>

        <div class="img">
            <i class="icon2"></i><span>联系电话：xxxxxxxx</span>
        </div>
    </div>
</div>
<script>
    function loginFnc() {
        if ($("#username").val() == "") {
            alert("情输入用户名");
        }
        if ($("#password").val() == "") {
            alert("请输入密码");
        }
        var param = {
            userName: $("#username").val(),
            password: $("#password").val(),
        }
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
                alert("登录成功");
                window.location.href = 'demo';
            },
            complete: function() {
                // layer.close(loadIndex);
            },
            error: function (xhr, type, errorThrown) {
            }
        })
    }
</script>

</body>
</html>