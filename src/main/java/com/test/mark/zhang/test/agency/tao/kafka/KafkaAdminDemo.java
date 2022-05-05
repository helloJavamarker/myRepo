package com.test.mark.zhang.test.agency.tao.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * kafka管理客户端api演示
 */
public class KafkaAdminDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Properties props = new Properties();
        props.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "doitedu01:9092");


        // 构造一个管理客户端对象
        AdminClient adminClient = KafkaAdminClient.create(props);

        // 示例1： 列出集群中的主题
        // ListTopicsResult listTopicsResult = adminClient.listTopics();
        // KafkaFuture<Set<String>> names = listTopicsResult.names();
        // Set<String> topicNames = names.get();
        // System.out.println(topicNames);


        // 示例2： 查看一个topic的具体信息
        //DescribeTopicsResult tpc_1 = adminClient.describeTopics(Arrays.asList("tpc_1"));
        //KafkaFuture<Map<String, TopicDescription>> future = tpc_1.all();
        //Map<String, TopicDescription> stringTopicDescriptionMap = future.get(); //  get会阻塞到返回结果为止
        //Set<Map.Entry<String, TopicDescription>> entries = stringTopicDescriptionMap.entrySet();
        //for (Map.Entry<String, TopicDescription> entry : entries) {
        //    System.out.println(entry.getKey());
        //    TopicDescription desc = entry.getValue();
        //    System.out.println(desc.name() + "," +desc.partitions());
        //    System.out.println("------------------闷骚的分割线-----------------");
        //}

        // 示例3：创建一个topic
        HashMap<Integer, List<Integer>> partitions = new HashMap<>();
        partitions.put(0, Arrays.asList(0, 2));
        partitions.put(1, Arrays.asList(1, 2));
        partitions.put(2, Arrays.asList(0, 1));

        NewTopic tpc_2 = new NewTopic("tpc_2", partitions);
        adminClient.createTopics(Arrays.asList(tpc_2));

        NewTopic tc = new NewTopic("tc", 2, (short) 1);
        adminClient.createTopics(Arrays.asList(tc));

        adminClient.close();
    }
}
