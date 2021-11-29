package com.test.mark.zhang.test.other.auth;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author by mark
 * @Classname MenuSpecialHandler
 * @Description TODO
 * @Date 2021/11/16 9:34 上午
 */
@Slf4j
public class MenuSpecialHandler extends BaseTypeHandler<MenuSpecial> {

    //BaseTypeHandler 是mybatis里的
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int columnIdx, MenuSpecial menuSpecial, JdbcType jdbcType) throws SQLException {
        String value = null;
        if (menuSpecial != null) {
            value = ClassUtil.getClassName(menuSpecial, false);
        }
        preparedStatement.setString(columnIdx, value);
    }

    @Override
    public MenuSpecial getNullableResult(ResultSet resultSet, String columnIdx) throws SQLException {
        return parse(resultSet.getString(columnIdx));
    }

    @Override
    public MenuSpecial getNullableResult(ResultSet resultSet, int columnIdx) throws SQLException {
        return parse(resultSet.getString(columnIdx));
    }

    @Override
    public MenuSpecial getNullableResult(CallableStatement callableStatement, int columnIdx) throws SQLException {
        return parse(callableStatement.getString(columnIdx));
    }

    public static MenuSpecial parse(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        MenuSpecial special = SpringUtil.getBean(value, MenuSpecial.class);
        if (special == null) {
            log.warn("菜单特殊化配置未找到:[{}]", value);
        }
        return special;
    }
}
