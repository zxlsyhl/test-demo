package org.zxl.testdemo.designPattern.xingWeiXing.fangWenZhe;

/**
 * 具体元素A类
 */
public class ConcreteElementB implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    public String operationB(){
        return "具体元素B的操作";
    }
}
