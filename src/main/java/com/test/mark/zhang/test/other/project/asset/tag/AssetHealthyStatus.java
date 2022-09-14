package com.test.mark.zhang.test.other.project.asset.tag;

/**
 * @author by mark
 * @Classname AssetHealthyStatus
 * @Description TODO
 * @Date 2022/6/30 10:18 上午
 */
public enum AssetHealthyStatus {
    /**
     *
     */
    fallen("已失陷", 59, 0),
    high_risk("高风险", 69, 60),
    medium_risk("中风险", 79, 70),
    low_risk("低风险", 89, 80),
    healthy("健康", 100, 90),
    unknown("未知", -1, -1);

    private final String value;
    private final int maxScore;
    private final int minScore;

    AssetHealthyStatus(String value, int maxScore, int minScore) {
        this.value = value;
        this.maxScore = maxScore;
        this.minScore = minScore;
    }

    public String value() {
        return value;
    }

    public double getMaxScore() {
        return maxScore;
    }

    public double getMinScore() {
        return minScore;
    }
}
