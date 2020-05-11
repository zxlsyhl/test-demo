package org.zxl.testdemo.designPattern.xingWeiXing.beiWangLu;

/**
 * 管理者
 */
public class Caretaker {
    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
