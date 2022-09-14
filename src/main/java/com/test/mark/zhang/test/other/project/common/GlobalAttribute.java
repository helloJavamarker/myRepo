package com.test.mark.zhang.test.other.project.common;

import cn.hutool.core.io.FileUtil;
import org.apache.logging.log4j.util.Strings;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author by mark
 * @Classname GlobalAttriute
 * @Description TODO
 * @Date 2022/7/14 9:29 上午
 */
public class GlobalAttribute {



    public static synchronized String removeKey(String key) {
        String info = "";
        StringBuffer buffer = new StringBuffer();
        String globalPath = "xxx";
        String globalContent = FileUtil.readString(globalPath, StandardCharsets.UTF_8);
        Arrays.stream(globalContent.split("\n"))
                .filter(Strings::isNotBlank)
                .forEach(line -> {
                    if (line.startsWith("#")) {
                        // 注释直接添加
                        buffer.append(line).append("\n");
                    } else if (line.contains("=")) {
                        String field = line.substring(0, line.indexOf("=")).trim();
                        if (!field.equals(key)) {
                            buffer.append(line).append("\n");
                        }
                    }
                });

        return info;
    }

    public void load0() {
        // 注释 以#或者!开头
        String command = "";
        if (command.trim().startsWith("#") || command.trim().startsWith("!")) {
            command = command.substring(1);
        }
    }

}
