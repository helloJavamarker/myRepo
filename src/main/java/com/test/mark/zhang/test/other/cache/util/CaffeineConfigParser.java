package com.test.mark.zhang.test.other.cache.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.Asserts;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/**
 * Caffeine配置文件解析
 *
 * @author fuli
 * @version 1.0.0
 * @date 2019年1月8日
 */
public final class CaffeineConfigParser {
    public static final String CONFIG_MAX_SIZE = "maxSize";
    public static final String CONFIG_EXPIRING = "expiring";
    public static final String CONFIG_TIMEUNIT = "timeUnit";
    public static final String CONFIG_EXPIRING_POLICY = "expPolicy";

    /**
     * 从json串或类路径加载配置文件并解析
     *
     * @param jsonOrFile
     * @return JSONObject
     */
    public static JSONObject parse(String jsonOrFile) throws RuntimeException {
        try {
            if (jsonOrFile.toLowerCase().endsWith(".xml")) {
                return parseXML(jsonOrFile);
            } else if (jsonOrFile.toLowerCase().endsWith(".json")) {
                return parseJSON(jsonOrFile);
            }
        } catch (Exception e) {
            throw new RuntimeException("parse caffeine config file error!", e);
        }
        return JSON.parseObject(jsonOrFile);
    }

    private static JSONObject parseXML(String classPathConfigFile) throws Exception {
        JSONObject object = new JSONObject();
        // 创建SAXReader的对象reader
        SAXReader reader = new SAXReader();
        try (InputStream resourceAsStream = CaffeineConfigParser.class.getResourceAsStream(classPathConfigFile)) {
            Document document = reader.read(resourceAsStream);
            // 获取根节点cf-cache
            Element element = document.getRootElement();
            // 通过elementIterator方法获取迭代器
            Iterator<?> it = element.elementIterator();
            // 遍历迭代器，获取根节点中的信息（子节点defaultCache、cache）
            while (it.hasNext()) {
                Element e = (Element) it.next();
                // 获取的属性名以及 属性值
                @SuppressWarnings("unchecked")
                List<Attribute> attributes = e.attributes();
                if ("defaultCache".equals(e.getName())) {
                    cacheIterator(attributes, object);
                } else {
                    JSONObject cacheConfig = new JSONObject();
                    String cacheName = cacheIterator(attributes, cacheConfig);
                    Asserts.check(!StringUtils.isBlank(cacheName), "[cache] CaffeineShardCache cache name can not be null");
                    object.put(cacheName, cacheConfig);
                }
            }
        }
        return object;
    }

    private static String cacheIterator(List<Attribute> attributes, JSONObject json) {
        String name = null;
        for (Attribute attr : attributes) {
            if ("name".equals(attr.getName())) {
                name = attr.getValue();
            }
            if (CONFIG_MAX_SIZE.equals(attr.getName())) {
                json.put(CONFIG_MAX_SIZE, attr.getValue());
            }
            if (CONFIG_EXPIRING.equals(attr.getName())) {
                json.put(CONFIG_EXPIRING, attr.getValue());
            }
            if (CONFIG_TIMEUNIT.equals(attr.getName())) {
                json.put(CONFIG_TIMEUNIT, attr.getValue());
            }
            if (CONFIG_EXPIRING_POLICY.equals(attr.getName())) {
                json.put(CONFIG_EXPIRING_POLICY, attr.getValue());
            }
        }
        return name;
    }

    private static JSONObject parseJSON(String classPathConfigFile) throws Exception {
        try (InputStream in = CaffeineConfigParser.class.getResourceAsStream(classPathConfigFile)) {
            String result = IOUtils.toString(in, "UTF-8");
            //将JsonObject数据转换为Json
            JSONObject object = JSON.parseObject(result);
            return object;
        }
    }
}
