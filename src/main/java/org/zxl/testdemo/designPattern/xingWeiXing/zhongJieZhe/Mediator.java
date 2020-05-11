package org.zxl.testdemo.designPattern.xingWeiXing.zhongJieZhe;

/**
 * 抽象中介者
 */
public abstract class Mediator {
    public abstract void register(Colleague colleague); //注册
    public abstract void relay(Colleague cl); //转发
}
