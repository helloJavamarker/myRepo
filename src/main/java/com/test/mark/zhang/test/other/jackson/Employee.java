package com.test.mark.zhang.test.other.jackson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author by mark
 * @Classname Employee
 * @Description TODO
 * @Date 2021/8/11 2:45 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private String name;
    private String email;
}
