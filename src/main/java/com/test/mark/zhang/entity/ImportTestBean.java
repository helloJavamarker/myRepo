package com.test.mark.zhang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author by mark
 * @Classname ImportTestBean
 * @Description TODO
 * @Date 2022/2/26 2:32 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportTestBean {

    private int id;
    private String name;
    private int age;
    private int height;

    private String hobby;
    private int money;
    private String play;
}
