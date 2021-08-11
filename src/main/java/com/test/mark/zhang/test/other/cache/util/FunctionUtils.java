package com.test.mark.zhang.test.other.cache.util;


import com.test.mark.zhang.test.other.cache.core.NAFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by mark
 * @Classname FunctionUtils
 * @Description TODO
 * @Date 2021/7/26 9:24 下午
 */
public class FunctionUtils {

    public static Map<Integer, Map<Object, Object>> syncCreate(NAFunction f1, NAFunction f2) {
        f1.apply();
        f2.apply();
        return new HashMap<>();
    }
}
