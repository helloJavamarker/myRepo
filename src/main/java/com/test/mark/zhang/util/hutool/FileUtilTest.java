package com.test.mark.zhang.util.hutool;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;

import java.io.File;

/**
 * @author by mark
 * @Classname FileUtilTest
 * @Description TODO
 * @Date 2022/6/17 5:10 下午
 */
public class FileUtilTest {
    //项目中有遇到 对于上传文件类型的判断   我们需要上传的文件是 excel类型的
    //如果获取文件名然后去判断 后缀名是否是 .xls 或者是 .xlsx 类型  但是这种判断是不严谨的  假如有人恶意攻击   将恶心文件的后缀名改成 xls/xlsx  就能轻易的进行next....所有需要进一步的判断

    //这个类是通过读取文件流中前N个byte值来判断文件类型，在类中我们通过Map形式将常用的文件类型做了映射，这些映射都是网络上搜集而来。
    // 也就是说，我们只能识别有限的几种文件类型。但是这些类型已经涵盖了常用的图片、音频、视频、Office文档类型，可以应对大部分的使用场景
    public static void main(String[] args) {
        //https://hutool.cn/docs/#/core/IO/%E6%96%87%E4%BB%B6%E7%B1%BB%E5%9E%8B%E5%88%A4%E6%96%AD-FileTypeUtil  hutool 官网
        System.out.println(FileTypeUtil.getType(new File("")));

        File file = FileUtil.file("d:/test.jpg");
        String type = FileTypeUtil.getType(file);
        //输出 jpg则说明确实为jpg文件
        Console.log(type);
    }
}
