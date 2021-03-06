package org.zxl.testdemo.designPattern.chuangJianXing.jianZaoZhe;

/**
 * 具体建造者：实现了抽象建造者的接口
 */
public class ConcreteBuilder extends Builder {
    @Override
    public void buildPart1() {
        product.setPart1("建造part1");
    }

    @Override
    public void buildPart2() {
        product.setPart2("建造part2");
    }

    @Override
    public void buildPart3() {
        product.setPart3("建造part3");
    }
}
