package com.test.mark.zhang.test.other.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author by mark
 * @Classname AuthServiceImpl
 * @Description TODO
 * @Date 2021/11/16 9:14 上午
 */
@Service
public class AuthServiceImpl implements AuthService{

//    private static final String PERMISSION_PATH = "auth/permission.json";
    private static final String PERMISSION_PATH = "auth/permission.json";

    @Override
    public void reloadAuth(InputStream authConfig, String appName) throws IOException {
        List<RolePermissionConfig> rolePermissionConfigs = readRolePermission();
        System.out.println(rolePermissionConfigs);
    }

    private List<RolePermissionConfig> readRolePermission() throws IOException {
        //classpath和操作系统有关,win/mac/linux不同
        //也可以使用new FileSystemResource("./");来读取文件
        try(InputStream configFile = new ClassPathResource(PERMISSION_PATH).getInputStream()) {
            PermissionConfig permissionConfig = new ObjectMapper().readValue(configFile, PermissionConfig.class);
            return permissionConfig.getRolePermissionConfigs();
        }

    }
}
