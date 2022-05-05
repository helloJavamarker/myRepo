package com.test.mark.zhang.test.other.project.temp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author by mark
 * @Classname Temp
 * @Description TODO
 * @Date 2022/4/24 4:02 下午
 */
public class Temp {

    private static String data = "{ \"total\":1, \"rows\":[ { \"agentId\":\"70db8ef89e9ae79a\", \"displayIp\":\"172.16.2.231\", \"connectionIp\":\"172.16.2.231\", \"externalIp\":null, \"internalIp\":null, \"bizGroupId\":null, \"bizGroup\":\"被删除分组\", \"remark\":null, \"hostTagList\":[ \"test-test000\", \"tmp\" ],\"hostname\":\"qingteng\", \"proxyIp\":null, \"platform\":\"Ubuntu 16.04.2 LTS\", \"kernelVersion\":\"4.13.0-26-generic\", \"cpu\":{ \"core\":4, \"producer\":\"GenuineIntel\", \"brand\":\"Intel(R) Core(TM) i5-7500 CPU @ 3.40GHz\", \"description\":\"GenuineIntel 4 Intel(R) Core(TM) i5-7500 CPU @ 3.40GHz\", \"loadAvgFifteen\": 0.35},\"memoryUsage\":null, \"memorySize\":null, \"onlineStatus\":1, \"agentStatus\":0, \"lastOnlineTime\":null, \"installTime\":null, \"agentVersion\":\"3.0.7-3.34.0-RC-Debug-2018-03-30_18-50-07-436\", \"bashVersion\":null, \"bashPluginInstalled\":null, \"offlineDays\": 0, \"chargeName\": \"qingteng\", \"hostLocation\": \"wuhan\", \"systemLoad\": 1, \"memories\": [ { \"type\": null, \"producer\": \"Not Specified\", \"size\": 980, \"speed\": 0, \"description\": \"0.96GB(Not Specified null 0MHZ)\", \"producerSize\": 2048, \"bank\": \"Not Specified 2.0GB 0MHZ\" } ],\"manufacturer\": \"\", \"productName\": \"\", \"serialNumber\": \"\", \"networkCards\": [ { \"name\": \"eth0\", \"mac\": \"00:50:56:36:35:bb\", \"ipv4\": \"192.168.78.131\", \"ipv6\": \"fe80::250:56ff:fe36:35bb\", \"gateway\": \"192.168.78.2\", \"dnsServer\": [ \"yes\" ]},{ \"name\": \"lo\", \"mac\": \"00:00:00:00:00:00\", \"ipv4\": \"127.0.0.1\", \"ipv6\": \"::1\", \"gateway\": \"\", \"dnsServer\": [ \"192.168.78.2\" ] } ],\"diskSize\": 51200, \"diskUsage\": 0.6096, \"diskCount\": 4, \"uuid\":\"dsdsdsdsdfdf\" } ] }";
    private static List<String> list = Arrays.asList("zhang","san", "zhangsan",  "张三", "shang", "lisi", "zhangsanfeng", "李四");
    public static void main(String[] args) {
        JSONObject jsonObject = JSON.parseObject(data);
        JSONArray rows = jsonObject.getJSONArray("rows");
        for (Object row : rows) {
            JSONObject rowSub = (JSONObject) row;
            System.out.println(rowSub.getString("agentId"));
            JSONArray networkCards = rowSub.getJSONArray("networkCards");
            for (Object networkCard : networkCards) {
                JSONObject netObj = (JSONObject) networkCard;
                System.out.println(netObj.getString("name"));
                System.out.println(netObj.getString("ipv4"));
            }
        }
    }

    @Test
    public void testSort() {
        list.stream().sorted().forEach(System.out::println);
        System.out.println(".......");
        String input = "an";
        list.stream().sorted(Comparator.comparing(data-> {
            if (data.contains(input)) {
                return 0;
            }
            return data.length();
        })).forEach(System.out::println);
    }

}
