package org.zxl.testdemo.jdk8test.visitObjectOrStaticVariables;

import org.junit.Test;

/**
 * lambda表达式中可以访问对象字段和静态变量，可读又可写
 */
public class Lambda4 {
    static int outerStaticNum;
    int outerNum;

    @Test
    public void test(){
        Converter<Integer, String> stringConverter1 = from -> {
            outerNum =23;
            return String.valueOf(from+outerNum);};
        Converter<Integer, String> stringConverter2 = from -> {
            outerStaticNum = 22;
            return String.valueOf(from+outerStaticNum);
        };

        System.out.println(stringConverter1.convert(1));
        System.out.println(stringConverter2.convert(1));
    }
}
