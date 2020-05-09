package org.zxl.testdemo.designPattern.jieGouXing.shiPeiQi;

/**
 * 对象适配器类
 */
public class ObjectAdapter implements Target {
    private Adaptee adaptee;

    public ObjectAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.specificRequest();
    }
}
