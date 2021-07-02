package com.test.mark.zhang.test.other.project.study;

import org.jaxen.function.FalseFunction;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//静态导入
import static com.test.mark.zhang.test.other.project.study.Tuple.map;
import static com.test.mark.zhang.test.other.project.study.Tuple.tuple;

/**
 * @Classname TupleTest
 * @Description TODO
 * @Date 2021/6/25 4:31 下午
 * @Created by mark
 */
public class TupleTest {

    public static List<Map<String, Object>> BOOLEAN_ENUMS = Arrays.asList(
            map(tuple("key",true),tuple("name","是")),
            map(tuple("key", false),tuple("name","否")),
            map(tuple("key1", "false")),
            map(tuple("key2", "false2")),
            map(tuple("key2",""))
    );
    public static void main(String[] args) {
        System.out.println(BOOLEAN_ENUMS);
        //[{name=是, key=true}, {name=否, key=false}]
        //[{name=是, key=true}, {name=否, key=false}, {key1=false}, {key2=false2}]
        //[{name=是, key=true}, {name=否, key=false}, {key1=false}, {key2=false2}, {key2=}]
    }
}
