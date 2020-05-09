package org.zxl.testdemo.designPattern.jieGouXing.shiPeiQi;

public class ClassAdapterApplication {
    public static void main(String[] args) {
        System.out.println("类适配器模式测试");
        Target target = new ClassAdapter();
        target.request();
    }
}
