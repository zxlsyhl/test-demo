package org.zxl.testdemo.designPattern.xingWeiXing.mingLing;

public class HunTun implements Breakfast {
    private HunTunChef receiver;

    public HunTun() {
        receiver = new HunTunChef();
    }

    @Override
    public void cooking() {
        receiver.cooking();
    }
}
