package org.zxl.testdemo.designPattern.jieGouXing.zhuangShi;

/**
 * 具体装饰角色：米饭加鸡蛋
 */
public class JIdan extends GaiJiaoMiFan {
    public JIdan(Food food) {
        super(food);
    }

    @Override
    public void display() {
        done();
        super.display();
    }

    public void done(){
        System.out.println("加一个鸡蛋");
    }
}
