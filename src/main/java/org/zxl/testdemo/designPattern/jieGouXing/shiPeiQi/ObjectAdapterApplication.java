package org.zxl.testdemo.designPattern.jieGouXing.shiPeiQi;

public class ObjectAdapterApplication {
    public static void main(String[] args) {
        System.out.println("对象适配器模式测试：");
        Adaptee adaptee = new Adaptee();
        Target target = new ObjectAdapter(adaptee);
        target.request();
    }
}
