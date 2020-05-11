package org.zxl.testdemo.designPattern.xingWeiXing.zhuangTai;

/**
 * 具体状态类：优秀
 */
public class HighState extends AbstractState {
    public HighState(AbstractState state) {
        hj = state.hj;
        stateName = "优秀";
        score=state.score;
    }

    @Override
    public void checkState() {
        if(score<60)
        {
            hj.setState(new LowState(this));
        }
        else if(score<90)
        {
            hj.setState(new MiddleState(this));
        }
    }
}
