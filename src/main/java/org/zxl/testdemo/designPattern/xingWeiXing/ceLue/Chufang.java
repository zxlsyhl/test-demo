package org.zxl.testdemo.designPattern.xingWeiXing.ceLue;

/**
 * 环境类：厨房
 */
public class Chufang {
    private PigCooking pigCooking;

    public PigCooking getPigCooking() {
        return pigCooking;
    }

    public void setPigCooking(PigCooking pigCooking) {
        this.pigCooking = pigCooking;
    }

    public void cookingMethod(){
        pigCooking.cookingMethod();
    }
}
