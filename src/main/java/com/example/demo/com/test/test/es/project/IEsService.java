package com.example.demo.com.test.test.es.project;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.ClearScrollResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.client.ml.job.results.Bucket;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public interface IEsService {
    Field categories(String type, String fieldName) throws Exception;

    List<Map<String,Object>> getFieldByType(String type) throws Exception;

    EsResult hitsByQuery(String query, String path) throws Exception;

    Stream<EsResult> scrollStream(SearchRequest request, int maxSize) throws Exception;

    SearchResponse searchScroll(SearchRequest request) throws Exception;

    ClearScrollResponse clearScroll(List<String> scrollIds) throws Exception;

    List<Bucket> getSingleSummary(String query, String field, String typePoint) throws Exception;

    String indexRefresh(String index);

    String query(String query, String method, String path) throws Exception;

    String save(String template, String type, String name) throws Exception;

    String update(String template, String type, String name, String uuid, String index) throws Exception;

    String command(String method, String path) throws Exception;

    void refresh(String type);

    String getDateHistogramInterval(String start, String end, String originalInterval);

    long getDateHistogramIntervalMin(String start, String end);

    Map<String,Object> dateHistogram(String query, String path)throws Exception;

    void updateByQueryAsync(UpdateByQueryRequest request, RequestOptions options, ActionListener<BulkByScrollResponse> listener) throws IOException;


    SearchResponse search(SearchRequest request) throws Exception;

    CountResponse count(CountRequest request) throws Exception;

    IndexResponse index(IndexRequest request) throws Exception;



}
