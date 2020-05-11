package org.zxl.testdemo.designPattern.xingWeiXing.zhongJieZhe;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体中介者
 */
public class ConcreteMediator extends Mediator {
    private List<Colleague> colleagues = new ArrayList<>();
    @Override
    public void register(Colleague colleague) {
        if(!colleagues.contains(colleague)){
            colleagues.add(colleague);
            colleague.setMediator(this);
        }
    }

    @Override
    public void relay(Colleague cl) {
        for (Colleague ob:colleagues){
            if(!ob.equals(cl)){
                ob.receive();
            }
        }
    }
}
