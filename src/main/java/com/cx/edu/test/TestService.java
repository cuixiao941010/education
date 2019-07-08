package com.cx.edu.test;

public class TestService {

    /**
     * lambda表达式测试
     */
    interface Mathoperation{
        int operation(int a,int b);

//        int abc(int a, int b);
    }

    public static void main(String args[]) {
//        (String s) -> System.out.print(s)
        Mathoperation addition = (int a, int b) -> {
            System.out.println("测试打印是否执行");
            return a + b;
        };
        Mathoperation mathoperation = (a, b) -> a*b;
        addition.operation(1,2);
    }
}
