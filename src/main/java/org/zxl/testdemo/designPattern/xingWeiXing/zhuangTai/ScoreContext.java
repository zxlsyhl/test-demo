package org.zxl.testdemo.designPattern.xingWeiXing.zhuangTai;

/**
 * 环境类
 */
public class ScoreContext {
    private AbstractState state;

    public ScoreContext() {
        state = new LowState(this);
    }

    public AbstractState getState() {
        return state;
    }

    public void setState(AbstractState state) {
        this.state = state;
    }

    public void add(int score){
        state.addScore(score);
    }
}
