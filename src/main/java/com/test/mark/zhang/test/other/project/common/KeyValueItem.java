package com.test.mark.zhang.test.other.project.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author by mark
 * @Classname KeyValueItem
 * @Description TODO
 * @Date 2022/2/9 10:29 上午
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class KeyValueItem<T> {

    private String name;
    private T value;
}

class KeyValueClient {
    public static void main(String[] args) {
        KeyValueItem<Integer> log = new KeyValueItem<>("log", 19);
        System.out.println(log.getValue());
        System.out.println(log.getName());
        System.out.println(log);
    }
}
