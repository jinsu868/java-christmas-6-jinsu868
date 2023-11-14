package christmas.domain;

import java.util.Arrays;

public enum Badge {
    SANTA("산타", 20000, Integer.MAX_VALUE),
    STAR("별", 5000, 10000),
    TREE("트리", 10000, 20000),
    NONE("없음", 0, 5000);

    private String badge;
    private int minBoundary;
    private int maxBoundary;

    private Badge(String badge, int minBoundary, int maxBoundary) {
        this.badge = badge;
        this.minBoundary = minBoundary;
        this.maxBoundary = maxBoundary;
    }

    public static Badge from(int totalDiscountAmount) {
        return Arrays.stream(Badge.values())
                .filter(badge -> totalDiscountAmount >= badge.getMinBoundary()
                        && (totalDiscountAmount < badge.getMaxBoundary()))
                .findFirst()
                .orElse(Badge.NONE);
    }

    public String getBadge() {
        return badge;
    }

    public int getMinBoundary() {
        return minBoundary;
    }

    public int getMaxBoundary() {
        return maxBoundary;
    }
}
