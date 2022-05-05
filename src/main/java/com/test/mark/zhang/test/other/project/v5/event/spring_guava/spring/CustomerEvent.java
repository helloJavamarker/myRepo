package com.test.mark.zhang.test.other.project.v5.event.spring_guava.spring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义事件
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEvent {
    private String name;
    private Boolean isCustomer;
}