package com.test.mark.zhang.test.other.project.cascade.session;

import com.google.common.graph.AbstractNetwork;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author by mark
 * @Classname User
 * @Description TODO
 * @Date 2021/7/14 1:22 下午
 */
@Data
public class User implements Serializable {

    private String id;
    private String name;
    private String realName;
    private Long expiredTime;
    private Date loginTime;
    private String lastLoginIp;

    @Phone
    private String mobile;
    private String email;
    private String province;
    private Integer defaultPwd;

}
