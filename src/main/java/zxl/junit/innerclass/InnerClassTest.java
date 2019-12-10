package zxl.junit.innerclass;

import org.junit.Test;

public class InnerClassTest {
    /**
     * 实例化一个非静态的内部类：1、先生成一个外部类的对象实例。2、再通过外部类的对象实例生成内部类的对象。
     */
    @Test
    public void test1(){
        InnerClassDemo innerClassDemo = new InnerClassDemo();
        InnerClassDemo.InnerClassOne  innerClassOne = innerClassDemo.new InnerClassOne();
    }

    /**
     * 实例化一个静态的内部类，不依赖于外部类的实例，直接实例化内部类的对象。
     * 调用内部静态类的方法或变量，通过类名直接调用。
     */
    public void test2(){
        InnerClassDemo.InnerClassTwo innerClassTwo = new InnerClassDemo.InnerClassTwo();
        int c = InnerClassDemo.InnerClassTwo.c;
    }
}
