package com.test.mark.zhang.test.other.project.cascade.session;

import com.test.mark.zhang.test.other.project.org.Organization;

import java.util.List;

/**
 * @author by mark
 * @Classname Insitution
 * @Description TODO
 * @Date 2021/7/14 1:19 下午
 */
public class Institution {

    boolean open;

    User user = OtherUtil.getUesr();

    List<SecurityZoneDO> zones;

    Organization institution;

    List<String> orgIds;

    List<String> zoneIds;
}
