package org.zxl.testdemo.designPattern.chuangJianXing.jianZaoZhe;

/**
 * 指挥者：调用创建者中的方法完成复杂对象的创建
 */
public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public Product construct(){
        builder.buildPart1();
        builder.buildPart2();
        builder.buildPart3();
        return builder.getProduct();
    }
}
