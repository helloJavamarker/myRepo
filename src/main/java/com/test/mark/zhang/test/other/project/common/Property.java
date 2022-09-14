package com.test.mark.zhang.test.other.project.common;

import lombok.Data;

/**
 * @author by mark
 * @Classname Property
 * @Description TODO
 * @Date 2022/7/14 9:48 上午
 */
@Data
public class Property {
    private String name;
    private String tip;
    private String nameType;
    private String valueType;
    private boolean restart;
    private int times;

    public Property(String name, String valueType, boolean restart, int times) {
        this.name = name;
        this.valueType = valueType;
        this.restart = restart;
        this.times = times;
    }
}
