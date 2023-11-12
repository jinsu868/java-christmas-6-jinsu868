package christmas.domain;

import christmas.constant.DiscountType;
import christmas.constant.Grade;
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

    public Grade getGrade() {
        int discountAmount = calculateDiscountAmount();
        if (discountAmount >= 5000 && discountAmount < 10000) {
            return Grade.STAR;
        }
        if (discountAmount >= 10000 && discountAmount < 20000) {
            return Grade.TREE;
        }
        if (discountAmount >= 20000) {
            return Grade.SANTA;
        }
        return Grade.NONE;
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
