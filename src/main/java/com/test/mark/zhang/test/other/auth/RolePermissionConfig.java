package com.test.mark.zhang.test.other.auth;

import lombok.Data;

import java.util.List;

/**
 * @author by mark
 * @Classname RolePermissionConfig
 * @Description TODO
 * @Date 2021/11/16 9:17 上午
 */
@Data
public class RolePermissionConfig {

    private List<String> roleTypes;
    private List<String> include;
    private List<String> exclude;
}
