package org.zxl.testdemo.designPattern.jieGouXing.qiaoJie;

/**
 * 扩展抽象化角色：挎包
 */
public class HandBag extends Bag{
    @Override
    public String getName() {
        return color.getColor()+"HandBag";
    }
}
