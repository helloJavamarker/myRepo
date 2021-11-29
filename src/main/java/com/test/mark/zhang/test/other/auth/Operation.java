package com.test.mark.zhang.test.other.auth;

/**
 * @author by mark
 * @Classname Operation
 * @Description TODO
 * @Date 2021/11/16 9:29 上午
 */
public enum Operation {
    /**
     *
     */
    Search("查询"),
    Add("新增"),
    Edit("编辑"),
    Delete("删除"),
    Import("导入"),
    Export("导出");

    private String name;
    Operation(String name) {
        this.name=name;
    }
    public String getName() {
        return name;
    }
}
