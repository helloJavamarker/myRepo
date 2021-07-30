package com.test.mark.zhang.test.other.project.cache.mapper;

import com.test.mark.zhang.test.other.project.cache.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author by mark
 * @Classname SecurityZoneDao
 * @Description TODO
 * @Date 2021/7/21 2:43 下午
 */
@Mapper
public interface SecurityZoneDao {

    List<Role> getAll();

    int count();

    void number();
}
