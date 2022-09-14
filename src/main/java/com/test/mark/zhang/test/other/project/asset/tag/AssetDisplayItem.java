package com.test.mark.zhang.test.other.project.asset.tag;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author by mark
 * @Classname AssetDisplayItem
 * @Description TODO
 * @Date 2022/6/30 10:09 上午
 */
public class AssetDisplayItem {
    private String assetId;
    private String assetName;
    private String assetType;
    //...
    private String assetLabel;
    private String assetTags;
    private List<DisplayTag> displayTags;
    private AssetHealthyStatus assetHealthyStatus;

    public AssetDisplayItem toFrontData() {
        if (displayTags == null) {
            displayTags = new ArrayList<>();
        }
        if (assetHealthyStatus != null && assetHealthyStatus != AssetHealthyStatus.unknown) {
            displayTags.add(DisplayTag.creator()
                    .key(assetHealthyStatus.name())
                    .value(assetHealthyStatus.value())
                    .source("healthy")
                    .build());
        } else {
            assetHealthyStatus = AssetHealthyStatus.healthy;
            displayTags.add(DisplayTag.creator()
                    .key(assetHealthyStatus.name())
                    .value(assetHealthyStatus.value())
                    .source("healthy")
                    .build());
        }
        //...
        // 自定义标签
        if (StringUtils.isNotBlank(assetLabel)) {
            displayTags.addAll(Stream.of(assetLabel.split(","))
                    .filter(StringUtils::isNotBlank).map(assetLabel ->
                            DisplayTag.creator()
                                    .key(assetLabel)
                                    .value(assetLabel)
                                    .source("xxx")
                                    .build()
                    ).collect(Collectors.toList()));
        }
        return this;
    }
}
