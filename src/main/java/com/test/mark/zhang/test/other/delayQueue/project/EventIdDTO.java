package com.test.mark.zhang.test.other.delayQueue.project;

import lombok.Data;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author mark
 */
@Data
public class EventIdDTO implements Delayed {
    private Date createTime;
    private String alarmEventId;
    private String logEventId;

    @Override
    public long getDelay(TimeUnit unit) {
        return createTime.getTime() - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        EventIdDTO dto = (EventIdDTO) o;
        long diff = this.createTime.getTime() - dto.createTime.getTime();
        return diff <= 0 ? -1 : 1;
    }
}
