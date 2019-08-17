package com.cx.edu.test;

import java.util.Map;

/**
 * 测试用例
 */
public class validationTest {

    public static void main(String args[]) {

//        Person person = new Person();
//        person.setName("测试");
//        person.setAge(12);
//        person.setGender(2);
//        ValidationResult result = ValidationUtils.validateProperty(person, "name");
//        ValidationResult result1 = ValidationUtils.validateEntity(person);
//        Map<String, String> map = result.getErrorMsg();
//        Map<String, String> map1 = result1.getErrorMsg();
//        boolean isError = result.isHasErrors();
//        boolean isError1 = result1.isHasErrors();
//        System.out.println("isError" + isError);
//        System.out.println(map);
//        System.out.println("isError" + isError1);
//        System.out.println(map1);

        test test = new test();
        test.setColname("sadasddsfsdfdsfsdfsdfsdfdsfdsfadsfasdfasdasfsadasd");
        test.setContemplate(124545454);
        ValidationUtils validationUtils = new ValidationUtils();
        ValidationResult result1 = validationUtils.validateEntity(test);
        System.out.println(result1);
    }
}
