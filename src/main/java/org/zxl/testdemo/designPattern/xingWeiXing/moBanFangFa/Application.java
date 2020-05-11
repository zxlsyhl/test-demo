package org.zxl.testdemo.designPattern.xingWeiXing.moBanFangFa;

public class Application {
    public static void main(String[] args) {
        StudyAbroad studyAbroad = new StudyJapan();
        studyAbroad.liuXue();

        studyAbroad = new StudyEngland();
        studyAbroad.liuXue();
    }
}
