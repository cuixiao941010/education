package com.cx.edu.user.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginCondition {

    @NotBlank(message = "用户名错误")
    private String userName;

    @NotBlank(message = "密码错误")
    private String password;

}
