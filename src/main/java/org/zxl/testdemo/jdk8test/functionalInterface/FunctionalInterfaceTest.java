package org.zxl.testdemo.jdk8test.functionalInterface;

import org.junit.Test;

public class FunctionalInterfaceTest {
    @Test
    public void test(){
//        Converter<String ,Integer> converter1 = (from) -> Integer.valueOf(from);
//        Integer num1 = converter1.convert("123");
//        System.out.println(num1);

        Converter<Integer , Integer> converter2 = ((t1, t2) -> t1+t2);

    }
}
