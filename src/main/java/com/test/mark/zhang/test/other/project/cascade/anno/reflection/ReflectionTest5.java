package com.test.mark.zhang.test.other.project.cascade.anno.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @Classname ReflectionTest5
 * @Description TODO
 * @Date 2021/7/11 12:22 下午
 * @Created by mark
 */
public class ReflectionTest5 {
    public void test1(Map<String, Person1> map, List<String> list) {
        System.out.println("test1");
    }

    public List<String> test2() {
        System.out.println("test2");
        return null;
    }

    public List<String> test3() throws NoSuchMethodException,NoSuchFieldError {
        System.out.println("test2");
        return null;
    }

    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException {
        Method method = ReflectionTest5.class.getMethod("test1", Map.class, List.class);
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        for (Type genericParameterType : genericParameterTypes) {
            System.out.println("##"+genericParameterType);
            if (genericParameterType instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) genericParameterType).getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    System.out.println(actualTypeArgument);
                }
            }
        }
        System.out.println("============");

        Method method2 = Class.forName("com.test.mark.zhang.test.other.project.cascade.anno.reflection.ReflectionTest5").getMethod("test2");
        Type genericReturnType = method2.getGenericReturnType();// 返回只有一种类型,所以不是数组
        System.out.println(genericReturnType);
        if (genericReturnType instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
            for (Type actualTypeArgument : actualTypeArguments) {
                System.out.println(actualTypeArgument);
            }
        }

        System.out.println(".....");
        Method method3 = Class.forName("com.test.mark.zhang.test.other.project.cascade.anno.reflection.ReflectionTest5").getMethod("test3");
        Type[] genericExceptionTypes = method3.getGenericExceptionTypes();// 参数,返回,异常
        System.out.println(genericExceptionTypes);
//        for (Type genericExceptionType : genericExceptionTypes) {
//            if (genericExceptionType instanceof ParameterizedType) {
//                Type[] actualTypeArguments = ((ParameterizedType) genericExceptionType).getActualTypeArguments();
//                for (Type actualTypeArgument : actualTypeArguments) {
//                    System.out.println(actualTypeArgument);
//                }
//            }
//        }

    }
}
