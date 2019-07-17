<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>login</title>
    <script type="text/javascript" src="${request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${request.contextPath}/js/jquery.cookie.js"></script>
    <script src="${request.contextPath}/js/CryptoJS/crypto-js.js"></script>
    <script src="${request.contextPath}/js/encryptInput.js"></script>
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
        // if ($("#password").val() == "") {
        //     alert("请输入密码");
        // }


       // var message = $("#username").val();//utf8字符串，待加密
        //var iv = CryptoJS.lib.WordArray.random(128 / 8).toString(CryptoJS.enc.Hex);//随机生成长度为32的16进制字符串。IV称为初始向量，不同的IV加密后的字符串是不同的，加密和解密需要相同的IV。
        // console.log("iv:"+iv);
        // var key = "0321ebeba1f75de2d3cd3471af7418a4";//秘钥。长度32的16进制字符串。
        // var key = "032103210321032103210321032103aa";
        // CBC秘钥的加密解密
        // var cryptkey  = CryptoJS.enc.Hex.parse(key);//将16进制字符串转换为 WordArray对象
        // var ciphertext = aesEncrypt(message,cryptkey,iv);//加密
        // var decryptedMessage = aesDecrypt(ciphertext,cryptkey,iv);//解密
        // console.log("秘钥"+ciphertext.toString());
        // console.log("解密"+decryptedMessage);



        //EBC秘钥加密解密
        // var crypKey = CryptoJS.enc.Utf8.parse(key);
        // console.log("加密key："+crypKey);
        // var ciphertext = aesEncryptEBC(message, crypKey);
        // var encryptedBase64Str = ciphertext.toString();
        // console.log("加密密文1："+encryptedBase64Str);
        // var encryptedStr = ciphertext.ciphertext.toString();
        // console.log("加密密文2："+encryptedStr);
        // var encryptedHexStr = CryptoJS.enc.Hex.parse(encryptedStr);
        // var encryptedBase64Str = CryptoJS.enc.Base64.stringify(encryptedHexStr);
        // var decryptedData = aesDecryptEBC(encryptedBase64Str, crypKey);
        // console.log("解密："+decryptedData.toString(CryptoJS.enc.Utf8));

        encryptInput("wrap");
        console.log($("#username").val());
        console.log($("#password").val());


    }

    //** 加密 **
    //var ciphertext = CryptoJS.AES.encrypt(message, key, cfg);
    //params: 注意参数key为WordArray对象
    //return: 密码对象 或者 密码对象Base64字符串
    function aesEncrypt(message,key,iv){
        var ciphertext = CryptoJS.AES.encrypt(message, key, {
            iv: CryptoJS.enc.Hex.parse(iv),
            mode: CryptoJS.mode.CBC,
            padding:CryptoJS.pad.Pkcs7
        });
        return ciphertext;//密码对象(Obejct类型，非WordArray类型)，Base64编码。
        //return ciphertext.toString();//密码对象的Base64字符串
    }

    function aesEncryptEBC(message, key) {
        var ciphertext = CryptoJS.AES.encrypt(message, key, {
            mode: CryptoJS.mode.ECB,
            padding: CryptoJS.pad.Pkcs7
        });
        return ciphertext;
    }

    //** 解密 **
    //var plaintext  = CryptoJS.AES.decrypt(ciphertext, key, cfg);
    //params: 注意参数ciphertext 必须为 Base64编码的对象或者字符串。
    function aesDecrypt(ciphertext,key,iv){
        var decrypted = CryptoJS.AES.decrypt(ciphertext,key,{
            iv: CryptoJS.enc.Hex.parse(iv),
            mode: CryptoJS.mode.CBC,
            padding:CryptoJS.pad.Pkcs7
        });
        return decrypted.toString(CryptoJS.enc.Utf8);//WordArray对象转utf8字符串
    }
    
    function aesDecryptEBC(encryptedBase64Str, key) {
        var decryptedData = CryptoJS.AES.decrypt(encryptedBase64Str, key, {
            mode: CryptoJS.mode.ECB,
            padding: CryptoJS.pad.Pkcs7
        });
        return decryptedData;
    }

    // function encryptInput(dom) {
    //     var key = "0321ebeba1f75de2d3cd3471af7418a4";//秘钥。长度32的16进制字符串。
    //     var inputs = $("#"+dom +" input");
    //     console.log(inputs);
    //     for(var i=0;i<inputs.length;i++) {
    //         var value = inputs[i].value;
    //         var crypKey = CryptoJS.enc.Utf8.parse(key);
    //         var ciphertext = aesEncryptEBC(value, crypKey);
    //         var encryptedBase64Str = ciphertext.toString();
    //         var value = document.getElementById(inputs[i].id);
    //         value.value = encryptedBase64Str;
    //     }
    // }

</script>

</body>
</html>