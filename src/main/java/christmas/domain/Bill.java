package christmas.domain;

import christmas.constant.DiscountAmount;
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

    public boolean judgeGiveaway() {
        if (order.calculateTotalOrderAmount() >= DiscountAmount.MIN_GIVEAWAY.getAmount()) {
            return true;
        }
        return false;
    }

    public Order getOrder() {
        return order;
    }

    public Map<DiscountType, Integer> getDiscountResults() {
        return discountResults;
    }

    public Badge getBadge() {
        int discountAmount = calculateDiscountAmount();
        return Badge.generate(discountAmount);
    }

    public int getBeforeDiscountTotalOrderAmount() {
        return order.calculateTotalOrderAmount();
    }

    public int getAfterDiscountOrderAmount() {
        return order.calculateTotalOrderAmount() - calculateDiscountAmountWithoutGiveaway();
    }

    private int calculateDiscountAmountWithoutGiveaway() {
        return discountResults.entrySet().stream()
                .filter(discountResult -> !discountResult.getKey().getType()
                        .equals(DiscountType.GIVEAWAY.getType()))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }
}
