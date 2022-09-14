package com.test.mark.zhang.test.agency.wang.guava.eventbus.internal;

/**
 * @author by mark
 * @Classname MsgType
 * @Description TODO
 * @Date 2022/7/14 10:36 上午
 */
public enum MsgType {
    /**
     *
     */
    MSG("msg", 1, ""),
    EMAIL("email", 2, ""),
    WE_CHAT("weChat", 3, "");

    MsgType(String name, int code, String msg) {
        this.name = name;
        this.code = code;
        this.msg = msg;
    }

    private final String name;
    private final int code;
    private final String msg;

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
