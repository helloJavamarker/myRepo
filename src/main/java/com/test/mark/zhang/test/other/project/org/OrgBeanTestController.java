package com.test.mark.zhang.test.other.project.org;



import com.test.mark.zhang.test.agency.heima.disign.day2.pattern.builder.demo1.OfoBuilder;
import org.junit.Test;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Classname OrgBeanTest
 * @Description TODO
 * @Date 2021/7/4 1:06 下午
 * @Created by mark
 */

@RestController("/org/cas")
public class OrgBeanTestController {
    //private List<OrgBean> result = new ArrayList<>();
    private final List<String> thisChildren = new ArrayList<>();
    public static void main(String[] args) {
        OrgBeanTestController orgController = new OrgBeanTestController();
        orgController.findChildren2();
//        OrgBeanTest orgBeanTest = new OrgBeanTest();
//        System.out.println(new OrgBeanTest().findChildren("200xx", generateData()));
//        System.out.println(new OrgBeanTest().findChildren("210xx", generateData()));
//        System.out.println(new OrgBeanTest().findChildren("220xx", generateData()));
//        System.out.println(new OrgBeanTest().findChildren("220xyy", generateData()));
    }

    @GetMapping("/children")
    public String getChildren(@RequestParam("orgId") String orgId) {
        System.out.println(findChildren(orgId, generateData()));
        return "success";
    }

    @GetMapping("children2")
    public String getChildren2() {
        findChildren("200xx",generateData());
        return "success";
    }

    private  List<OrgBean> findChildren2() {
        OrgBeanTestController orgBeanTestController = new OrgBeanTestController();
        OrgController orgController = new OrgController();
        Map<String, Organization> organizationMap = orgController.get2();
//        System.out.println(organizationMap.get("200xx").getChildren());
//        System.out.println("...");
//        System.out.println(organizationMap);
//
//        System.out.println("..................");
//        addChild(organizationMap.get("200xx"));
//        addChilds(organizationMap.get("200xx"));
//        addChilds(organizationMap.get("100xx"));
//        addChilds(organizationMap.get("400xx"));

        System.out.println(System.currentTimeMillis());
        System.out.println(orgBeanTestController.addChilds2(organizationMap.get("200xx")));
        System.out.println(System.currentTimeMillis());
        orgBeanTestController.thisChildren.clear();
        //thisChildren = null;
        System.out.println(orgBeanTestController.addChilds2(organizationMap.get("100xx")));
        System.out.println(System.currentTimeMillis());
        orgBeanTestController.thisChildren.clear();
        //thisChildren = null;
        System.out.println(orgBeanTestController.addChilds2(organizationMap.get("400xx")));
        orgBeanTestController.thisChildren.clear();
        //thisChildren = null;
        System.out.println(orgBeanTestController.addChilds2(organizationMap.get("300xx")));
        orgBeanTestController.thisChildren.clear();
        //thisChildren = null;
        //System.out.println(orgBeanTestController.addChilds2(organizationMap.get("500")));

        return null;
    }

    private List<String> addChilds2(Organization organization) {

        List<String> children = new ArrayList<>();
        while(!CollectionUtils.isEmpty(organization.getChildren())) {
            for (Organization org : organization.getChildren()) {
                //thisChildren.add(org.getOrgId());
                children.add(org.getOrgId());
                organization = org;  //关键是这一步
                //thisChildren.removeAll();
                addChilds2(organization);
            }
        }
//        System.out.println("///////");
//        System.out.println(thisChildren);
        return children;
    }

    private List<String> getChildren(Organization organization) {
        List<String> children = new ArrayList<>();
        while(!CollectionUtils.isEmpty(organization.getChildren())) {
            for (Organization org : organization.getChildren()) {
                //thisChildren.add(org.getOrgId());
                children.add(org.getOrgId());
                organization = org;  //关键是这一步
                //thisChildren.removeAll();
                addChilds2(organization);
            }
        }
        return children;
    }

    @Test
    public void test02() {
        List<OrgBean> orgBeans = generateData();

    }

    private static List<String> addChilds(Organization organization) {
        List<String> children =new ArrayList<>();
        while(!CollectionUtils.isEmpty(organization.getChildren())) {
            for (Organization org : organization.getChildren()) {
                children.add(org.getOrgId());
                organization = org;  //关键是这一步
            }
        }
        System.out.println("///////");
        System.out.println(children);
        return children;
    }

    private static List<String> addChild(Organization organization) {
        List<String> children =new ArrayList<>();
        if (!CollectionUtils.isEmpty(organization.getChildren())) {
            organization.getChildren().forEach(org -> {
                children.add(org.getOrgId());
                addChild(org);
            });
        }
        System.out.println(children);
        return children;
    }

    private  List<OrgBean> findChildren(String orgId, List<OrgBean> list) {
        List<OrgBean> result = new ArrayList<>();
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        if (StringUtils.isEmpty(orgId)) {
            throw new RuntimeException("非法参数:orgId");
        } else {
            List<OrgBean> children = list.stream().filter(org -> orgId.equals(org.getParentOrgId())).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(children)) {
                return result;
            }
            result.addAll(children);
            children.forEach(orgBean -> {
                findChildren(orgBean.getOrgId(),list);
            });
        }
        //System.out.println("result:" + result);
        return result;
    }
    private  List<OrgBean> generateData() {
        OrgBean bean1 = new OrgBean("100xx", "总部", null);
        OrgBean bean2 = new OrgBean("200xx", "直属", "100xx");
        OrgBean bean3 = new OrgBean("300xx", "浙江", "200xx");
        OrgBean bean4 = new OrgBean("400xx", "广东", "200xx");
        OrgBean bean5 = new OrgBean("210xx", "分支机构1", "100xx");
        OrgBean bean6 = new OrgBean("220xx", "分支机构2", "100xx");
        OrgBean bean7 = new OrgBean("310xx", "杭州", "300xx");
        OrgBean bean8 = new OrgBean("320xx", "宁波", "300xx");
        OrgBean bean9 = new OrgBean("410xx", "广州", "400xx");
        OrgBean bean10 = new OrgBean("311xx", "滨江", "310xx");
        OrgBean bean11 = new OrgBean("211xx", "局域网1", "210xx");
        OrgBean bean12 = new OrgBean("112xx", "局域网2", "210xx");
        return Arrays.asList(bean1,bean2,bean3,bean4,bean5,bean6,bean7,bean8,bean9,bean10,bean11,bean12);

    }
}
