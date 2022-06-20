package com.test.mark.zhang.util.spring;

import cn.hutool.core.util.NumberUtil;
import com.test.mark.zhang.util.stream.collect.project.OrgBean;
import org.junit.Test;
import org.springframework.util.ObjectUtils;

/**
 * @author by mark
 * @Classname SpringUtilTest
 * @Description TODO
 * @Date 2022/5/18 1:08 下午
 */
public class SpringUtilTest {

    @Test
    public void objectUtil() {
//        ObjectUtils.isEmpty(null);

        // 获取类名,可以为空
        ObjectUtils.nullSafeClassName(null);
        System.out.println(ObjectUtils.nullSafeClassName("xxx"));
        

        // 传入null,返回0,不会报错,比直接调hashcode好
        System.out.println(ObjectUtils.nullSafeHashCode(new OrgBean()));
        System.out.println(new OrgBean().hashCode());

//        boolean nullSafeEquals(Object o1, Object o2)
        //public static boolean nullSafeEquals(@Nullable Object o1, @Nullable Object o2) {
        //		if (o1 == o2) {
        //			return true;
        //		}
        //		if (o1 == null || o2 == null) {
        //			return false;
        //		}
        //		if (o1.equals(o2)) {
        //			return true;
        //		}
        //		if (o1.getClass().isArray() && o2.getClass().isArray()) {
        //			return arrayEquals(o1, o2);
        //		}
        //		return false;
        //	}
    }

    @Test
    public void testDiv() {
        System.out.println(NumberUtil.div(3, 7, 2));
        System.out.println(NumberUtil.div(3, 6, 2));
        System.out.println(NumberUtil.div(3.0, 6, 2));
    }
}
