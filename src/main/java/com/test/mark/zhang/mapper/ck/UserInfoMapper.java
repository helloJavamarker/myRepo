package com.test.mark.zhang.mapper.ck;

import com.test.mark.zhang.cache.caffeine.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author by mark
 * @Classname UserInfoMapper
 * @Description TODO
 * @Date 2021/9/7 3:57 下午
 */
@Mapper
public interface UserInfoMapper {
    /**
     * clickhouse 不应该作为通用的数据存储,适合特定场景
     * · 大多数是读请求
     * · 数据总是以相当大的批(> 1000 rows)进行写入
     * · 不修改已添加的数据
     * · 每次查询都从数据库中读取大量的行，但是同时又仅需要少量的列
     * · 宽表，即每个表包含着大量的列
     * · 较少的查询(通常每台服务器每秒数百个查询或更少)
     * · 对于简单查询，允许延迟大约50毫秒
     * · 列中的数据相对较小： 数字和短字符串(例如，每个URL 60个字节)
     * · 处理单个查询时需要高吞吐量（每个服务器每秒高达数十亿行）
     * · 事务不是必须的
     * · 对数据一致性要求低
     * · 每一个查询除了一个大表外都很小
     * · 查询结果明显小于源数据，换句话说，数据被过滤或聚合后能够被盛放在单台服务器的内存中
     *
     * @param userInfo
     */
    // 写入数据
    void saveData (UserInfo userInfo) ;
    // ID 查询
    UserInfo selectById (@Param("id") Integer id) ;
    // 查询全部
    List<UserInfo> selectList () ;
}

