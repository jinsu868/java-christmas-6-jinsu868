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
        if (discountAmount >= 5000 && discountAmount < 10000) {
            return Badge.STAR;
        }
        if (discountAmount >= 10000 && discountAmount < 20000) {
            return Badge.TREE;
        }
        if (discountAmount >= 20000) {
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
