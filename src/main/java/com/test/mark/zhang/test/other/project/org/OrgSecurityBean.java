package com.test.mark.zhang.test.other.project.org;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname OrgSecurityBean
 * @Description TODO
 * @Date 2021/7/6 7:22 下午
 * @Created by mark
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrgSecurityBean {

    private String orgId;
    private String securityZoneId;
}
