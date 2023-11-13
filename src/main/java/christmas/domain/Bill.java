package christmas.domain;

import christmas.constant.DiscountType;
import christmas.constant.Badge;
import java.util.Map;

public class Bill {
    private Order order;
    private Map<DiscountType, Integer> discountResults;

    public Bill(Order order, Map<DiscountType, Integer> discountResults) {
        this.discountResults = discountResults;
        this.order = order;
    }

    public int calculateDiscountAmount() {
        return discountResults.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Badge getBadge() {
        int discountAmount = calculateDiscountAmount();
        if (discountAmount >= Badge.STAR.getMinBoundary() && discountAmount < Badge.STAR.getMaxBoundary()) {
            return Badge.STAR;
        }
        if (discountAmount >= Badge.TREE.getMinBoundary() && discountAmount < Badge.TREE.getMaxBoundary()) {
            return Badge.TREE;
        }
        if (discountAmount >= Badge.SANTA.getMinBoundary()) {
            return Badge.SANTA;
        }
        return Badge.NONE;
    }

    public int getBeforeDiscountOrderAmount() {
        return order.calculateTotalOrderAmount();
    }

    public int getAfterDiscountOrderAmount() {
        return order.calculateTotalOrderAmount() - calculateRealDiscountAmount();
    }

    private int calculateRealDiscountAmount() {
        return discountResults.entrySet().stream()
                .filter(discountResult -> !discountResult.getKey().getType()
                        .equals(DiscountType.GIVEAWAY.getType()))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }
}
