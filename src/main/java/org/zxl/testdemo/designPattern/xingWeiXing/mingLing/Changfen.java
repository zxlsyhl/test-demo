package org.zxl.testdemo.designPattern.xingWeiXing.mingLing;

/**
 * 具体命令：肠粉
 */
public class Changfen implements Breakfast {
    private ChangFenChef receiver;

    public Changfen() {
        receiver = new ChangFenChef();
    }

    @Override
    public void cooking() {
        receiver.cooking();
    }
}
