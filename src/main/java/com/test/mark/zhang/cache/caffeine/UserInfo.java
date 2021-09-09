package com.test.mark.zhang.cache.caffeine;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {
    private Integer id;
    private String name;
    private String sex;
    private Integer age;
    private String userName;
    private transient String passWord;
    private String phone;
    private String email;
    private String createDay;
}