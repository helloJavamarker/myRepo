package com.test.mark.zhang.test.other.auth;

import cn.hutool.poi.excel.sax.SheetRidReader;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import java.util.List;

/**
 * @author by mark
 * @Classname MenuConfig
 * @Description TODO
 * @Date 2021/11/16 9:24 上午
 */
@Data
public class MenuConfig {

    private String code;
    private String parentCode;
    private String name;
    private String type;
    private int module;
    private String url;
    private String href;
    private String icon;
    private String group;
    private Double sort;
    private String description;
    private List<MenuConfig> children;

    private MenuSpecial special;

    @JsonSetter("special")
    public void setSpecial(String special) {
        this.special = MenuSpecialHandler.parse(special);
    }

    private List<Operation> operations;
}
