package org.zxl.testdemo.designPattern.xingWeiXing.ceLue;

/**
 * 具体策略类：排骨猪肉汤
 */
public class ZhutangPig implements PigCooking {
    @Override
    public void cookingMethod() {
        System.out.println("排骨猪肉汤");
    }
}
