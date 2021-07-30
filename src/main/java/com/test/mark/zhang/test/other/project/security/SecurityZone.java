package com.test.mark.zhang.test.other.project.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Classname SecurityZone
 * @Description TODO
 * @Date 2021/6/25 3:15 下午
 * @Created by mark
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SecurityZone {

    private String id;
    private String name;
    private String iconPath;
    private long createTime;
    private String tag;
    private String orgIds;
    private String orgAbsolutePaths;

}
