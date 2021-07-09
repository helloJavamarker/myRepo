package com.test.mark.zhang.test.other.project.org;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.test.mark.zhang.test.agency.shanggg.java8.day2.java8.TestForkJoin.test3;
//使用静态导入方法,可以减少代码量,注意不要导入*,否则语义不明确,滥用静态导入会使程序更难阅读，更难维护。 假如同名静态方法有很多,不建议使用静态导入

/**
 * @Classname OrgTest
 * @Description TODO
 * @Date 2021/7/6 7:23 下午
 * @Created by mark
 */
public class OrgTest {
    public static void main(String[] args) {
        test3();
        OrgTest orgTest = new OrgTest();
        orgTest.relation();
        System.out.println(orgTest.getBranch("org5"));
        System.out.println(orgTest.getBranch("org4"));
        System.out.println(orgTest.getBranch("org3"));

        Map<String, String> map = new HashMap<>();
        map.put("zhang","san");
        map.put("zhang1","san1");
        map.put("","empty");
        map.put("zhang2","san2");
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            map.put("li","si");
//        }

    }

    private Map<String, List<String>> relation() {
        Map<String, List<OrgSecurityBean>> map = generateData().stream().collect(Collectors.groupingBy(OrgSecurityBean::getOrgId));
        System.out.println(map);

        Map<String, List<String>> result = new HashMap<>();
        for (Map.Entry<String, List<OrgSecurityBean>> entry : map.entrySet()) {
            result.put(entry.getKey(), entry.getValue().stream().map(OrgSecurityBean::getSecurityZoneId).collect(Collectors.toList()));
        }
        System.out.println(result);

        //generateData().stream().collect(Collectors.groupingBy(OrgSecurityBean::getOrgId), )

        return null;
    }

    private OrgBean getBranch(String orgId) {
        for (OrgBean org : generateOrgData()) {
            if (orgId.equals(org.getOrgId())) {
                //if ("org1".equals(org.getParentOrgId())) {
                    //return getBranch(org.getParentOrgId());
                //}
                if (!"org1".equals(org.getParentOrgId())) {
                    return getBranch(org.getParentOrgId());
                } else {
                    return org;
                }
            }
        }
        return null;
    }

    private List<OrgBean> generateOrgData() {
        OrgBean bean1 = new OrgBean("org1", "总部",null);
        OrgBean bean2 = new OrgBean("org2", "直属","org1");
        OrgBean bean3 = new OrgBean("org3", "分支1","org1");
        OrgBean bean4 = new OrgBean("org4", "浙江","org2");
        OrgBean bean5 = new OrgBean("org5", "杭州","org4");
        OrgBean bean6 = new OrgBean("org6", "滨江","org5");
        OrgBean bean7 = new OrgBean("org7", "宁波","org4");
        OrgBean bean8 = new OrgBean("org8", "局域网1","org3");
        return Arrays.asList(bean1,bean2,bean3,bean4,bean5,bean6,bean7,bean8);
    }

    private List<OrgSecurityBean> generateData() {
        OrgSecurityBean bean1 = new OrgSecurityBean("org1", "securityZone1");
        OrgSecurityBean bean2 = new OrgSecurityBean("org1", "securityZone2");
        OrgSecurityBean bean3 = new OrgSecurityBean("org1", "securityZone3");
        OrgSecurityBean bean4 = new OrgSecurityBean("org2", "securityZone1");
        OrgSecurityBean bean5 = new OrgSecurityBean("org2", "securityZone4");
        OrgSecurityBean bean6 = new OrgSecurityBean("org2", "securityZone5");
        OrgSecurityBean bean7 = new OrgSecurityBean("org3", "securityZone1");
        OrgSecurityBean bean8 = new OrgSecurityBean("org3", "securityZone6");
        return Arrays.asList(bean1,bean2,bean3,bean4,bean5,bean6,bean7,bean8);
    }
}
