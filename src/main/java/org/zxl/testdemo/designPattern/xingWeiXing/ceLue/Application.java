package org.zxl.testdemo.designPattern.xingWeiXing.ceLue;

public class Application {
    public static void main(String[] args) {
        PigCooking pigCooking = new HongshaoPig();
        Chufang chufang = new Chufang();
        chufang.setPigCooking(pigCooking);
        chufang.cookingMethod();

        pigCooking = new ZhutangPig();
        chufang.setPigCooking(pigCooking);
        chufang.cookingMethod();
    }
}
