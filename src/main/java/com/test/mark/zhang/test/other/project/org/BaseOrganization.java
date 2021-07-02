package com.test.mark.zhang.test.other.project.org;

import lombok.Data;

/**
 * @Classname BaseOrganization
 * @Description TODO
 * @Date 2021/6/30 9:49 上午
 * @Created by mark
 */
@Data
public class BaseOrganization {

    protected Long id;
    protected String orgName;
    protected String orgId;
    protected String parentOrgId;

    // 更基本的字段放到父类,更好扩展,一些重复字段没必要重复定义,一些场景可以直接返回base对象,减少无用返回
}
