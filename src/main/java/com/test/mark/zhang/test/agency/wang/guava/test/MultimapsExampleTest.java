package com.test.mark.zhang.test.agency.wang.guava.test;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/***************************************
 * @author:Alex Wang
 * @Date:2018/1/14
 * QQ: 532500648
 * QQ群:463962286
 ***************************************/
public class MultimapsExampleTest {

    @Test
    public void testBasic() {
        LinkedListMultimap<String, String> multipleMap = LinkedListMultimap.create();
        HashMap<String, String> hashMap = Maps.newHashMap();
        hashMap.put("1", "1");
        hashMap.put("1", "2");
        assertThat(hashMap.size(), equalTo(1));


        multipleMap.put("1", "1");
        multipleMap.put("1", "2");

        multipleMap.putAll("2", Arrays.asList("3","4","5"));
        assertThat(multipleMap.size(), equalTo(2));
        System.out.println(multipleMap.get("1"));
        //key 对应一个list
    }

    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        method(list);
        System.out.println(list);

        String str = "1234#zafaa";
        System.out.println(str.substring(0, str.indexOf("#")));
        System.out.println(str.substring(str.indexOf("#")+1));
    }

    private void method(List<String> list) {
        add("1", list);
        add("2", list);
    }

    private void add(String data ,List<String> list) {
        list.add(data);
    }

    @Test
    public void testRetain() {
        List<AssetLevel> assetLevels = generateData(5);
        List<AssetLevel> assetLevels2 = generateData(2);
        List<AssetLevel> assetLevels1 = generateRetainData(2);
        System.out.println(assetLevels);
        assetLevels.retainAll(assetLevels1);
        System.out.println(assetLevels);
    }

    @Test
    public void testRetain2() {
        List<String> list = new ArrayList<>();
        List<String> listRetain = new ArrayList<>();
        listRetain.add("zhang");
        list.add("zhang");
        list.add("san");
        list.add("li");
        list.add("si");
        list.retainAll(listRetain);
        System.out.println(list);
        System.out.println(listRetain);
        listRetain.removeAll(list);
        System.out.println(list);
        System.out.println(listRetain);
    }

    private List<AssetLevel> generateData(int num) {
        List<AssetLevel> assetLevels = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            AssetLevel assetLevel = new AssetLevel();
            assetLevel.setId("id:" + i);
            assetLevel.setNetwork("network:" + i);
            assetLevel.setLevel("level:" + i);
            assetLevel.setName("name:" + i);
            assetLevels.add(assetLevel);
        }
        return assetLevels;
    }

    private List<AssetLevel> generateRetainData(int num) {
        List<AssetLevel> assetLevels = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            AssetLevel assetLevel = new AssetLevel();
            assetLevel.setId("id:" + i);
            assetLevel.setNetwork("network:" + i);
            //assetLevel.setLevel("level:" + i);
            assetLevels.add(assetLevel);
        }
        return assetLevels;
    }

    @Test
    public void testGroup() {
        List<AssetLevel> assetLevels = new ArrayList<>();
        AssetLevel assetLevel1 = new AssetLevel("1", "net1", "level1", "name1");
        AssetLevel assetLevel2 = new AssetLevel("2", "net2", "level2", "name2");
        AssetLevel assetLevel3 = new AssetLevel("3", "net3", "level3", "name3");
        AssetLevel assetLevel4 = new AssetLevel("4", "net2", "level4", "name4");
        AssetLevel assetLevel5 = new AssetLevel("5", "net1", "level5", "name5");
        AssetLevel assetLevel6 = new AssetLevel("6", "net3", "level6", "name6");
        AssetLevel assetLevel7 = new AssetLevel("7", "net3", "level7", "name7");
        AssetLevel assetLevel8 = new AssetLevel("8", "net2", "level8", "name8");
        AssetLevel assetLevel9 = new AssetLevel("9", "net9", "level9", "name9");
        assetLevels.add(assetLevel1);
        assetLevels.add(assetLevel2);
        assetLevels.add(assetLevel3);
        assetLevels.add(assetLevel4);
        assetLevels.add(assetLevel5);
        assetLevels.add(assetLevel6);
        assetLevels.add(assetLevel7);
        assetLevels.add(assetLevel8);
        assetLevels.add(assetLevel9);

        System.out.println(assetLevels.stream().collect(Collectors.groupingBy(AssetLevel::getNetwork)));
        //{"action": "enable", "content": {"assetTypes": ["server"], "method": "", "ipintervals": {}}}
        //{"orgId1": {"action": "enable", "content": {}}, "orgId2": {"action": "enable", "content": {}}}
    }


}

@Data
@AllArgsConstructor
@NoArgsConstructor
class AssetLevel {
    private String id;
    private String network;
    private String level;
    private String name;
}