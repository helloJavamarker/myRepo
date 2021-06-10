package com.test.mark.zhang.test.agency.heima.disign.day3.pattern.bridge;

/**
 * @version v1.0
 * @ClassName: RmvbFile
 * @Description: rmvb视频文件（具体的实现化角色）
 * @Author: 黑马程序员
 */
public class RmvbFile implements VideoFile {

    @Override
    public void decode(String fileName) {
        System.out.println("rmvb视频文件 ：" + fileName);
    }
}
