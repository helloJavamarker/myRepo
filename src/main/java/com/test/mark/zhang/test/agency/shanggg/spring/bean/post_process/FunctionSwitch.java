package com.test.mark.zhang.test.agency.shanggg.spring.bean.post_process;

import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * @author by mark
 * @Classname FunctionSwitch
 * @Description TODO
 * @Date 2021/7/13 8:03 下午
 */
public class FunctionSwitch {

    public static boolean isSwitchOpened(String swirchName) {
        return StringUtils.isNoneBlank(swirchName);
    }
}
