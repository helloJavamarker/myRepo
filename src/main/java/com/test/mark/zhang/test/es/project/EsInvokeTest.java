package com.test.mark.zhang.test.es.project;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class EsInvokeTest {

    @Autowired
    private static IEsService esService;
    private static final String TIMESTAMP = "@timestamp";


    public static void main(String[] args) {

    }


    public static SearchResponse subCount(String[] indices, String timeField, Date startTime, Date endTime,
                                          QueryBuilder query, String subField, int size) throws Exception {
        String[] fields = subField.split(",");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(addTimeQuery(timeField, startTime, endTime, query)).fetchSource(false)
                .aggregation(getSubAgg(fields, size, fields.length, null));
        SearchRequest request = new SearchRequest(indices, builder);
        return esService.search(request);
    }

    private static AggregationBuilder getSubAgg(String[] fields, int size, int count, TermsAggregationBuilder termAgg) {
        count--;
        if (count >= 0) {
            if (count == fields.length -1) {
                return getSubAgg(fields, size, count,
                        AggregationBuilders.terms(fields[fields.length - 1]).field(fields[fields.length -1]).size(size));
            } else {
                return AggregationBuilders.terms(fields[count]).field(fields[count]).size(size)
                        .subAggregation(termAgg);
            }
        }
        return termAgg;
    }

    private static QueryBuilder addTimeQuery(String timeField, Date startTime, Date endTime, QueryBuilder query) {
        if (query != null) {
            return QueryBuilders.boolQuery().must(getTimeRangeQuery(timeField, startTime, endTime)).must(query);
        }
        return getTimeRangeQuery(timeField,startTime,endTime);
    }

    private static QueryBuilder getTimeRangeQuery(String timeField, Date startTime, Date endTime) {
        if (TIMESTAMP.equals(timeField)) {
            return QueryBuilders.rangeQuery(timeField).from(startTime, true).to(endTime,true);
        }else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return QueryBuilders.rangeQuery(timeField).from(format.format(startTime), true)
                    .to(format.format(endTime),true);
        }
    }
}
