package org.zxl.testdemo.innerclass;

public class InnerClassDemo {
    private String param1;
    private static String param2;
    private String param3 = "";

    public void test1(){}

    public static void test2(){}

    /**
     *非静态内部类可以访问外部类的所有成员，静态、非静态。
     */
    class InnerClassOne{
        public InnerClassOne() {
        }

        void method1(){
            param1 = "22";
            test1();
        }
        void method2(){
            param2 = "33";
            test2();
        }
    }

    /**
     *静态内部类可以访问外部类的静态所有成员、非静态成员不能访问。
     */
    static class InnerClassTwo{
        String b = "";
        static int c = 0;
        void method1(){
            param2 = "44";
            test2();
        }
    }
}
