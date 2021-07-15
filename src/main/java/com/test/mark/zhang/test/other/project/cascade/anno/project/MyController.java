package com.test.mark.zhang.test.other.project.cascade.anno.project;

import com.test.mark.zhang.test.other.project.org.Organization;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname MyController
 * @Description TODO
 * @Date 2021/7/11 2:59 下午
 * @Created by mark
 */
@RestController
@RequestMapping("anno/cas")
public class MyController {

    private static List<Organization> allOrgsCache = new ArrayList<>();
    @PostConstruct
    public void initData() {
        allOrgsCache = generateData();
    }

    @GetMapping("getAllOrg")
    public List<Organization> getAllOrg() {

        return allOrgsCache;
    }

    @GetMapping("getOrgById")
    public Organization getOrgById(@RequestParam("orgId")String orgId) {
        return allOrgsCache.stream().filter(organization -> orgId.equals(organization.getOrgId())).collect(Collectors.toList()).get(0);
    }

    @PostMapping("updateOrgOld")
    public String updateOrgOld(@RequestBody Organization organization) {
        if (organization != null) {
            deleteCache(organization);
            //updateDB
            addCache(organization);
        }
        return "success";
    }

    @PostMapping("updateOrgNew")
    @CacheUpdate(key = "orgList")
    public Organization updateOrgNew(@RequestBody Organization organization) {
        if (organization != null) {

        }
        return null;
    }

    private void addCache(Organization organization) {
        allOrgsCache.add(organization);
    }

    private void deleteCache(Organization organization) {
        List<Organization> filter = allOrgsCache.stream().filter(org -> organization.getOrgId().equals(org.getOrgId())).collect(Collectors.toList());
        allOrgsCache.removeAll(filter);
    }


    @CacheContainer("orgList")
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

        // 将二级节点
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
