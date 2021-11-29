package com.test.mark.zhang.test.other.auth;

import lombok.Data;

import java.util.List;

/**
 * @author by mark
 * @Classname PermissionConfig
 * @Description TODO
 * @Date 2021/11/16 9:22 上午
 */
@Data
public class PermissionConfig {
    private List<MenuConfig> menus;
    private List<RolePermissionConfig> rolePermissionConfigs;
}
