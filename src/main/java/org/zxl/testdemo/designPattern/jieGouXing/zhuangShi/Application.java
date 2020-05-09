package org.zxl.testdemo.designPattern.jieGouXing.zhuangShi;

public class Application {
    public static void main(String[] args) {
        Food food = new MiFan();
//        food.display();

        Food food3 = new GaiJiaoMiFan(food);
//        food3.display();

        Food food1 = new JIdan(food);
//        food1.display();

        Food food2 = new NiuRou(food1);
        food2.display();
    }
}
