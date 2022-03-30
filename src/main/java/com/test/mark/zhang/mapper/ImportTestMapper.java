package com.test.mark.zhang.mapper;

import com.test.mark.zhang.entity.ImportTestBean;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author by mark
 * @Classname ImportTestMapper
 * @Description TODO
 * @Date 2022/2/26 2:31 下午
 */
@Mapper
public interface ImportTestMapper {

    void importFirst(ImportTestBean importTestBean);

    void importSecond(ImportTestBean importTestBean);
}
