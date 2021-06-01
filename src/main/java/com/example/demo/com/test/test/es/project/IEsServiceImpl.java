package com.example.demo.com.test.test.es.project;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.ClearScrollResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.client.ml.job.results.Bucket;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@Slf4j
@Service
public class IEsServiceImpl implements IEsService{

    private Map<String, Map<String, Field>> fieldGroupMap = new ConcurrentHashMap<>();
    private RestClient client;
    //highLevelClient 是新api,使用较多
    private RestHighLevelClient highLevelClient;
    private static Map<String, List<Map<String, Object>>> fields = new ConcurrentHashMap<>();

    //检索时,忽略关闭的索引
    public final static IndicesOptions IGNORE_CLOSED_INDICES  = new IndicesOptions(
            EnumSet.of(IndicesOptions.Option.ALLOW_NO_INDICES,
                    IndicesOptions.Option.FORBID_CLOSED_INDICES,
                    IndicesOptions.Option.IGNORE_THROTTLED,
                    IndicesOptions.Option.IGNORE_UNAVAILABLE),
            EnumSet.of(IndicesOptions.WildcardStates.OPEN));

    @Override
    public Field categories(String type, String fieldName) throws Exception {
        Map<String, Field> tmp = fieldGroupMap.get(type);
        if (tmp != null) {
            return tmp.get(fieldName);
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> getFieldByType(String type) throws Exception {
        if (type == null) {
            type = "search";
        }
        List<Map<String, Object>> maps = IEsServiceImpl.fields.get(type);
        if (!CollectionUtils.isEmpty(maps)) {
            return maps;
        }
        //client.performRequest("GET", getFieldPointByType(type), Collections.<String,String>emptyMap());

        return null;
    }

    @Override
    public EsResult hitsByQuery(String query, String path) throws Exception {
        return null;
    }

    @Override
    public Stream<EsResult> scrollStream(SearchRequest request, int maxSize) throws Exception {
        return null;
    }

    @Override
    public SearchResponse searchScroll(SearchRequest request) throws Exception {
        return null;
    }

    @Override
    public ClearScrollResponse clearScroll(List<String> scrollIds) throws Exception {
        return null;
    }

    @Override
    public List<Bucket> getSingleSummary(String query, String field, String typePoint) throws Exception {
        return null;
    }

    @Override
    public String indexRefresh(String index) {
        return null;
    }

    @Override
    public String query(String query, String method, String path) throws Exception {
        return null;
    }

    @Override
    public String save(String template, String type, String name) throws Exception {
        return null;
    }

    @Override
    public String update(String template, String type, String name, String uuid, String index) throws Exception {
        return null;
    }

    @Override
    public String command(String method, String path) throws Exception {
        return null;
    }

    @Override
    public void refresh(String type) {

    }

    @Override
    public String getDateHistogramInterval(String start, String end, String originalInterval) {
        return null;
    }

    @Override
    public long getDateHistogramIntervalMin(String start, String end) {
        return 0;
    }

    @Override
    public Map<String, Object> dateHistogram(String query, String path) throws Exception {
        return null;
    }

    @Override
    public void updateByQueryAsync(UpdateByQueryRequest request, RequestOptions options, ActionListener<BulkByScrollResponse> listener) throws IOException {

    }

    @Override
    public SearchResponse search(SearchRequest request) throws Exception {
        request.indicesOptions(IGNORE_CLOSED_INDICES);
        log.info("{}", request);
        return highLevelClient.search(request, RequestOptions.DEFAULT);
    }

    @Override
    public CountResponse count(CountRequest request) throws Exception {
        request.indicesOptions(IGNORE_CLOSED_INDICES);
        log.info("{}",request);
        return highLevelClient.count(request, RequestOptions.DEFAULT);
    }

    @Override
    public IndexResponse index(IndexRequest request) throws Exception {
        log.info("{}",request);
        return highLevelClient.index(request, RequestOptions.DEFAULT);
    }


    private static String getFieldPointByType(String type) {
        if (StringUtils.isEmpty(type)) {
            return "/ailpha-securitylog-*/_search";
        }
        return "xxx";
    }
}
