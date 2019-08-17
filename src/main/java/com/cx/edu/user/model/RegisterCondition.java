package com.cx.edu.user.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class RegisterCondition {

    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^\\w{6,18}$", message = "用户名不合法")
    private String userName;

    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^\\w{6,18}$", message = "密码不合法")
    private String password;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @NotBlank(message = "联系方式不能为空")
    @Pattern(regexp = "^1\\d{10}", message = "手机号格式不正确")
    private String mobile;

}
