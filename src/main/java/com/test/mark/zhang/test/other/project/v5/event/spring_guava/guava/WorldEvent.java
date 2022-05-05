package com.test.mark.zhang.test.other.project.v5.event.spring_guava.guava;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author by mark
 * @Classname WorldEvent
 * @Description TODO
 * @Date 2022/4/29 10:49 上午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorldEvent extends HelloEvent {

    private int eventNo;
    // eventName 虽然是私有的,不能继承,但是set,get可以继承

    public WorldEvent(String name, int no) {
        setEventName(name);
        setEventNo(no);
    }

}
