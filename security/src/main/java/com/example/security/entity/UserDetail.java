package com.example.security.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author by mark
 * @Classname UserDetail
 * @Description TODO
 * @Date 2021/11/18 9:56 上午
 */
public class UserDetail extends User {

    private Integer id;
    private Integer roleId;
    private long authVersion;

    public UserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
    }

    //这里没有重写toString,id,roleId,authVersion不会被打印
    public UserDetail(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public long getAuthVersion() {
        return authVersion;
    }

    public void setAuthVersion(long authVersion) {
        this.authVersion = authVersion;
    }
}
