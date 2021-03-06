package com.test.mark.zhang.test.es.project;
import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.ActiveShardCount;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CloseIndexRequest;
import org.elasticsearch.client.indices.CloseIndexResponse;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.VersionType;
import org.elasticsearch.index.get.GetResult;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ResrHighLevelTest {
//    RestHighLevelClient client = new RestHighLevelClient(
//            RestClient.builder(
//                    new HttpHost("localhost", 9200, "http"),
//                    new HttpHost("localhost", 9201, "http")));

    public void createIndex() {

         RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http")));

        CreateIndexRequest request = new CreateIndexRequest("twitter_two");//????????????
        //???????????????????????????????????????????????????????????????
            request.settings(Settings.builder()
                    .put("index.number_of_shards", 3)
                    .put("index.number_of_replicas", 2)
            );
        //???????????????????????????????????????
            request.mapping("tweet",//????????????
                    "  {\n" +
                    "    \"tweet\": {\n" +
                    "      \"properties\": {\n" +
                    "        \"message\": {\n" +
                    "          \"type\": \"text\"\n" +
                    "        }\n" +
                    "      }\n" +
                    "    }\n" +
                    "  }",//?????????????????????????????????JSON?????????
        XContentType.JSON);

        //???????????????????????????
            request.alias(
                    new Alias("twitter_alias")
            );
        //????????????
            request.timeout(TimeValue.timeValueMinutes(2));//??????,???????????????????????????(??????TimeValue??????)
        //request.timeout("2m");//??????,???????????????????????????(?????????????????????)

            request.masterNodeTimeout(TimeValue.timeValueMinutes(1));//??????master?????????????????????(??????TimeValue??????)
        //request.masterNodeTimeout("1m");//??????master?????????????????????(?????????????????????)

            request.waitForActiveShards(2);//???????????????API????????????????????????????????????????????????????????????int???????????????
        //request.waitForActiveShards(ActiveShardCount.DEFAULT);//???????????????API????????????????????????????????????????????????????????????ActiveShardCount???????????????

        //????????????
        //CreateIndexResponse createIndexResponse = client.indices().create(request);
        //????????????
        //???????????????????????????????????????CreateIndexRequest?????????ActionListener??????????????????????????????
        //CreateIndexResponse?????????????????????????????????
        //??????????????????????????????????????????
        ActionListener<CreateIndexResponse> listener = new ActionListener<CreateIndexResponse>() {
            @Override
            public void onResponse(CreateIndexResponse createIndexResponse) {
                //??????????????????????????????onResponse??????;
            }
            @Override
            public void onFailure(Exception e) {
                //????????????????????????onFailure?????????
            }
        };
            //client.indices().createAsync(request);
            client.indices().createAsync(request,RequestOptions.DEFAULT,null);
            //????????????CreateIndexRequest??????????????????????????????ActionListener

        //?????????CreateIndexResponse????????????????????????????????????????????????????????????
//        boolean acknowledged = createIndexResponse.isAcknowledged();//??????????????????????????????????????????
//        boolean shardsAcknowledged = createIndexResponse.isShardsAcknowledged();//???????????????????????????????????????????????????????????????????????????????????????

    }

    public void deleteIndex() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http")));

        DeleteIndexRequest request = new DeleteIndexRequest("twitter_two");//??????????????????????????????
        //???????????????
        request.timeout(TimeValue.timeValueMinutes(2)); //????????????????????????????????????????????????????????????TimeValue?????????
        // request.timeout("2m"); //??????????????????????????????????????????????????????????????????????????????

        request.masterNodeTimeout(TimeValue.timeValueMinutes(1));////??????master?????????????????????(??????TimeValue??????)
        // request.masterNodeTimeout("1m");//??????master?????????????????????(?????????????????????)

        //??????IndicesOptions????????????????????????????????????????????????????????????????????????
        request.indicesOptions(IndicesOptions.lenientExpandOpen());

        //????????????
        AcknowledgedResponse deleteIndexResponse = client.indices().delete(request, RequestOptions.DEFAULT);

  /*    //???????????????????????????????????????DeleteIndexRequest?????????ActionListener??????????????????????????????
        //DeleteIndexResponse?????????????????????????????????
        //??????????????????????????????????????????
        ActionListener<DeleteIndexResponse> listener = new ActionListener<DeleteIndexResponse>() {
            @Override
            public void onResponse(DeleteIndexResponse deleteIndexResponse) {
                //??????????????????????????????onResponse??????;
            }

            @Override
            public void onFailure(Exception e) {
                //????????????????????????onFailure?????????
            }
        };
        client.indices().deleteAsync(request, listener);*/

        //Delete Index Response
        //?????????DeleteIndexResponse????????????????????????????????????????????????????????????
        boolean acknowledged = deleteIndexResponse.isAcknowledged();//????????????????????????????????????


        //????????????????????????????????????ElasticsearchException???
        try {
            request = new DeleteIndexRequest("does_not_exist");
            client.indices().delete(request,RequestOptions.DEFAULT);
        } catch (ElasticsearchException exception) {
//            if (exception.status() == RestStatus.NOT_FOUND) {
//                //????????????????????????????????????????????????????????????
//            }
        }
    }

    public void openIndex() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http")));

        OpenIndexRequest request = new OpenIndexRequest("twitter");//????????????

        //???????????????
        request.timeout(TimeValue.timeValueMinutes(2)); //???????????????????????????????????????????????????????????????TimeValue?????????
        // request.timeout("2m"); //?????????????????????????????????????????????????????????????????????????????????

        request.masterNodeTimeout(TimeValue.timeValueMinutes(1));////??????master?????????????????????(??????TimeValue??????)
        // request.masterNodeTimeout("1m");//??????master?????????????????????(?????????????????????)

        request.waitForActiveShards(2);//???????????????API????????????????????????????????????????????????????????????int???????????????
        //request.waitForActiveShards(ActiveShardCount.ONE);//???????????????API????????????????????????????????????????????????????????????ActiveShardCount???????????????

        //??????IndicesOptions????????????????????????????????????????????????????????????????????????
        request.indicesOptions(IndicesOptions.strictExpandOpen());

        //????????????
        OpenIndexResponse openIndexResponse = client.indices().open(request,RequestOptions.DEFAULT);

        /*//???????????????????????????????????????OpenIndexRequest?????????ActionListener??????????????????????????????
        //OpenIndexResponse?????????????????????????????????
        //??????????????????????????????????????????
        ActionListener<OpenIndexResponse> listener = new ActionListener<OpenIndexResponse>() {
            @Override
            public void onResponse(OpenIndexResponse openIndexResponse) {
                //??????????????????????????????onResponse??????;
            }

            @Override
            public void onFailure(Exception e) {
                //????????????????????????onFailure?????????
            }
        };
        client.indices().openAsync(request, listener);*/

        //Open Index Response
        //?????????OpenIndexResponse????????????????????????????????????????????????????????????
        boolean acknowledged = openIndexResponse.isAcknowledged();//??????????????????????????????????????????
        boolean shardsAcknowledged = openIndexResponse.isShardsAcknowledged();//???????????????????????????????????????????????????????????????????????????????????????
    }

    public void closeIndex() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http")));
        CloseIndexRequest request = new CloseIndexRequest("index");//????????????

        //???????????????
        //request.timeout(TimeValue.timeValueMinutes(2)); //???????????????????????????????????????????????????????????????TimeValue?????????
        // request.timeout("2m"); //?????????????????????????????????????????????????????????????????????????????????

        //request.masterNodeTimeout(TimeValue.timeValueMinutes(1));////??????master?????????????????????(??????TimeValue??????)
        // request.masterNodeTimeout("1m");//??????master?????????????????????(?????????????????????)

        //??????IndicesOptions????????????????????????????????????????????????????????????????????????
        request.indicesOptions(IndicesOptions.lenientExpandOpen());
        //????????????
        CloseIndexResponse closeIndexResponse = client.indices().close(request,RequestOptions.DEFAULT);

         /*//???????????????????????????????????????CloseIndexRequest?????????ActionListener??????????????????????????????
        //CloseIndexResponse?????????????????????????????????
        //??????????????????????????????????????????
        ActionListener<CloseIndexResponse> listener = new ActionListener<CloseIndexResponse>() {
            @Override
            public void onResponse(CloseIndexResponse closeIndexResponse) {
                 //??????????????????????????????onResponse??????;
            }

            @Override
            public void onFailure(Exception e) {
                 //????????????????????????onFailure?????????
            }
        };
        client.indices().closeAsync(request, listener); */

        //Close Index Response
        //?????????CloseIndexResponse ????????????????????????????????????????????????????????????
        boolean acknowledged = closeIndexResponse.isAcknowledged(); //??????????????????????????????????????????
    }

    public void index() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http")));
        IndexRequest indexRequest1 = new IndexRequest(
                "posts",//????????????
                "doc",//????????????
                "1");//??????ID

        //==============================???????????????========================================
        //??????1???????????????????????????
        String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        indexRequest1.source(jsonString, XContentType.JSON);

        //??????2??????Map????????????
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user", "kimchy");
        jsonMap.put("postDate", new Date());
        jsonMap.put("message", "trying out Elasticsearch");
        //Map??????????????????JSON??????????????????
        IndexRequest indexRequest2 = new IndexRequest("posts", "doc", "1")
                .source(jsonMap);

        // ??????3???????????????XContentBuilder????????????????????????Elasticsearch????????????????????????JSON??????

        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("user", "kimchy");
            builder.field("postDate", new Date());
            builder.field("message", "trying out Elasticsearch");
        }
        builder.endObject();
        IndexRequest indexRequest3 = new IndexRequest("posts", "doc", "1")
                .source(builder);

        //??????4??????Object key-pairs???????????????????????????????????????JSON??????
        IndexRequest indexRequest4 = new IndexRequest("posts", "doc", "1")
                .source("user", "kimchy",
                        "postDate", new Date(),
                        "message", "trying out Elasticsearch");

        //===============================????????????start====================================
        indexRequest1.routing("routing");//???????????????
        //indexRequest1.parent("parent");//??????parent???

        //???????????????????????????????????????????????????
        indexRequest1.timeout(TimeValue.timeValueSeconds(1));//TimeValue??????
        indexRequest1.timeout("1s");//???????????????

        //????????????
        indexRequest1.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);//WriteRequest.RefreshPolicy????????????
        indexRequest1.setRefreshPolicy("wait_for");//???????????????

        indexRequest1.version(2);//????????????

        indexRequest1.versionType(VersionType.EXTERNAL);//??????????????????

        //????????????
        indexRequest1.opType(DocWriteRequest.OpType.CREATE);//DocWriteRequest.OpType??????
        indexRequest1.opType("create");//???????????????, ????????? create ??? update (??????)

        //The name of the ingest pipeline to be executed before indexing the document
        indexRequest1.setPipeline("pipeline");

        //===============================??????====================================
        //????????????
        IndexResponse indexResponse = client.index(indexRequest1,RequestOptions.DEFAULT);

        //????????????
        //IndexResponse ?????????????????????????????????
        //??????????????????????????????????????????
        ActionListener<IndexResponse> listener = new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {
                //???????????????????????? Response?????????????????????
            }

            @Override
            public void onFailure(Exception e) {
                //?????????????????????????????? ????????????????????????????????????
            }
        };
        //?????????????????????????????????IndexRequest?????????ActionListener??????????????????????????????
        client.indexAsync(indexRequest2,RequestOptions.DEFAULT, listener);

        //Index Response
        //?????????IndexResponse?????????????????????????????????????????????????????????
        String index = indexResponse.getIndex();
        String type = indexResponse.getType();
        String id = indexResponse.getId();
        long version = indexResponse.getVersion();
        if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
            //??????????????????????????????????????????????????????
        } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            //????????????????????????????????????????????????
        }
        ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
            //??????????????????????????????????????????????????????
        }
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
                String reason = failure.reason();//?????????????????????
            }
        }

        //???????????????????????????????????????ElasticsearchException???
        IndexRequest request = new IndexRequest("posts", "doc", "1")
                .source("field", "value")
                .version(1);
        try {
            IndexResponse response = client.index(request,RequestOptions.DEFAULT);
        } catch(ElasticsearchException e) {
            if (e.status() == RestStatus.CONFLICT) {
                //????????????????????????????????????????????????
            }
        }

        //??????opType???????????????????????????????????????????????????ID??????????????????????????????????????????????????????
        request = new IndexRequest("posts", "doc", "1")
                .source("field", "value")
                .opType(DocWriteRequest.OpType.CREATE);
        try {
            IndexResponse response = client.index(request,RequestOptions.DEFAULT);
        } catch(ElasticsearchException e) {
            if (e.status() == RestStatus.CONFLICT) {
                //????????????????????????????????????????????????
            }
        }
    }


    public void getIndex() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http")));

        GetRequest getRequest = new GetRequest(
                "posts",//??????
                "doc",//??????
                "1");//??????ID

        //===============================????????????start====================================
        //??????_source????????????????????????
        getRequest.fetchSourceContext(new FetchSourceContext(false));


        //?????????????????????_source_include
        String[] includes = new String[]{"message", "*Date"};
        String[] excludes = Strings.EMPTY_ARRAY;
        FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
        getRequest.fetchSourceContext(fetchSourceContext);

        //?????????????????????_source_exclude
        String[] includes1 = Strings.EMPTY_ARRAY;
        String[] excludes1 = new String[]{"message"};
        FetchSourceContext fetchSourceContext1 = new FetchSourceContext(true, includes, excludes);
        getRequest.fetchSourceContext(fetchSourceContext);

        //????????????stored_fields???????????????????????????????????????????????????
        getRequest.storedFields("message");
        GetResponse getResponse = client.get(getRequest,RequestOptions.DEFAULT);
        //??????message ?????????????????????????????????????????????????????????
        String message = getResponse.getField("message").getValue();

        getRequest.routing("routing");//??????routing???
        //getRequest.parent("parent");//??????parent???
        getRequest.preference("preference");//??????preference???
        getRequest.realtime(false);//??????realtime???false????????????true
        getRequest.refresh(true);//?????????????????????????????????????????????false???
        getRequest.version(2);//????????????
        getRequest.versionType(VersionType.EXTERNAL);//??????????????????
        //===============================????????????end====================================

        //????????????
        GetResponse getResponse1 = client.get(getRequest,RequestOptions.DEFAULT);


        //????????????
        //GetResponse ?????????????????????????????????
        //??????????????????????????????????????????
        ActionListener<GetResponse> listener = new ActionListener<GetResponse>() {
            @Override
            public void onResponse(GetResponse getResponse) {
                //???????????????????????? Response?????????????????????
            }

            @Override
            public void onFailure(Exception e) {
                //?????????????????????????????? ????????????????????????????????????
            }
        };
        //???????????????????????????????????????GetRequest ?????????ActionListener??????????????????????????????
        client.getAsync(getRequest,RequestOptions.DEFAULT, listener);


        //Get Response
        //?????????GetResponse?????????????????????????????????????????????????????????????????????
        String index = getResponse.getIndex();
        String type = getResponse.getType();
        String id = getResponse.getId();
        if (getResponse.isExists()) {
            long version = getResponse.getVersion();
            String sourceAsString = getResponse.getSourceAsString();//????????????(String??????)
            Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();//????????????(Map<String, Object>??????)
            byte[] sourceAsBytes = getResponse.getSourceAsBytes();//???????????????byte[]?????????
        } else {
           /* ????????????????????????????????? ????????????????????????404????????????
            ????????????????????????GetResponse??????????????????????????????
            ??????Response????????????????????????????????????isExists????????????false???*/
        }


        //?????????????????????????????????????????????????????????404?????????????????????ElasticsearchException?????????????????????????????????
        GetRequest request = new GetRequest("does_not_exist", "doc", "1");
        try {
            GetResponse getResponse2 = client.get(request,RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            if (e.status() == RestStatus.NOT_FOUND) {
                //???????????????????????????????????????????????????
            }
        }

        //???????????????????????????????????????????????????????????????????????????????????????????????????????????????
        try {
            GetRequest request1 = new GetRequest("posts", "doc", "1").version(2);
            GetResponse getResponse3 = client.get(request,RequestOptions.DEFAULT);
        } catch (ElasticsearchException exception) {
            if (exception.status() == RestStatus.CONFLICT) {
                //????????????????????????????????????????????????
            }
        }
    }

    public void deleteIndex2() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http")));

        DeleteRequest request = new DeleteRequest (
                "posts",//??????
                "doc",//??????
                "1");//??????ID

        //===============================????????????====================================
        request.routing("routing");//??????routing???
        //request.parent("parent");//??????parent???

        //???????????????????????????????????????????????????
        request.timeout(TimeValue.timeValueMinutes(2));//TimeValue??????
        request.timeout("1s");//???????????????

        //????????????
        request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);//WriteRequest.RefreshPolicy????????????
        request.setRefreshPolicy("wait_for");//???????????????

        request.version(2);//????????????
        request.versionType(VersionType.EXTERNAL);//??????????????????

        //????????????
        DeleteResponse deleteResponse = client.delete(request,RequestOptions.DEFAULT);


        //????????????
        //DeleteResponse  ?????????????????????????????????
        //??????????????????????????????????????????
        ActionListener<DeleteResponse > listener = new ActionListener<DeleteResponse >() {
            @Override
            public void onResponse(DeleteResponse  getResponse) {
                //???????????????????????? Response?????????????????????
            }

            @Override
            public void onFailure(Exception e) {
                //?????????????????????????????? ????????????????????????????????????
            }
        };
        //???????????????????????????????????????DeleteRequest  ?????????ActionListener??????????????????????????????
        client.deleteAsync(request,RequestOptions.DEFAULT, listener);

        //Delete Response
        //?????????DeleteResponse?????????????????????????????????????????????????????????
        String index = deleteResponse.getIndex();
        String type = deleteResponse.getType();
        String id = deleteResponse.getId();
        long version = deleteResponse.getVersion();
        ReplicationResponse.ShardInfo shardInfo = deleteResponse.getShardInfo();
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
            //??????????????????????????????????????????????????????
        }
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
                String reason = failure.reason();//?????????????????????
            }
        }

        //???????????????????????????????????????
        DeleteRequest request1 = new DeleteRequest("posts", "doc", "does_not_exist");
        DeleteResponse deleteResponse1 = client.delete(request,RequestOptions.DEFAULT);
        if (deleteResponse.getResult() == DocWriteResponse.Result.NOT_FOUND) {
            //??????????????????????????????????????????????????????
        }

        //???????????????????????????????????????ElasticsearchException???
        try {
            DeleteRequest request2 = new DeleteRequest("posts", "doc", "1").version(2);
            DeleteResponse deleteResponse2 = client.delete(request,RequestOptions.DEFAULT);
        } catch (ElasticsearchException exception) {
            if (exception.status() == RestStatus.CONFLICT) {
                //????????????????????????????????????????????????
            }
        }
    }

    public void updateIndex() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http")));

        UpdateRequest request = new UpdateRequest  (
                "test",//??????
                "_doc",//??????
                "1");//??????ID

        //??????API?????????????????????????????????????????????????????????????????????

        //????????????
        //??????1?????????????????????????????????????????????
        /*Map<String, Object> parameters = singletonMap("count", 4);//????????????
        //??????painless????????????????????????????????????????????????
        Script inline = new Script(ScriptType.INLINE, "painless", "ctx._source.field += params.count", parameters);
        request.script(inline);

        //??????2??????????????????increment-field?????????,??????????????????????????????????????????
        Script stored =
                new Script(ScriptType.STORED, null, "increment-field", parameters);
        request.script(stored);*/

        //???????????????
        //????????????????????????????????????????????????????????????????????????

        //??????1????????????????????????
        UpdateRequest request1 = new UpdateRequest("posts", "doc", "1");
        String jsonString = "{" +
                "\"updated\":\"2017-01-01\"," +
                "\"reason\":\"daily update\"" +
                "}";
        request1.doc(jsonString, XContentType.JSON);

        //??????2?????????Map???????????????????????????json??????
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("updated", new Date());
        jsonMap.put("reason", "daily update");
        UpdateRequest request2 = new UpdateRequest("posts", "doc", "1")
                .doc(jsonMap);


        //??????3?????????XContentBuilder??????
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("updated", new Date());
            builder.field("reason", "daily update");
        }
        builder.endObject();
        UpdateRequest request3 = new UpdateRequest("posts", "doc", "1")
                .doc(builder);


        //??????4?????????Object key-pairs??????
        UpdateRequest request4 = new UpdateRequest("posts", "doc", "1")
                .doc("updated", new Date(),
                        "reason", "daily update");


        //??????????????????????????????????????????upsert??????????????????????????????????????????????????????
        //????????????????????????????????????????????????String???Map???XContentBuilder???Object key-pairs??????????????????upsert??????????????????
        String jsonString1 = "{\"created\":\"2017-01-01\"}";
        request.upsert(jsonString1, XContentType.JSON);

        //=========================????????????===========================
        request.routing("routing");//??????routing???
        //request.parent("parent");//??????parent???

        //???????????????????????????????????????????????????
        request.timeout(TimeValue.timeValueSeconds(1));//TimeValue??????
        request.timeout("1s");//???????????????

        //????????????
        request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);//WriteRequest.RefreshPolicy????????????
        request.setRefreshPolicy("wait_for");//???????????????

        //????????????????????????????????????????????????????????????????????????????????????????????????????????????
        request.retryOnConflict(3);

        request.version(2);//????????????

        request.fetchSource(true); //??????_source????????????????????????

        //?????????????????????_source_include
        String[] includes = new String[]{"updated", "r*"};
        String[] excludes = Strings.EMPTY_ARRAY;
        request.fetchSource(new FetchSourceContext(true, includes, excludes));

        //?????????????????????_source_exclude
        String[] includes1 = Strings.EMPTY_ARRAY;
        String[] excludes1 = new String[]{"updated"};
        request.fetchSource(new FetchSourceContext(true, includes1, excludes1));

        request.detectNoop(false);//??????noop??????

        //???????????????????????????????????????????????????????????????????????????????????????????????????????????????
        request.scriptedUpsert(true);

        //???????????????????????????????????????????????????upsert?????????
        request.docAsUpsert(true);

        //????????????????????????????????????????????????????????????????????????
        request.waitForActiveShards(2);
        //??????ActiveShardCount??????????????????ActiveShardCount.ALL???ActiveShardCount.ONE???ActiveShardCount.DEFAULT???????????????
        request.waitForActiveShards(ActiveShardCount.ALL);

        //????????????
        UpdateResponse updateResponse = client.update(request,RequestOptions.DEFAULT);


        //????????????
        //DeleteResponse  ?????????????????????????????????
        //??????????????????????????????????????????
        ActionListener<UpdateResponse > listener = new ActionListener<UpdateResponse >() {
            @Override
            public void onResponse(UpdateResponse  updateResponse) {
                //???????????????????????? Response?????????????????????
            }

            @Override
            public void onFailure(Exception e) {
                //?????????????????????????????? ????????????????????????????????????
            }
        };
        //???????????????????????????????????????UpdateRequest  ?????????ActionListener??????????????????????????????
        client.updateAsync(request,RequestOptions.DEFAULT, listener);

        //Update Response
        //?????????UpdateResponse?????????????????????????????????????????????????????????
        String index = updateResponse.getIndex();
        String type = updateResponse.getType();
        String id = updateResponse.getId();
        long version = updateResponse.getVersion();
        if (updateResponse.getResult() == DocWriteResponse.Result.CREATED) {
            //???????????????????????????????????????upsert???
        } else if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            //??????????????????????????????
        } else if (updateResponse.getResult() == DocWriteResponse.Result.DELETED) {
            //?????????????????????????????????
        } else if (updateResponse.getResult() == DocWriteResponse.Result.NOOP) {
            //??????????????????????????????????????????????????????????????????????????????noop???
        }

        //?????????fetchSource?????????UpdateRequest?????????????????????????????????????????????????????????
        GetResult result = updateResponse.getGetResult();//????????????????????????
        if (result.isExists()) {
            String sourceAsString = result.sourceAsString();//??????????????????????????????String?????????
            Map<String, Object> sourceAsMap = result.sourceAsMap();//??????????????????????????????Map?????????
            byte[] sourceAsBytes = result.source();//??????????????????????????????byte[]?????????
        } else {
            //???????????????????????????????????????????????????????????????
        }


        //??????????????????????????????
        ReplicationResponse.ShardInfo shardInfo = updateResponse.getShardInfo();
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
            //??????????????????????????????????????????????????????
        }
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
                String reason = failure.reason();//?????????????????????
            }
        }

        //????????????????????????????????????404?????????????????????ElasticsearchException?????????????????????????????????
        UpdateRequest request5 = new UpdateRequest("posts", "type", "does_not_exist").doc("field", "value");
        try {
            UpdateResponse updateResponse5 = client.update(request,RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            if (e.status() == RestStatus.NOT_FOUND) {
                //??????????????????????????????????????????
            }
        }

        //???????????????????????????????????????ElasticsearchException???
        UpdateRequest request6 = new UpdateRequest("posts", "doc", "1")
                .doc("field", "value")
                .version(1);
        try {
            UpdateResponse updateResponse6 = client.update(request,RequestOptions.DEFAULT);
        } catch(ElasticsearchException e) {
            if (e.status() == RestStatus.CONFLICT) {
                //????????????????????????????????????????????????
            }
        }
    }


}
