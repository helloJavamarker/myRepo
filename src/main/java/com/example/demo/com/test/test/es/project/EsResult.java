package com.example.demo.com.test.test.es.project;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class EsResult {
    private long total;
    private String scrollId;
    private List<JSONObject> documents;
    private JSONObject aggregation;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getScrollId() {
        return scrollId;
    }

    public void setScrollId(String scrollId) {
        this.scrollId = scrollId;
    }

    public List<JSONObject> getDocuments() {
        return documents;
    }

    public void setDocuments(List<JSONObject> documents) {
        this.documents = documents;
    }

    public JSONObject getAggregation() {
        return aggregation;
    }

    public void setAggregation(JSONObject aggregation) {
        this.aggregation = aggregation;
    }
}
