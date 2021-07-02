package com.test.mark.zhang.test.other.project.security;

import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

/**
 * @Classname SecurityZoneEvent
 * @Description TODO
 * @Date 2021/6/25 3:11 下午
 * @Created by mark
 */
@EqualsAndHashCode(of = {"action"}, callSuper = false)
public class SecurityZoneEvent extends ApplicationEvent {

    public SecurityZoneEvent(Object source,Action action, SecurityZone securityZone) {
        super(source);
        this.action = action;
        this.securityZone = securityZone;
    }

    private Action action;
    private SecurityZone securityZone;

    public static enum Action {
        add,update,delete,sync
    }

}
