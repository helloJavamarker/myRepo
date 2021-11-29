package com.test.mark.zhang.controller.kafka;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.test.mark.zhang.entity.Person;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

@Component
public class ConsumerDemo {
    @KafkaListener(topics = "topicxxx")
    public void listen (ConsumerRecord<?, ?> record){
        System.out.printf("topic is %s, offset is %d, value is %s \n", record.topic(), record.offset(), record.value());
    }

    public static void main(String[] args) {
        Date date = new Date(1629475200000L);
        System.out.println(DateUtil.between(new Date(1629475200000L), new Date(), DateUnit.MS));
        Object obj = getObject();
        Field[] fields = obj.getClass().getDeclaredFields();
        HashMap<String, Object> map = new HashMap<>();
        for (Field field : fields) {
            ReflectionUtils.makeAccessible(field);
            Object value = ReflectionUtils.getField(field, obj);
            System.out.println(value);
            if (value != null)
                map.put(field.getName(), value);
        }
        System.out.println(map);

    }

    private static Object getObject() {

        Person person = new Person();
        person.setAge(12);
        person.setHobby("hobby");
        person.setName("zhangsan");
        return person;
    }

}
