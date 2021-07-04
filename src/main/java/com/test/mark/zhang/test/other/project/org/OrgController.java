package com.test.mark.zhang.test.other.project.org;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Classname OrgController
 * @Description TODO
 * @Date 2021/6/22 2:21 下午
 * @Created by mark
 */
@RestController
@RequestMapping("org")
public class OrgController {

    private static List<Organization> allOrgsCache = new ArrayList<>();
    @PostConstruct
    public void init() {
        //select from db
        //insert 总部data
        Organization organization = new Organization();
        organization.setId(101L);
        organization.setOrgName("直属");
        organization.setAbbreviation(null);
        organization.setOrgId("101xx");
        organization.setParentOrgId("100xx");
        organization.setOrgIdentifier("");
        organization.setOrgAddress("直属");
        organization.setOrgArea("直属");
        allOrgsCache.add(organization);
        allOrgsCache = generateData();
        //filter 第二级
        allOrgsCache = allOrgsCache.stream().peek(org -> {
            if ("100xx".equals(org.getParentOrgId()) && !"101xx".equals(org.getOrgId())) {
                org.setParentOrgId("101xx");
            }
        }).collect(Collectors.toList());
        //将第二级指向总部
        //注意判空  下次更新版本删除数据
    }

    @GetMapping("get")
    public Map<String, Organization> get() {
        Map<String, Organization> orgIdMapTree = allOrgsCache.stream()
                .collect(Collectors.toMap(Organization::getOrgId, org -> org, (o1, o2) -> o1));
        orgIdMapTree.forEach((orgId, organization) -> {
            String parentId = organization.getParentOrgId();
            Organization parent = orgIdMapTree.get(parentId);
            if (parent != null) {
                organization.setParent(parent);
                List<Organization> children = parent.getChildren();
                if (children == null) {
                    parent.setChildren(new ArrayList<>());
                }
                children.add(organization);
                //children.sort((c1, c2) -> (int)(c1.getId() - c2.getId()));
                parent.setLeaf(false);
            }
            if (organization.getChildren() == null || organization.getChildren().size() == 0) {
                organization.setLeaf(true);
            }
        });
        return orgIdMapTree;
    }

    public Map<String, Organization> get2() {
        Map<String, Organization> orgIdMapTree = generateData().stream()
                .collect(Collectors.toMap(Organization::getOrgId, org -> org, (o1, o2) -> o1));
        orgIdMapTree.forEach((orgId, organization) -> {
            String parentId = organization.getParentOrgId();
            Organization parent = orgIdMapTree.get(parentId);
            if (parent != null) {
                organization.setParent(parent);
                List<Organization> children = parent.getChildren();
                if (children == null) {
                    parent.setChildren(new ArrayList<>());
                }
                children.add(organization);
                //children.sort((c1, c2) -> (int)(c1.getId() - c2.getId()));
                parent.setLeaf(false);
            }
            if (organization.getChildren() == null || organization.getChildren().size() == 0) {
                organization.setLeaf(true);
            }
        });
        return orgIdMapTree;
    }

    public static void main(String[] args) {
        Map<String, Organization> orgIdMapTree = generateData().stream()
                .collect(Collectors.toMap(Organization::getOrgId, org -> org, (o1, o2) -> o1));
        orgIdMapTree.forEach((orgId, organization) -> {
            String parentId = organization.getParentOrgId();
            Organization parent = orgIdMapTree.get(parentId);
            if (parent != null) {
                organization.setParent(parent);
                List<Organization> children = parent.getChildren();
                if (children == null) {
                    parent.setChildren(new ArrayList<>());
                }
                children.add(organization);
                //children.sort((c1, c2) -> (int)(c1.getId() - c2.getId()));
                parent.setLeaf(false);
            }
            if (organization.getChildren() == null || organization.getChildren().size() == 0) {
                organization.setLeaf(true);
            }
        });
        System.out.println(orgIdMapTree.get("200xx"));
    }
    @GetMapping("getAllChildren")
    public List<Organization> getAllChildren(String branchId) {
        Map<String, Organization> orgIdMapTree = allOrgsCache.stream()
                .collect(Collectors.toMap(Organization::getOrgId, org -> org, (o1, o2) -> o1));
        orgIdMapTree.forEach((orgId, organization) -> {
            String parentId = organization.getParentOrgId();
            Organization parent = orgIdMapTree.get(parentId);
            if (parent != null) {
                organization.setParent(parent);
                List<Organization> children = parent.getChildren();
                if (children == null) {
                    parent.setChildren(new ArrayList<>());
                }
                children.add(organization);
                //children.sort((c1, c2) -> (int)(c1.getId() - c2.getId()));
                parent.setLeaf(false);
            }
            if (organization.getChildren() == null || organization.getChildren().size() == 0) {
                organization.setLeaf(true);
            }
        });
        //orgIdMapTree.v
        List<Organization> orgs =new ArrayList<>();
        for (Map.Entry<String, Organization> orgEntry : orgIdMapTree.entrySet()) {
            if (branchId.equals(orgEntry.getKey())) {
                orgs.add(orgEntry.getValue());

            }
        }

        //得到某个子树
        //遍历子树


        return new ArrayList<>();
    }


    private List<Organization> getAllChildren() {
        return null;
    }




    private static List<Organization> generateData() {
        Organization organization1 = new Organization();
        organization1.setId(100L);
        organization1.setOrgName("总部");
        organization1.setAbbreviation(null);
        organization1.setOrgId("100xx");
        organization1.setParentOrgId(null);
        organization1.setOrgIdentifier("");
        organization1.setOrgAddress("");
        organization1.setOrgArea("");

        Organization organization2 = new Organization();
        organization2.setId(200L);
        organization2.setOrgName("浙江");
        organization2.setAbbreviation(null);
        organization2.setOrgId("200xx");
        organization2.setParentOrgId("100xx");
        organization2.setOrgIdentifier("");
        organization2.setOrgAddress("浙江");
        organization2.setOrgArea("浙江");

        Organization organization3 = new Organization();
        organization3.setId(300L);
        organization3.setOrgName("杭州");
        organization3.setAbbreviation(null);
        organization3.setOrgId("300xx");
        organization3.setParentOrgId("200xx");
        organization3.setOrgIdentifier("");
        organization3.setOrgAddress("杭州");
        organization3.setOrgArea("杭州");

        Organization organization4 = new Organization();
        organization4.setId(310L);
        organization4.setOrgName("宁波");
        organization4.setAbbreviation(null);
        organization4.setOrgId("301xx");
        organization4.setParentOrgId("200xx");
        organization4.setOrgIdentifier("");
        organization4.setOrgAddress("宁波");
        organization4.setOrgArea("宁波");

        Organization organization5 = new Organization();
        organization5.setId(300L);
        organization5.setOrgName("广东");
        organization5.setAbbreviation(null);
        organization5.setOrgId("400xx");
        organization5.setParentOrgId("100xx");
        organization5.setOrgIdentifier("");
        organization5.setOrgAddress("广东");
        organization5.setOrgArea("广东");

        Organization organization6 = new Organization();
        organization6.setId(2L);
        organization6.setOrgName("广州");
        organization6.setAbbreviation(null);
        organization6.setOrgId("401xx");
        organization6.setParentOrgId("400xx");
        organization6.setOrgIdentifier("");
        organization6.setOrgAddress("广州");
        organization6.setOrgArea("广州");

        Organization organization7 = new Organization();
        organization7.setId(202L);
        organization7.setOrgName("滨江");
        organization7.setAbbreviation(null);
        organization7.setOrgId("402xx");
        organization7.setParentOrgId("300xx");
        organization7.setOrgIdentifier("");
        organization7.setOrgAddress("滨江");
        organization7.setOrgArea("滨江");

        Organization organization8 = new Organization();
        organization7.setId(202L);
        organization7.setOrgName("西湖");
        organization7.setAbbreviation(null);
        organization7.setOrgId("403xx");
        organization7.setParentOrgId("300xx");
        organization7.setOrgIdentifier("");
        organization7.setOrgAddress("滨江");
        organization7.setOrgArea("滨江");

        Organization organization9 = new Organization();
        organization7.setId(202L);
        organization7.setOrgName("余杭");
        organization7.setAbbreviation(null);
        organization7.setOrgId("404xx");
        organization7.setParentOrgId("300xx");
        organization7.setOrgIdentifier("");
        organization7.setOrgAddress("滨江");
        organization7.setOrgArea("滨江");

        allOrgsCache.add(organization1);
        allOrgsCache.add(organization2);
        allOrgsCache.add(organization3);
        allOrgsCache.add(organization4);
        allOrgsCache.add(organization5);
        allOrgsCache.add(organization6);
        allOrgsCache.add(organization7);
        allOrgsCache.add(organization8);
        allOrgsCache.add(organization9);
        return allOrgsCache;
    }


}
