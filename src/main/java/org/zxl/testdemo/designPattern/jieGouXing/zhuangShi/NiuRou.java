package org.zxl.testdemo.designPattern.jieGouXing.zhuangShi;

public class NiuRou extends GaiJiaoMiFan {
    public NiuRou(Food food) {
        super(food);
    }

    @Override
    public void display() {
        done();
        super.display();
    }

    public void done(){
        System.out.println("加牛肉");
    }
}
