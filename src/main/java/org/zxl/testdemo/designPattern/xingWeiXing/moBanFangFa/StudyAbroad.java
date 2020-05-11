package org.zxl.testdemo.designPattern.xingWeiXing.moBanFangFa;

/**
 * 抽象类：出国留学
 */
public abstract class StudyAbroad {
    public void liuXue(){
        xueXiao();
        huZhao();
        qianZheng();
        chuFa();
        ruXue();
    }

    public void xueXiao(){
        System.out.println("向学校申请留学");
    }

    public void huZhao(){
        System.out.println("办理中国护照");
    }

    public abstract void qianZheng(); //签证

    public void chuFa(){
        System.out.println("去机场出发");
    }

    public abstract void ruXue(); //入学
}
