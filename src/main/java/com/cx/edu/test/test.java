package com.cx.edu.test;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class test {

    /**
     * 属性colname
     */
    @Size(max = 5, message = "栏目名称输入过长")
    private String colname;

    /**
     * 属性colsy
     */
    @Size(max = 50, message = "栏目索引输入过长")
    private String colsy;

    /**
     * 属性colwblink
     */
    @Size(max = 50, message = "参数输入过长")
    private String colwblink;


    /**
     * 属性colpageurl
     */
    @Size(max = 50, message = "生成页面路径输入过长")
    private String colpageurl;

    /**
     * 属性colpicurl
     */
    private String colpicurl;

    /**
     * 属性content
     */
    private String content;

    /**
     * 属性keyword
     */
    @Size(max = 50, message = "关键字列表输入过长")
    private String keyword;

    /**
     * 属性describe
     */
    @Size(max = 100, message = "页面描述输入过长")
    private String describe;

    @Size(max = 50, message = "参数输入过长")
    private String wblink;

    @Digits(integer=3,fraction=0, message="请输入数字")
    private Integer contentmodel;

    @Digits(integer=3,fraction=0, message="请输入数字")
    private Integer isdel;

    @Size(max = 25, message = "参数输入过长")
    private String chiefeditor;

    @Size(max = 250, message = "参数输入过长")
    private String specialname;

    @Size(max = 50, message = "参数输入过长")
    private String publishtime;

    @Digits(integer=10,fraction=0, message="请输入数字")
    private Integer coltemplate;

    @Digits(integer=10,fraction=0, message="请输入数字")
    private Integer contemplate;
}
