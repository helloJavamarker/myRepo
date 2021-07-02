package com.test.mark.zhang.util.stream.collect.project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.mark.zhang.test.other.project.org.Organization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname OrgBean
 * @Description TODO
 * @Date 2021/6/30 4:08 下午
 * @Created by mark
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrgBean {

    private Long id;
    private String orgName;

    private String orgId;
    private String parentOrgId;
//    @JsonIgnore
//    private OrgBean parent;
}
