package com.test.mark.zhang.test.agency.heima.disign.day6.disign_partten.pattern.responsibility;

/**
 * @version v1.0
 * @ClassName: GroupLeader
 * @Description: 总经理类（具体的处理者）
 * @Author: 黑马程序员
 */
public class GeneralManager extends Handler {

    public GeneralManager() {
        super(NUM_THREE, NUM_SEVEN);
    }

    protected void handleLeave(LeaveRequest leave) {
        System.out.println(leave.getName() + "请假" + leave.getNum() + "天，" + leave.getContent() + "。");
        System.out.println("总经理审批：同意");
    }
}
