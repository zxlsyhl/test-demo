package zxl.junit.finaltest;

import org.junit.Test;
import zxl.junit.entity.Student;

public class FinalTest {
    /**
     * final修饰的基本数据类型，值不能改变，修改其值时，编译不通过。
     */
    @Test
    public void test1(){
        final String a = "";
//        a = "b";
        final int b = 0;
//        b = 1;
    }

    /**
     * final修饰的引用数据类型，值可以变，但引用不可变（地址不可变），编译不通过。
     */
    @Test
    public void test2(){
        final Student stu = new Student();
        stu.setAge(1);
//        stu = new Student();
    }

    @Test
    public void test3(){
        new MethodFinalTest().testone("");
    }

    /**
     * 修饰方法的参数，防止形参被方法篡改，基础数据类型值不可以改，引用数据类型值可改引用不可以改。
     */
    class MethodFinalTest{

        void testone(final String a){
//            a = "";
            System.out.println("testone");
        }

        void testTwo(final Student stu){
            stu.setAge(1);
//            stu = new Student();
        }
     }
}
