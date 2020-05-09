package org.zxl.testdemo.designPattern.jieGouXing.qiaoJie;

/**
 * 扩展抽象化角色：钱包
 */
public class WalletBag extends Bag {
    @Override
    public String getName() {
        return color.getColor()+"Wallet";
    }
}
