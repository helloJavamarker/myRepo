package com.test.mark.zhang.test.other.static0.static03;

public class MyProducer {
    private MyProperties properties;
    private String name;

    public MyProducer(MyProperties properties) {
        this.properties = properties;
    }

    public void send(String topic, String msg) {
        System.out.println("topic:" + topic + ",and msg is: " + msg);
    }

//    private Map<String, Object> propsToMap(MyProperties properties) {
//        Map<String, Object> map = new HashMap(properties.size());
//        Iterator var2 = properties.entrySet().iterator();
//
//        while(var2.hasNext()) {
//            Map.Entry<Object, Object> entry = (Map.Entry)var2.next();
//            if (!(entry.getKey() instanceof String)) {
//                throw new ConfigException(entry.getKey().toString(), entry.getValue(), "Key must be a string.");
//            }
//
//            String k = (String)entry.getKey();
//            map.put(k, properties.get(k));
//        }
//        return map;
//    }
}
