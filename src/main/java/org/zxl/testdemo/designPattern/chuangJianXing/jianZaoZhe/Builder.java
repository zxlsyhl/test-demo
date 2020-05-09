package org.zxl.testdemo.designPattern.chuangJianXing.jianZaoZhe;

/**
 * 抽象创建者：包含创建产品各个子部件的抽象方法
 */
public abstract class Builder {
    protected Product product = new Product();
    public abstract void buildPart1();
    public abstract void buildPart2();
    public abstract void buildPart3();
    //获取产品对象
    public Product getProduct(){
        return product;
    }
}
