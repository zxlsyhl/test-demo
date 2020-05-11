package org.zxl.testdemo.designPattern.xingWeiXing.zeRenLian;

/**
 * 具体处理者3：院长类
 */
public class Dean extends Leader
{
    @Override
    public void handlerRequest(int leaveDays) {
        if(leaveDays<=10){
            System.out.println("院长批准您请假"+leaveDays+"天。");
        }else {
            if(getNext()!=null){
                getNext().handlerRequest(leaveDays);
            }else {
                System.out.println("请假天数太多，没有人批准改假条");
            }
        }
    }
}
