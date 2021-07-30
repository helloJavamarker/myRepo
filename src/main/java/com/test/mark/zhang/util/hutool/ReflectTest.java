package com.test.mark.zhang.util.hutool;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.TypeUtil;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author by mark
 * @Classname ReflectTest
 * @Description TODO
 * @Date 2021/7/19 8:11 下午
 */
public class ReflectTest {
    @Test
    public void method() {
        Method[] methods = ReflectUtil.getMethods(IOTest.class);
        Method method = ReflectUtil.getMethod(IOTest.class, "getId");  //null 并不会抛异常

        ReflectUtil.newInstance(IOTest.class);
    }

    public void type() {
        //获取方法的参数和返回值类型（包括Type和Class）
        //获取泛型参数类型（包括对象的泛型参数或集合元素的泛型类型）

        Method method = ReflectUtil.getMethod(TestClass.class, "intTest", Integer.class);
        Type type = TypeUtil.getParamType(method, 0);
        // 结果：Integer.class

        Method method2 = ReflectUtil.getMethod(TestClass.class, "getList");
        Type type2 = TypeUtil.getReturnType(method);
        // 结果：java.util.List<java.lang.String>

        Method method3 = ReflectUtil.getMethod(TestClass.class, "getList");
        Type type3 = TypeUtil.getReturnType(method);

        Type type4 = TypeUtil.getTypeArgument(type);
        // 结果：String.class
    }


    //泛型类
    class TestClass {
        public List<String> getList(){
            return new ArrayList<>();
        }

        public Integer intTest(Integer integer) {
            return 1;
        }
    }
}
