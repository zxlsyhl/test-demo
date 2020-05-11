package org.zxl.testdemo.designPattern.xingWeiXing.moBanFangFa;

/**
 * 具体子类：日本留学
 */
public class StudyJapan extends StudyAbroad {
    @Override
    public void qianZheng() {
        System.out.println("申请日本签证");
    }

    @Override
    public void ruXue() {
        System.out.println("进入日本东京大学，开始学习");
    }
}
