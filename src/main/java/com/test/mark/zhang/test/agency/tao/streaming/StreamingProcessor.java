package com.test.mark.zhang.test.agency.tao.streaming;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import redis.clients.jedis.Jedis;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 流式处理器
 */
public class StreamingProcessor {
    public static void main(String[] args) {

        AtomicBoolean isRunnning = new AtomicBoolean(true);

        //  构造一个kafkaconsumer
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "doitedu01:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "g0002");
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "c001");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        // 构造一个jedis客户端
        Jedis jedis = new Jedis("doitedu01", 6379);


        // 订阅主题
        consumer.subscribe(Arrays.asList("app_log"));

        // 循环拉取数据处理
        while (isRunnning.get()) {

            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));

            if (records.isEmpty()) continue;

            for (ConsumerRecord<String, String> record : records) {
                String logJson = record.value();

                // TODO
                // 解析json，抽出： ip地址，设备id，经度，维度

                // 用一个jedis客户端，拿着本条数据中的经纬度，去redis中查询所属的省市区，如果查询不到，则省市区置为 未知省，未知市，未知区

                // 用jedis客户端，将本条数据的ip地址，放入一个hyperloglog ==>  pfadd  IPCNT:湖南省:长沙市:岳麓区  101.203.67.65
                // 用jedis客户端，将本条数据的ip地址，放入一个hyperloglog ==>  pfadd  IPCNT:湖南省:长沙市  101.203.67.65
                // 用jedis客户端，将本条数据的ip地址，放入一个hyperloglog ==>  pfadd  IPCNT:湖南省  101.203.67.65

                // 用jedis客户端，将本条数据的device_id，放入一个hyperloglog ==>  pfadd  DEVCNT:湖南省:长沙市:岳麓区  193285LSDJFL
                // 用jedis客户端，将本条数据的device_id，放入一个hyperloglog ==>  pfadd  DEVCNT:湖南省:长沙市  193285LSDJFL
                // 用jedis客户端，将本条数据的device_id，放入一个hyperloglog ==>  pfadd  DEVCNT:湖南省  193285LSDJFL

                // 用jedis客户端，累计pv总数   :   incr  PVAMT

            }
        }

        consumer.close();
        jedis.close();
    }
}
