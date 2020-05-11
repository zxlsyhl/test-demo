package org.zxl.testdemo.designPattern.xingWeiXing.mingLing;

public class Application {
    public static void main(String[] args) {
        Breakfast changFen = new Changfen(), huTun = new HunTun();
        Waiter fwy = new Waiter();
        fwy.setChangFen(changFen); //设置肠粉菜单
        fwy.setHunTun(huTun);//设置馄饨菜单
        fwy.chooseChangFen(); //选择肠粉
        fwy.chooseHunTun(); //选择馄饨

    }
}
