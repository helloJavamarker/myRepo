package com.test.mark.zhang.test.agency.shengsy.java8.project;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

/**
 * @Classname FunctionTest
 * @Description TODO
 * @Date 2021/6/4 4:37 下午
 * @Created by mark
 */
public class FunctionTest {
    public static void main(String[] args) {
        QueryBuilders.rangeQuery("apple").from("");
        QueryBuilders.boolQuery();
        QueryBuilders.fuzzyQuery("bb","ll");
        QueryBuilders.fuzzyQuery("bb","ll");

    }
}
