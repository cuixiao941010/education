package com.cx.edu.test;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Person {

    @Size(max=20,message="姓名长度不能大于20")
    @NotEmpty(message="姓名不能为空")
    private String name;

    @Range(min = 0, max = 1, message = "性别只能输入只能输入0或1")
    private Integer gender;

    @NotNull(message = "请填写年龄")
    private Integer age;
}
