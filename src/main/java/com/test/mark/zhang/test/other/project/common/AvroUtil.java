package com.test.mark.zhang.test.other.project.common;

import cn.hutool.core.util.ReflectUtil;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.file.SeekableByteArrayInput;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecordBase;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author by mark
 * @Classname AvroTest
 * @Description TODO
 * @Date 2022/6/29 2:04 下午
 */
public class AvroUtil {

    /**
     * Avro 简化了在以任何语言编写的程序之间交换大数据的过程。 借助序列化服务，程序可以将数据高效地序列化为文件或消息   kafka等数据量较大的序列化建议--和阿帕奇生态兼容最好
     * @link:https://juejin.cn/post/7047704464265838629
     * @param tClass
     * @param data
     * @param <T>
     * @return
     */
    public static <T extends SpecificRecordBase> byte[] avroSerialize(Class<T> tClass, T data) {
        return avroSerialize(tClass, Collections.singletonList(data));
    }

    /**
     * 序列化成byte数组
     * @param tClass
     * @param dataList
     * @param <T>
     * @return
     */
    private static <T extends SpecificRecordBase> byte[] avroSerialize(Class<T> tClass, List<T> dataList) {
        try {
            DatumWriter<T> datumWriter = new SpecificDatumWriter<>(tClass);
            DataFileWriter<T> dataFileWriter = new DataFileWriter<>(datumWriter);
            Method staticSay = ReflectUtil.getMethod(tClass, "getClassSchma");
            Schema schema = ReflectUtil.invokeStatic(staticSay);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            dataFileWriter.create(schema, byteArrayOutputStream);
            for (T item : dataList) {
                dataFileWriter.append(item);
            }
            dataFileWriter.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            System.out.println("serial error");
        }
        return new byte[0];
    }

    /**
     * 反序列化
     * @param tClass
     * @param bytes
     * @param <T>
     * @return
     */
    private static <T extends SpecificRecordBase> List<T> avroDeserialize(Class<T> tClass, byte[] bytes) {
        List<T> list = new ArrayList<>();
        try {
            SeekableByteArrayInput seekableByteArrayInput = new SeekableByteArrayInput(bytes);
            DatumReader<T> datumReader = new SpecificDatumReader<>();
            DataFileReader<T> dataFileReader = new DataFileReader<T>(seekableByteArrayInput, datumReader);
            while (dataFileReader.hasNext()) {
                list.add(dataFileReader.next());
            }
        } catch (Exception e) {
            System.out.println("deserialize error");
        }
        return list;
    }

}
