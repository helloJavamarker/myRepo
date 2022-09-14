package com.test.mark.zhang.test.other.project.asset.tag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author by mark
 * @Classname DisplayTag
 * @Description TODO
 * @Date 2022/6/30 10:04 上午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisplayTag {

    private String key;
    private String value;
    /**
     * 标签来源
     */
    private String source;

    public static Builder creator() {
        return new Builder();
    }

    /**
     * 必须是public
     */
    public static class Builder {
        private final DisplayTag displayTag;

        private Builder() {
            this.displayTag = new DisplayTag();
        }

        public Builder key(String key) {
            this.displayTag.setKey(key);
            return this;
        }

        public Builder value(String value) {
            this.displayTag.setValue(value);
            return this;
        }

        public Builder source(String source) {
            this.displayTag.setSource(source);
            return this;
        }

        public DisplayTag build() {
            return this.displayTag;
        }
    }

}
