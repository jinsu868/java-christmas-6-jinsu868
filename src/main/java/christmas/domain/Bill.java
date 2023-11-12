package christmas.domain;

import christmas.constant.DiscountType;
import christmas.constant.Grade;
import java.util.Map;

public class Bill {
    private Order order;
    private Map<DiscountType, Integer> discountResults;
    private Grade grade;

    public Bill(Order order, Map<DiscountType, Integer> discountResults) {
        this.grade = getGrade(discountResults);
        this.discountResults = discountResults;
        this.order = order;
    }

    public int getBeforeDiscountOrderAmount() {
        return order.calculateTotalOrderAmount();
    }

    public int getAfterDiscountOrderAmount() {
        return order.calculateTotalOrderAmount() - calculateDiscountAmount(discountResults);
    }

    private Grade getGrade(Map<DiscountType, Integer> discountResults) {
        int discountAmount = calculateDiscountAmount(discountResults);
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

    private int calculateDiscountAmount(Map<DiscountType, Integer> discountResults) {
        return discountResults.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
