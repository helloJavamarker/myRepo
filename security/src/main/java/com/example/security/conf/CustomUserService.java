package com.example.security.conf;

import com.example.security.entity.Role;
import com.example.security.entity.UserDetail;
import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author by mark
 * @Classname CustomUserService
 * @Description TODO
 * @Date 2021/11/18 9:47 上午
 */

//这里CustomeService是通过@bean注解注入的,所以并不用@service再次注入
public class CustomUserService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Role user = userService.getOneByName(userName);
        if (user != null) {
            //得到权限信息   permissionService

//            UserDetail userDetail = new UserDetail();
//            userDetail.setAuthVersion();
//            userDetail.setRoleId();
//            return userDetail;
        }
        return null;
    }
}
