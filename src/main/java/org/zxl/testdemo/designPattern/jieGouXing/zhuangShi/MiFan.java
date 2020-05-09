package org.zxl.testdemo.designPattern.jieGouXing.zhuangShi;

/**
 * 具体构件角色：面条
 */
public class MiFan implements Food{
    public MiFan() {
        System.out.println("开始吃饭了！！");
    }

    @Override
    public void display() {
        System.out.println("我是米饭");
    }
}
