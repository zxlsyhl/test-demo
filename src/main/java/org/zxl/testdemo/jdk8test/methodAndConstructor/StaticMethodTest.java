package org.zxl.testdemo.jdk8test.methodAndConstructor;

import org.junit.Test;

public class StaticMethodTest {
    /**
     * 通过静态方法引用 实现接口
     */
    @Test
    public void test1(){
        Converter<String ,Integer> converter = Integer::valueOf;
        Integer num2 = converter.convert("123");
        System.out.println(num2);
    }
}
