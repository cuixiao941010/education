function encryptInput(dom) {

    // var new_element=document.createElement("script");
    // new_element.setAttribute("type","text/javascript");
    // new_element.setAttribute("src","./js/CryptoJS/crypto-js.js");
    // document.body.appendChild(new_element);

    var key = "0321ebeba1f75de2d3cd3471af7418a4";//秘钥。长度32的16进制字符串。
    var inputs = $("#"+dom +" input");
    console.log(inputs);
    for(var i=0;i<inputs.length;i++) {
        var value = inputs[i].value;
        var crypKey = CryptoJS.enc.Utf8.parse(key);
        var ciphertext = aesEncryptEBC(value, crypKey);
        var encryptedBase64Str = ciphertext.toString();
        var value = document.getElementById(inputs[i].id);
        value.value = encryptedBase64Str;
    }
}