package com.test.mark.zhang.test.other.project.org;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Classname Organization
 * @Description TODO
 * @Date 2021/6/22 2:10 下午
 * @Created by mark
 */
@Getter
@Setter
@ToString(exclude = {"parent"})
public class Organization extends BaseOrganization implements Serializable {

//    private Long id;
//
//    private String orgName;

    private String abbreviation;
//    private String orgId;
//    private String parentOrgId;

    private String orgIdentifier;
    private String orgAddress;
    private String orgArea;
    private Boolean leaf;
    private Boolean loading;
    private String title;
    private Boolean selected = false;
    private Boolean selectAll;
    //是否展开
    private Boolean expand;

    @JsonIgnore
    private Organization parent;
    //private Set<Organization> children = new HashSet<>();
    private List<Organization> children = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Organization that = (Organization) o;
        return Objects.equals(id, that.id) && Objects.equals(orgName, that.orgName) && Objects.equals(abbreviation, that.abbreviation) && Objects.equals(orgId, that.orgId) && Objects.equals(parentOrgId, that.parentOrgId) && Objects.equals(orgIdentifier, that.orgIdentifier) && Objects.equals(orgAddress, that.orgAddress) && Objects.equals(orgArea, that.orgArea) && Objects.equals(leaf, that.leaf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orgName, abbreviation, orgId, parentOrgId, orgIdentifier, orgAddress, orgArea, leaf);
    }
}
