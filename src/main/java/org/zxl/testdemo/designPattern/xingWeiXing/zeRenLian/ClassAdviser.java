package org.zxl.testdemo.designPattern.xingWeiXing.zeRenLian;

/**
 * 具体处理者1：班主任类
 */
public class ClassAdviser extends Leader {
    @Override
    public void handlerRequest(int leaveDays) {
        if(leaveDays<=2){
            System.out.println("班主任批准您请假"+leaveDays+"天");
        }
        else {
            if(getNext()!=null){
                getNext().handlerRequest(leaveDays);
            }
            else {
                System.out.println("请假天数太多，没有人批准改假条");
            }
        }
    }
}
