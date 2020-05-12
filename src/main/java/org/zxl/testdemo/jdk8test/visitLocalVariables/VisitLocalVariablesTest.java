package org.zxl.testdemo.jdk8test.visitLocalVariables;

import org.junit.Test;

public class VisitLocalVariablesTest {
    /**
     * 类实现具体接口，实现类中访问局部变量
     */
    @Test
    public void test(){
        VisitLocalVariablesTest test = new VisitLocalVariablesTest();
        test.num = 10;
        System.out.println(new Converter1().convert(1));
    }

    int num = 0;

    class Converter1 implements Converter<Integer,String>{
        @Override
        public String convert(Integer from) {
            return String.valueOf(from+num);
        }
    }

    /**
     * 访问局部变量，局部变量使用fianl修饰。
     */
    @Test
    public void test2(){
        final int num = 1;
        Converter<Integer, String> stringConverter = (from -> String.valueOf(from+num));
        String abc = stringConverter.convert(2);
        System.out.println(abc);
    }

    /**
     * 访问局部变量，局部变量不为fianl修饰，但不能修改值，否则无法通过编译。
     */
    @Test
    public void test3(){
        int num = 1;
        Converter<Integer, String> stringConverter = (from -> {from++;return String.valueOf(from+num); });
        String abc = stringConverter.convert(2);
        System.out.println(abc);
        //num = 2;  //无法通过编译,lambda也无法修改
    }
}
