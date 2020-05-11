package org.zxl.testdemo.designPattern.xingWeiXing.mingLing;

/**
 * 调用者：服务员
 */
public class Waiter {
    private Breakfast changFen,hunTun;

    public void setChangFen(Breakfast changFen) {
        this.changFen = changFen;
    }

    public void setHunTun(Breakfast hunTun) {
        this.hunTun = hunTun;
    }

    public void chooseChangFen(){
        changFen.cooking();
    }

    public void chooseHunTun(){
        hunTun.cooking();
    }
}
