package org.zxl.testdemo.designPattern.xingWeiXing.ceLue;

/**
 * 具体策略类：红烧猪肉
 */
public class HongshaoPig implements PigCooking {
    @Override
    public void cookingMethod() {
        System.out.println("做红烧肉");
    }
}
