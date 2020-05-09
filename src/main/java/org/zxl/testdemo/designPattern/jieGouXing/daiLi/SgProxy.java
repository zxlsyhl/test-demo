package org.zxl.testdemo.designPattern.jieGouXing.daiLi;

/**
 * 代理： 韶关代理
 */
public class SgProxy implements Specialty {
    private WySpecialty wySpecialty = new WySpecialty();
    @Override
    public void display() {
        preReq();
        wySpecialty.display();
        postReq();
    }

    public void preReq(){
        System.out.println("韶关代理，买商品前");
    }


    public void postReq(){
        System.out.println("韶关代理，买商品后");
    }
}
