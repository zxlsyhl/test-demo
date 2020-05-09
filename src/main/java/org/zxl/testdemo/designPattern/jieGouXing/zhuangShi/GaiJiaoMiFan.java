package org.zxl.testdemo.designPattern.jieGouXing.zhuangShi;

/**
 * 抽象装饰角色：米饭
 */
public class GaiJiaoMiFan implements Food {
    Food food;

    public GaiJiaoMiFan(Food food) {
        this.food = food;
    }

    @Override
    public void display() {
        food.display();
    }
}
