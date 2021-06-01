package com.example.demo.com.test.test.es.project;
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

        CreateIndexRequest request = new CreateIndexRequest("twitter_two");//创建索引
        //创建的每个索引都可以有与之关联的特定设置。
            request.settings(Settings.builder()
                    .put("index.number_of_shards", 3)
                    .put("index.number_of_replicas", 2)
            );
        //创建索引时创建文档类型映射
            request.mapping("tweet",//类型定义
                    "  {\n" +
                    "    \"tweet\": {\n" +
                    "      \"properties\": {\n" +
                    "        \"message\": {\n" +
                    "          \"type\": \"text\"\n" +
                    "        }\n" +
                    "      }\n" +
                    "    }\n" +
                    "  }",//类型映射，需要的是一个JSON字符串
        XContentType.JSON);

        //为索引设置一个别名
            request.alias(
                    new Alias("twitter_alias")
            );
        //可选参数
            request.timeout(TimeValue.timeValueMinutes(2));//超时,等待所有节点被确认(使用TimeValue方式)
        //request.timeout("2m");//超时,等待所有节点被确认(使用字符串方式)

            request.masterNodeTimeout(TimeValue.timeValueMinutes(1));//连接master节点的超时时间(使用TimeValue方式)
        //request.masterNodeTimeout("1m");//连接master节点的超时时间(使用字符串方式)

            request.waitForActiveShards(2);//在创建索引API返回响应之前等待的活动分片副本的数量，以int形式表示。
        //request.waitForActiveShards(ActiveShardCount.DEFAULT);//在创建索引API返回响应之前等待的活动分片副本的数量，以ActiveShardCount形式表示。

        //同步执行
        //CreateIndexResponse createIndexResponse = client.indices().create(request);
        //异步执行
        //异步执行创建索引请求需要将CreateIndexRequest实例和ActionListener实例传递给异步方法：
        //CreateIndexResponse的典型监听器如下所示：
        //异步方法不会阻塞并立即返回。
        ActionListener<CreateIndexResponse> listener = new ActionListener<CreateIndexResponse>() {
            @Override
            public void onResponse(CreateIndexResponse createIndexResponse) {
                //如果执行成功，则调用onResponse方法;
            }
            @Override
            public void onFailure(Exception e) {
                //如果失败，则调用onFailure方法。
            }
        };
            //client.indices().createAsync(request);
            client.indices().createAsync(request,RequestOptions.DEFAULT,null);
            //要执行的CreateIndexRequest和执行完成时要使用的ActionListener

        //返回的CreateIndexResponse允许检索有关执行的操作的信息，如下所示：
//        boolean acknowledged = createIndexResponse.isAcknowledged();//指示是否所有节点都已确认请求
//        boolean shardsAcknowledged = createIndexResponse.isShardsAcknowledged();//指示是否在超时之前为索引中的每个分片启动了必需的分片副本数

    }

    public void deleteIndex() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http")));

        DeleteIndexRequest request = new DeleteIndexRequest("twitter_two");//指定要删除的索引名称
        //可选参数：
        request.timeout(TimeValue.timeValueMinutes(2)); //设置超时，等待所有节点确认索引删除（使用TimeValue形式）
        // request.timeout("2m"); //设置超时，等待所有节点确认索引删除（使用字符串形式）

        request.masterNodeTimeout(TimeValue.timeValueMinutes(1));////连接master节点的超时时间(使用TimeValue方式)
        // request.masterNodeTimeout("1m");//连接master节点的超时时间(使用字符串方式)

        //设置IndicesOptions控制如何解决不可用的索引以及如何扩展通配符表达式
        request.indicesOptions(IndicesOptions.lenientExpandOpen());

        //同步执行
        AcknowledgedResponse deleteIndexResponse = client.indices().delete(request, RequestOptions.DEFAULT);

  /*    //异步执行删除索引请求需要将DeleteIndexRequest实例和ActionListener实例传递给异步方法：
        //DeleteIndexResponse的典型监听器如下所示：
        //异步方法不会阻塞并立即返回。
        ActionListener<DeleteIndexResponse> listener = new ActionListener<DeleteIndexResponse>() {
            @Override
            public void onResponse(DeleteIndexResponse deleteIndexResponse) {
                //如果执行成功，则调用onResponse方法;
            }

            @Override
            public void onFailure(Exception e) {
                //如果失败，则调用onFailure方法。
            }
        };
        client.indices().deleteAsync(request, listener);*/

        //Delete Index Response
        //返回的DeleteIndexResponse允许检索有关执行的操作的信息，如下所示：
        boolean acknowledged = deleteIndexResponse.isAcknowledged();//是否所有节点都已确认请求


        //如果找不到索引，则会抛出ElasticsearchException：
        try {
            request = new DeleteIndexRequest("does_not_exist");
            client.indices().delete(request,RequestOptions.DEFAULT);
        } catch (ElasticsearchException exception) {
//            if (exception.status() == RestStatus.NOT_FOUND) {
//                //如果没有找到要删除的索引，要执行某些操作
//            }
        }
    }

    public void openIndex() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http")));

        OpenIndexRequest request = new OpenIndexRequest("twitter");//打开索引

        //可选参数：
        request.timeout(TimeValue.timeValueMinutes(2)); //设置超时，等待所有节点确认索引已打开（使用TimeValue形式）
        // request.timeout("2m"); //设置超时，等待所有节点确认索引已打开（使用字符串形式）

        request.masterNodeTimeout(TimeValue.timeValueMinutes(1));////连接master节点的超时时间(使用TimeValue方式)
        // request.masterNodeTimeout("1m");//连接master节点的超时时间(使用字符串方式)

        request.waitForActiveShards(2);//在打开索引API返回响应之前等待的活动分片副本的数量，以int形式表示。
        //request.waitForActiveShards(ActiveShardCount.ONE);//在打开索引API返回响应之前等待的活动分片副本的数量，以ActiveShardCount形式表示。

        //设置IndicesOptions控制如何解决不可用的索引以及如何扩展通配符表达式
        request.indicesOptions(IndicesOptions.strictExpandOpen());

        //同步执行
        OpenIndexResponse openIndexResponse = client.indices().open(request,RequestOptions.DEFAULT);

        /*//异步执行打开索引请求需要将OpenIndexRequest实例和ActionListener实例传递给异步方法：
        //OpenIndexResponse的典型监听器如下所示：
        //异步方法不会阻塞并立即返回。
        ActionListener<OpenIndexResponse> listener = new ActionListener<OpenIndexResponse>() {
            @Override
            public void onResponse(OpenIndexResponse openIndexResponse) {
                //如果执行成功，则调用onResponse方法;
            }

            @Override
            public void onFailure(Exception e) {
                //如果失败，则调用onFailure方法。
            }
        };
        client.indices().openAsync(request, listener);*/

        //Open Index Response
        //返回的OpenIndexResponse允许检索有关执行的操作的信息，如下所示：
        boolean acknowledged = openIndexResponse.isAcknowledged();//指示是否所有节点都已确认请求
        boolean shardsAcknowledged = openIndexResponse.isShardsAcknowledged();//指示是否在超时之前为索引中的每个分片启动了必需的分片副本数
    }

    public void closeIndex() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http")));
        CloseIndexRequest request = new CloseIndexRequest("index");//关闭索引

        //可选参数：
        //request.timeout(TimeValue.timeValueMinutes(2)); //设置超时，等待所有节点确认索引已关闭（使用TimeValue形式）
        // request.timeout("2m"); //设置超时，等待所有节点确认索引已关闭（使用字符串形式）

        //request.masterNodeTimeout(TimeValue.timeValueMinutes(1));////连接master节点的超时时间(使用TimeValue方式)
        // request.masterNodeTimeout("1m");//连接master节点的超时时间(使用字符串方式)

        //设置IndicesOptions控制如何解决不可用的索引以及如何扩展通配符表达式
        request.indicesOptions(IndicesOptions.lenientExpandOpen());
        //同步执行
        CloseIndexResponse closeIndexResponse = client.indices().close(request,RequestOptions.DEFAULT);

         /*//异步执行打开索引请求需要将CloseIndexRequest实例和ActionListener实例传递给异步方法：
        //CloseIndexResponse的典型监听器如下所示：
        //异步方法不会阻塞并立即返回。
        ActionListener<CloseIndexResponse> listener = new ActionListener<CloseIndexResponse>() {
            @Override
            public void onResponse(CloseIndexResponse closeIndexResponse) {
                 //如果执行成功，则调用onResponse方法;
            }

            @Override
            public void onFailure(Exception e) {
                 //如果失败，则调用onFailure方法。
            }
        };
        client.indices().closeAsync(request, listener); */

        //Close Index Response
        //返回的CloseIndexResponse 允许检索有关执行的操作的信息，如下所示：
        boolean acknowledged = closeIndexResponse.isAcknowledged(); //指示是否所有节点都已确认请求
    }

    public void index() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http")));
        IndexRequest indexRequest1 = new IndexRequest(
                "posts",//索引名称
                "doc",//类型名称
                "1");//文档ID

        //==============================提供文档源========================================
        //方式1：以字符串形式提供
        String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        indexRequest1.source(jsonString, XContentType.JSON);

        //方式2：以Map形式提供
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user", "kimchy");
        jsonMap.put("postDate", new Date());
        jsonMap.put("message", "trying out Elasticsearch");
        //Map会自动转换为JSON格式的文档源
        IndexRequest indexRequest2 = new IndexRequest("posts", "doc", "1")
                .source(jsonMap);

        // 方式3：文档源以XContentBuilder对象的形式提供，Elasticsearch内部会帮我们生成JSON内容

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

        //方式4：以Object key-pairs提供的文档源，它会被转换为JSON格式
        IndexRequest indexRequest4 = new IndexRequest("posts", "doc", "1")
                .source("user", "kimchy",
                        "postDate", new Date(),
                        "message", "trying out Elasticsearch");

        //===============================可选参数start====================================
        indexRequest1.routing("routing");//设置路由值
        //indexRequest1.parent("parent");//设置parent值

        //设置超时：等待主分片变得可用的时间
        indexRequest1.timeout(TimeValue.timeValueSeconds(1));//TimeValue方式
        indexRequest1.timeout("1s");//字符串方式

        //刷新策略
        indexRequest1.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);//WriteRequest.RefreshPolicy实例方式
        indexRequest1.setRefreshPolicy("wait_for");//字符串方式

        indexRequest1.version(2);//设置版本

        indexRequest1.versionType(VersionType.EXTERNAL);//设置版本类型

        //操作类型
        indexRequest1.opType(DocWriteRequest.OpType.CREATE);//DocWriteRequest.OpType方式
        indexRequest1.opType("create");//字符串方式, 可以是 create 或 update (默认)

        //The name of the ingest pipeline to be executed before indexing the document
        indexRequest1.setPipeline("pipeline");

        //===============================执行====================================
        //同步执行
        IndexResponse indexResponse = client.index(indexRequest1,RequestOptions.DEFAULT);

        //异步执行
        //IndexResponse 的典型监听器如下所示：
        //异步方法不会阻塞并立即返回。
        ActionListener<IndexResponse> listener = new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {
                //执行成功时调用。 Response以参数方式提供
            }

            @Override
            public void onFailure(Exception e) {
                //在失败的情况下调用。 引发的异常以参数方式提供
            }
        };
        //异步执行索引请求需要将IndexRequest实例和ActionListener实例传递给异步方法：
        client.indexAsync(indexRequest2,RequestOptions.DEFAULT, listener);

        //Index Response
        //返回的IndexResponse允许检索有关执行操作的信息，如下所示：
        String index = indexResponse.getIndex();
        String type = indexResponse.getType();
        String id = indexResponse.getId();
        long version = indexResponse.getVersion();
        if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
            //处理（如果需要）第一次创建文档的情况
        } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            //处理（如果需要）文档被重写的情况
        }
        ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
            //处理成功分片数量少于总分片数量的情况
        }
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
                String reason = failure.reason();//处理潜在的失败
            }
        }

        //如果存在版本冲突，则会抛出ElasticsearchException：
        IndexRequest request = new IndexRequest("posts", "doc", "1")
                .source("field", "value")
                .version(1);
        try {
            IndexResponse response = client.index(request,RequestOptions.DEFAULT);
        } catch(ElasticsearchException e) {
            if (e.status() == RestStatus.CONFLICT) {
                //引发的异常表示返回了版本冲突错误
            }
        }

        //如果opType设置为创建但是具有相同索引，类型和ID的文档已存在，则也会发生同样的情况：
        request = new IndexRequest("posts", "doc", "1")
                .source("field", "value")
                .opType(DocWriteRequest.OpType.CREATE);
        try {
            IndexResponse response = client.index(request,RequestOptions.DEFAULT);
        } catch(ElasticsearchException e) {
            if (e.status() == RestStatus.CONFLICT) {
                //引发的异常表示返回了版本冲突错误
            }
        }
    }


    public void getIndex() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http")));

        GetRequest getRequest = new GetRequest(
                "posts",//索引
                "doc",//类型
                "1");//文档ID

        //===============================可选参数start====================================
        //禁用_source检索，默认为启用
        getRequest.fetchSourceContext(new FetchSourceContext(false));


        //为特定字段配置_source_include
        String[] includes = new String[]{"message", "*Date"};
        String[] excludes = Strings.EMPTY_ARRAY;
        FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
        getRequest.fetchSourceContext(fetchSourceContext);

        //为指定字段配置_source_exclude
        String[] includes1 = Strings.EMPTY_ARRAY;
        String[] excludes1 = new String[]{"message"};
        FetchSourceContext fetchSourceContext1 = new FetchSourceContext(true, includes, excludes);
        getRequest.fetchSourceContext(fetchSourceContext);

        //配置指定stored_fields的检索（要求字段在映射中单独存储）
        getRequest.storedFields("message");
        GetResponse getResponse = client.get(getRequest,RequestOptions.DEFAULT);
        //检索message 存储字段（要求将字段分开存储在映射中）
        String message = getResponse.getField("message").getValue();

        getRequest.routing("routing");//设置routing值
        //getRequest.parent("parent");//设置parent值
        getRequest.preference("preference");//设置preference值
        getRequest.realtime(false);//设置realtime为false，默认是true
        getRequest.refresh(true);//在检索文档之前执行刷新（默认为false）
        getRequest.version(2);//设置版本
        getRequest.versionType(VersionType.EXTERNAL);//设置版本类型
        //===============================可选参数end====================================

        //同步执行
        GetResponse getResponse1 = client.get(getRequest,RequestOptions.DEFAULT);


        //异步执行
        //GetResponse 的典型监听器如下所示：
        //异步方法不会阻塞并立即返回。
        ActionListener<GetResponse> listener = new ActionListener<GetResponse>() {
            @Override
            public void onResponse(GetResponse getResponse) {
                //执行成功时调用。 Response以参数方式提供
            }

            @Override
            public void onFailure(Exception e) {
                //在失败的情况下调用。 引发的异常以参数方式提供
            }
        };
        //异步执行获取索引请求需要将GetRequest 实例和ActionListener实例传递给异步方法：
        client.getAsync(getRequest,RequestOptions.DEFAULT, listener);


        //Get Response
        //返回的GetResponse允许检索请求的文档及其元数据和最终存储的字段。
        String index = getResponse.getIndex();
        String type = getResponse.getType();
        String id = getResponse.getId();
        if (getResponse.isExists()) {
            long version = getResponse.getVersion();
            String sourceAsString = getResponse.getSourceAsString();//检索文档(String形式)
            Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();//检索文档(Map<String, Object>形式)
            byte[] sourceAsBytes = getResponse.getSourceAsBytes();//检索文档（byte[]形式）
        } else {
           /* 处理找不到文档的情况。 请注意，尽管返回404状态码，
            但返回的是有效的GetResponse，而不是抛出的异常。
            此类Response不包含任何源文档，并且其isExists方法返回false。*/
        }


        //当针对不存在的索引执行获取请求时，响应404状态码，将引发ElasticsearchException，需要按如下方式处理：
        GetRequest request = new GetRequest("does_not_exist", "doc", "1");
        try {
            GetResponse getResponse2 = client.get(request,RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            if (e.status() == RestStatus.NOT_FOUND) {
                //处理因为索引不存在而抛出的异常情况
            }
        }

        //如果请求了特定的文档版本，并且现有文档具有不同的版本号，则会引发版本冲突：
        try {
            GetRequest request1 = new GetRequest("posts", "doc", "1").version(2);
            GetResponse getResponse3 = client.get(request,RequestOptions.DEFAULT);
        } catch (ElasticsearchException exception) {
            if (exception.status() == RestStatus.CONFLICT) {
                //引发的异常表示返回了版本冲突错误
            }
        }
    }

    public void deleteIndex2() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http")));

        DeleteRequest request = new DeleteRequest (
                "posts",//索引
                "doc",//类型
                "1");//文档ID

        //===============================可选参数====================================
        request.routing("routing");//设置routing值
        //request.parent("parent");//设置parent值

        //设置超时：等待主分片变得可用的时间
        request.timeout(TimeValue.timeValueMinutes(2));//TimeValue方式
        request.timeout("1s");//字符串方式

        //刷新策略
        request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);//WriteRequest.RefreshPolicy实例方式
        request.setRefreshPolicy("wait_for");//字符串方式

        request.version(2);//设置版本
        request.versionType(VersionType.EXTERNAL);//设置版本类型

        //同步执行
        DeleteResponse deleteResponse = client.delete(request,RequestOptions.DEFAULT);


        //异步执行
        //DeleteResponse  的典型监听器如下所示：
        //异步方法不会阻塞并立即返回。
        ActionListener<DeleteResponse > listener = new ActionListener<DeleteResponse >() {
            @Override
            public void onResponse(DeleteResponse  getResponse) {
                //执行成功时调用。 Response以参数方式提供
            }

            @Override
            public void onFailure(Exception e) {
                //在失败的情况下调用。 引发的异常以参数方式提供
            }
        };
        //异步执行获取索引请求需要将DeleteRequest  实例和ActionListener实例传递给异步方法：
        client.deleteAsync(request,RequestOptions.DEFAULT, listener);

        //Delete Response
        //返回的DeleteResponse允许检索有关执行操作的信息，如下所示：
        String index = deleteResponse.getIndex();
        String type = deleteResponse.getType();
        String id = deleteResponse.getId();
        long version = deleteResponse.getVersion();
        ReplicationResponse.ShardInfo shardInfo = deleteResponse.getShardInfo();
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
            //处理成功分片数量少于总分片数量的情况
        }
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
                String reason = failure.reason();//处理潜在的失败
            }
        }

        //还可以检查文档是否被找到：
        DeleteRequest request1 = new DeleteRequest("posts", "doc", "does_not_exist");
        DeleteResponse deleteResponse1 = client.delete(request,RequestOptions.DEFAULT);
        if (deleteResponse.getResult() == DocWriteResponse.Result.NOT_FOUND) {
            //如果找不到要删除的文档，执行某些操作
        }

        //如果存在版本冲突，则会抛出ElasticsearchException：
        try {
            DeleteRequest request2 = new DeleteRequest("posts", "doc", "1").version(2);
            DeleteResponse deleteResponse2 = client.delete(request,RequestOptions.DEFAULT);
        } catch (ElasticsearchException exception) {
            if (exception.status() == RestStatus.CONFLICT) {
                //引发的异常表示返回了版本冲突错误
            }
        }
    }

    public void updateIndex() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http")));

        UpdateRequest request = new UpdateRequest  (
                "test",//索引
                "_doc",//类型
                "1");//文档ID

        //更新API允许通过使用脚本或传递部分文档来更新现有文档。

        //使用脚本
        //方式1：该脚本可以作为内联脚本提供：
        /*Map<String, Object> parameters = singletonMap("count", 4);//脚本参数
        //使用painless语言和上面的参数创建一个内联脚本
        Script inline = new Script(ScriptType.INLINE, "painless", "ctx._source.field += params.count", parameters);
        request.script(inline);

        //方式2：引用名称为increment-field的脚本,改脚本定义的位置还没搞清楚。
        Script stored =
                new Script(ScriptType.STORED, null, "increment-field", parameters);
        request.script(stored);*/

        //只更新部分
        //更新部分文档时，更新的部分文档将与现有文档合并。

        //方式1：使用字符串形式
        UpdateRequest request1 = new UpdateRequest("posts", "doc", "1");
        String jsonString = "{" +
                "\"updated\":\"2017-01-01\"," +
                "\"reason\":\"daily update\"" +
                "}";
        request1.doc(jsonString, XContentType.JSON);

        //方式2：使用Map形式，会被自动转为json格式
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("updated", new Date());
        jsonMap.put("reason", "daily update");
        UpdateRequest request2 = new UpdateRequest("posts", "doc", "1")
                .doc(jsonMap);


        //方式3：使用XContentBuilder形式
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("updated", new Date());
            builder.field("reason", "daily update");
        }
        builder.endObject();
        UpdateRequest request3 = new UpdateRequest("posts", "doc", "1")
                .doc(builder);


        //方式4：使用Object key-pairs形式
        UpdateRequest request4 = new UpdateRequest("posts", "doc", "1")
                .doc("updated", new Date(),
                        "reason", "daily update");


        //如果文档尚不存在，则可以使用upsert方法定义一些将作为新文档插入的内容：
        //与部分文档更新类似，可以使用接受String，Map，XContentBuilder或Object key-pairs的方式来定义upsert文档的内容。
        String jsonString1 = "{\"created\":\"2017-01-01\"}";
        request.upsert(jsonString1, XContentType.JSON);

        //=========================可选参数===========================
        request.routing("routing");//设置routing值
        //request.parent("parent");//设置parent值

        //设置超时：等待主分片变得可用的时间
        request.timeout(TimeValue.timeValueSeconds(1));//TimeValue方式
        request.timeout("1s");//字符串方式

        //刷新策略
        request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);//WriteRequest.RefreshPolicy实例方式
        request.setRefreshPolicy("wait_for");//字符串方式

        //如果要更新的文档在获取或者索引阶段已被另一操作更改，则重试更新操作的次数
        request.retryOnConflict(3);

        request.version(2);//设置版本

        request.fetchSource(true); //启用_source检索，默认为禁用

        //为特定字段配置_source_include
        String[] includes = new String[]{"updated", "r*"};
        String[] excludes = Strings.EMPTY_ARRAY;
        request.fetchSource(new FetchSourceContext(true, includes, excludes));

        //为指定字段配置_source_exclude
        String[] includes1 = Strings.EMPTY_ARRAY;
        String[] excludes1 = new String[]{"updated"};
        request.fetchSource(new FetchSourceContext(true, includes1, excludes1));

        request.detectNoop(false);//禁用noop检测

        //无论文档是否存在，脚本都必须运行，即如果脚本尚不存在，则脚本负责创建文档。
        request.scriptedUpsert(true);

        //如果不存在，则表明部分文档必须用作upsert文档。
        request.docAsUpsert(true);

        //设置在继续更新操作之前必须激活的分片副本的数量。
        request.waitForActiveShards(2);
        //使用ActiveShardCount方式，可以是ActiveShardCount.ALL，ActiveShardCount.ONE或ActiveShardCount.DEFAULT（默认值）
        request.waitForActiveShards(ActiveShardCount.ALL);

        //同步执行
        UpdateResponse updateResponse = client.update(request,RequestOptions.DEFAULT);


        //异步执行
        //DeleteResponse  的典型监听器如下所示：
        //异步方法不会阻塞并立即返回。
        ActionListener<UpdateResponse > listener = new ActionListener<UpdateResponse >() {
            @Override
            public void onResponse(UpdateResponse  updateResponse) {
                //执行成功时调用。 Response以参数方式提供
            }

            @Override
            public void onFailure(Exception e) {
                //在失败的情况下调用。 引发的异常以参数方式提供
            }
        };
        //异步执行获取索引请求需要将UpdateRequest  实例和ActionListener实例传递给异步方法：
        client.updateAsync(request,RequestOptions.DEFAULT, listener);

        //Update Response
        //返回的UpdateResponse允许检索有关执行操作的信息，如下所示：
        String index = updateResponse.getIndex();
        String type = updateResponse.getType();
        String id = updateResponse.getId();
        long version = updateResponse.getVersion();
        if (updateResponse.getResult() == DocWriteResponse.Result.CREATED) {
            //处理第一次创建文档的情况（upsert）
        } else if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            //处理文档被更新的情况
        } else if (updateResponse.getResult() == DocWriteResponse.Result.DELETED) {
            //处理文档已被删除的情况
        } else if (updateResponse.getResult() == DocWriteResponse.Result.NOOP) {
            //处理文档未受更新影响的情况，即文档上未执行任何操作（noop）
        }

        //当通过fetchSource方法在UpdateRequest中启用源检索时，响应会包含已更新文档：
        GetResult result = updateResponse.getGetResult();//获取已更新的文档
        if (result.isExists()) {
            String sourceAsString = result.sourceAsString();//获取已更新的文档源（String方式）
            Map<String, Object> sourceAsMap = result.sourceAsMap();//获取已更新的文档源（Map方式）
            byte[] sourceAsBytes = result.source();//获取已更新的文档源（byte[]方式）
        } else {
            //处理不返回文档源的场景（默认就是这种情况）
        }


        //也可以检查分片失败：
        ReplicationResponse.ShardInfo shardInfo = updateResponse.getShardInfo();
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
            //处理成功分片数量少于总分片数量的情况
        }
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
                String reason = failure.reason();//处理潜在的失败
            }
        }

        //当针对文档不存在时，响应404状态码，将引发ElasticsearchException，需要按如下方式处理：
        UpdateRequest request5 = new UpdateRequest("posts", "type", "does_not_exist").doc("field", "value");
        try {
            UpdateResponse updateResponse5 = client.update(request,RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            if (e.status() == RestStatus.NOT_FOUND) {
                //处理由于文档不存在抛出的异常
            }
        }

        //如果存在版本冲突，则会抛出ElasticsearchException：
        UpdateRequest request6 = new UpdateRequest("posts", "doc", "1")
                .doc("field", "value")
                .version(1);
        try {
            UpdateResponse updateResponse6 = client.update(request,RequestOptions.DEFAULT);
        } catch(ElasticsearchException e) {
            if (e.status() == RestStatus.CONFLICT) {
                //引发的异常表示返回了版本冲突错误
            }
        }
    }


}
