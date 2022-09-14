package com.test.mark.zhang.test.other.project.asset;

import com.test.mark.zhang.test.other.project.asset.util.RegExUtil;
import org.springframework.lang.NonNull;


/**
 * @author by mark
 * @Classname AssetType
 * @Description TODO
 * @Date 2022/7/8 2:57 下午
 */
public enum AssetType {
    /**
     *
     */
    private_Ip("private_Ip", "私网ip", RegExUtil::verifyIp),
    public_Ip("public_Ip", "公网ip", RegExUtil::verifyIp),
    private_mac("mac", "mac地址", RegExUtil::verifyMac),
    private_phone("phone", "手机", RegExUtil::verifyIp),
    private_domain("domain", "域名", RegExUtil::verifyDomain);

    private final String formatType;
    private final String value;
    private final AilphaValidator validator;

    AssetType(String formatType, String value, AilphaValidator validator) {
        this.formatType = formatType;
        this.value = value;
        this.validator = validator;
    }

    public boolean validate(@NonNull String asset) {
        return validator.validate(asset);
    }

    private interface AilphaValidator {
        boolean validate(String asset);
    }
}
