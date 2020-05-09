package org.zxl.testdemo.designPattern.jieGouXing.qiaoJie;

public class Application
{
    public static void main(String[] args) {
        Color c = new YellowColor();
        Color c2 = new RedColor();
        Bag bag = new HandBag();
        bag.setColor(c);
        System.out.println(bag.getName());

        Bag bag1 = new WalletBag();
        bag1.setColor(c2);
        System.out.println(bag1.getName());

        bag1.setColor(c);
        System.out.println(bag1.getName());
    }
}
