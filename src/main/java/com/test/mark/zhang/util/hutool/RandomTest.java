package com.test.mark.zhang.util.hutool;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.junit.Test;

import javax.sound.midi.spi.SoundbankReader;

/**
 * @author by mark
 * @Classname RandomTest
 * @Description TODO
 * @Date 2021/7/20 8:51 上午
 */
public class RandomTest {

    @Test
    public void random() {

        //java产生的都是伪随机数,但是基本够用

        //RandomUtil.randomInt 获得指定范围内的随机数
        //RandomUtil.randomBytes 随机bytes
        //RandomUtil.randomEle 随机获得列表中的元素
        //RandomUtil.randomEleSet 随机获得列表中的一定量的不重复元素，返回Set
        //RandomUtil.randomString 获得一个随机的字符串（只包含数字和字符）
        //RandomUtil.randomNumbers 获得一个只包含数字的字符串
        //RandomUtil.randomUUID 随机UUID
        //RandomUtil.weightRandom 权重随机生成器，传入带权重的对象，然后根据权重随机获取对象



        //分布式唯一id生成
        //生成的UUID是带-的字符串，类似于：a5c8a5e8-df2b-4706-bea4-08d0939410e3
        String uuid = IdUtil.randomUUID();

        //生成的是不带-的字符串，类似于：b17f24ff026d40949c85a24f4f375d42
        String simpleUUID = IdUtil.simpleUUID();
        //说明 Hutool重写java.util.UUID的逻辑，对应类为cn.hutool.core.lang.UUID，使生成不带-的UUID字符串不再需要做字符替换，性能提升一倍左右。


        //参数1为终端ID
        //参数2为数据中心ID    id按时间有序生成 希望唯一并且有顺序---id作为索引,不用使用随机uuid
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        long id = snowflake.nextId();
        // 1417287239293079552
        System.out.println(id);
        //注意 IdUtil.createSnowflake每次调用会创建一个新的Snowflake对象，不同的Snowflake对象创建的ID可能会有重复，因此请自行维护此对象为单例，或者使用IdUtil.getSnowflake使用全局单例对象。

    }

    @Test
    public void idTest() {
        //IdcardUtil现在支持大陆15位、18位身份证，港澳台10位身份证。
        //
        //工具中主要的方法包括：
        //
        //isValidCard 验证身份证是否合法
        //convert15To18 身份证15位转18位
        //getBirthByIdCard 获取生日
        //getAgeByIdCard 获取年龄
        //getYearByIdCard 获取生日年
        //getMonthByIdCard 获取生日月
        //getDayByIdCard 获取生日天
        //getGenderByIdCard 获取性别
        //getProvinceByIdCard 获取省份
    }

    @Test
    public void desensitizedTest() {
        //现阶段支持的脱敏数据类型包括：
        //
        //用户id
        //中文姓名
        //身份证号
        //座机号
        //手机号
        //地址
        //电子邮件
        //密码
        //中国大陆车牌，包含普通车辆、新能源车辆
        //银行卡
        //整体来说，所谓脱敏就是隐藏掉信息中的一部分关键信息，用*代替，自定义隐藏可以使用StrUtil.hide方法完成。
    }

    @Test
    public void jsonTest() {
        JSONObject jsonObject = new JSONObject();
        SwitchAction switchAction = new SwitchAction();
        switchAction.setHobby("eat");
        switchAction.setName("zhang");
        jsonObject.put("local", switchAction);
        System.out.println(jsonObject);

        JSONObject object = new JSONObject();
        object.put("name1","zhang1");
        object.put("hobby","eat1");
        jsonObject.put("local", object);
        System.out.println(jsonObject);
    }

    @Data
    class SwitchAction {
        String name;
        String hobby;
    }

    @Test
    public void json() {
        JSONObject content = new JSONObject();
        JSONObject param = new JSONObject();
        content.put("code1", "org1");
        content.put("code2", "org1");
        content.put("code3", "org3");
        param.put("action", true);
        //param.put("content", content);
        param.put("content", JSONObject.toJSONString(content));
        System.out.println(param);
    }
}
