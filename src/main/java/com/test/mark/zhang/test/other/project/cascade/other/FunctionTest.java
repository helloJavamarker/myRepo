package com.test.mark.zhang.test.other.project.cascade.other;

import cn.hutool.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author by mark
 * @Classname FunctionTest
 * @Description TODO
 * @Date 2021/7/15 9:06 上午
 */
public class FunctionTest {

    private Function<List<JSONObject>, List<JSONObject>> getDicMapping() {

        return datas -> {
            List<JSONObject> result = new ArrayList<>();
            for (JSONObject data : datas) {
                if (StringUtils.isNoneBlank(data.getStr("startTime"))) {
                    data.put("startTime", data.getStr("startTime"));
                }
                result.add(data);
            }
            return result;
        };
    }
}
