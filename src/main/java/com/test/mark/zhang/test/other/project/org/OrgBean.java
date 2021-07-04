package com.test.mark.zhang.test.other.project.org;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname OrgBean
 * @Description TODO
 * @Date 2021/7/4 1:05 下午
 * @Created by mark
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrgBean {
    private String orgId;
    private String name;
    private String parentOrgId;
}
