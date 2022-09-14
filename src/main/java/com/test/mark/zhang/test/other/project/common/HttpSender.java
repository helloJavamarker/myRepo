package com.test.mark.zhang.test.other.project.common;

//import io.confluent.kafka.schemaregistry.client.CachedSchemaRegistryClient;
//import io.confluent.kafka.schemaregistry.client.rest.exceptions.RestClientException;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.web.client.RestClientException;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import static org.apache.avro.Schema.Type.RECORD;


public class HttpSender {
    private String topic;
    private Schema schema;
    private DefaultHttpClient httpClient;
    private String filePath;
    private String sendHttpUrl;

    public HttpSender(String topic, String schemaUrl, String filePath, String sendHttpUrl) throws IOException, RestClientException {
        this.topic = topic;
        this.sendHttpUrl = sendHttpUrl;
        this.filePath = filePath;
//        init(schemaUrl);
    }

    /**
     * 根据传入的schemaUrl和topic获取schema。
     *  基于Kafka的高性能流处理平台——Confluent
     *  Confluent有两个类型可以下载，企业版(Enterprise)需要付费，可以免费使用30天
//     * @param schemaUrl
     * @throws IOException
     * @throws RestClientException
     */
//    private void init(String schemaUrl) throws IOException, RestClientException {
//        Schema.Parser parser = new Schema.Parser();
//        CachedSchemaRegistryClient cachedSchemaRegistryClient = new CachedSchemaRegistryClient(schemaUrl, 100);
//        schema = parser.parse(cachedSchemaRegistryClient.getLatestSchemaMetadata(topic).getSchema());
//        httpClient = new DefaultHttpClient();
//    }

    public static Object convert(Schema schema, String v) throws Exception {
// System.out.println("schema:" + schema + " v:\"" + v + "\"");
        Object o = null;
// ----------------''|null-----------------
// ''|'null' -> string|bytes:''|'null'
// others:null
        if (v == null) {
            return null;
        }
        if (v.length() == 0 || v.equals("null")) {
            switch (schema.getType()) {
                case STRING:
                    return v;
                case BYTES:
                    return ByteBuffer.wrap(v.getBytes("UTF-8"));
                case UNION:
                    break;
                default:
                    return null;
            }
        }
        switch (schema.getType()) {
            case NULL:
                o = null;
                break;
            case BOOLEAN:
                o = Boolean.parseBoolean(v);
                break;
            case INT:
                o = Integer.parseInt(v);
                break;
            case LONG:
                o = Long.parseLong(v);
                break;
            case FLOAT:
                o = Float.parseFloat(v);
                break;
            case DOUBLE:
                o = Double.parseDouble(v);
                break;
            case BYTES:
                try {
                    o = ByteBuffer.wrap(v.getBytes("UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            case STRING:
                o = v;
                break;
            case UNION:
                for (Schema mem : schema.getTypes()) {
                    o = convert(mem, v);
// break wehn encounter not null value, or we will get null
                    if (o != null) break;
                }
                break;
            case RECORD:
                throw new Exception("Unsopported test.avro type:" + RECORD);
            case MAP:
                throw new Exception("Unsopported test.avro type:" + RECORD);
            case ENUM:
                throw new Exception("Unsopported test.avro type:" + RECORD);
            case ARRAY:
                throw new Exception("Unsopported test.avro type:" + RECORD);
            case FIXED:
                throw new Exception("Unsopported test.avro type:" + RECORD);
            default:
                throw new Exception("Unsopported test.avro type:" + RECORD);
        }
        return o;
    }

    /**
     * 封装http消息头和消息体，将其发送到指定加载机。
     *
     * @param out
     */
    private void sendData(ByteArrayOutputStream out) {
        HttpPost httpPost = new HttpPost(sendHttpUrl);
        httpPost.addHeader("content-type", "utf-8");
        httpPost.addHeader("Topic", topic);
        httpPost.addHeader("Format", "avro");
        HttpEntity httpEntity = new ByteArrayEntity(out.toByteArray());
        httpPost.setEntity(httpEntity);
        try {
            HttpResponse response = httpClient.execute(httpPost);
        } catch (IOException e) {
            System.out.println("send http error" + e);
        } finally {
            if (httpPost != null) {
                httpPost.abort();
            }
        }
    }

    /**
     * 读取文件内容，并将其序列化。
     *
     * @throws IOException
     */
    public void sendDataToHttp() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024 * 1024);
        GenericDatumWriter<GenericRecord> writer = new GenericDatumWriter<GenericRecord>(schema);
        Encoder encoder = EncoderFactory.get().binaryEncoder(out, null);
        BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
        String line;
        while ((line = reader.readLine()) != null) {
            out.reset();
            GenericRecord record = new GenericData.Record(schema);
            String[] readline = line.split(",");
            for (int i = 0; i < schema.getFields().size(); i++) {
                try {
                    System.out.println(readline[i]);
//                    Object o = convert(schema.getFields().get.schema(), readline[i]);
//                    record.put(i, o);
                } catch (Exception e) {
                    break;
                }
            }
            writer.write(record, encoder);
            encoder.flush();
            out.flush();
            sendData(out);
        }
        System.out.println("send data finished");
    }

    /**
     * main函数，args[0]代表topic名，args[1]代表schemaUrl，args[2]代表发送的文件，args[3]代表加载机信息。
     * <p>
     * 加载机信息可分为两种：一种是IP:10080；一种是demo.httpload.dc.pub：10080。
     *
     * @param args
     * @throws IOException
     * @throws RestClientException
     */
    public static void main(String[] args) throws IOException, RestClientException {
        if (args.length < 4) {
            System.out.println("usage is :topic schemaurl sendfilePath sendurl");
            System.exit(-1);
        }
        HttpSender sender = new HttpSender(args[0], args[1], args[2], args[3]);
        sender.sendDataToHttp();
    }
}