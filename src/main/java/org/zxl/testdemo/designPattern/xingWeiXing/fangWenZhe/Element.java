package org.zxl.testdemo.designPattern.xingWeiXing.fangWenZhe;

/**
 * 抽象元素类
 */
public interface Element {
    void accept(Visitor visitor);
}
