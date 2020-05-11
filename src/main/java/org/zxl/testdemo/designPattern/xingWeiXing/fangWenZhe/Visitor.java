package org.zxl.testdemo.designPattern.xingWeiXing.fangWenZhe;

/**
 * 抽象访问者
 */
public interface Visitor {
    void visit(ConcreteElementA element);
    void visit(ConcreteElementB element);
}
