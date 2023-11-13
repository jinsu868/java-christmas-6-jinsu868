package christmas.domain;

import christmas.constant.Date;
import christmas.constant.DiscountAmount;
import christmas.constant.DiscountType;
import christmas.constant.Menu;
import christmas.constant.MenuType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountManager {
    private static final int D_DAY_DISCOUNT_LAST_DAY = 25;
    private static final int D_DAY_DISCOUNT_FIRST_DAY = 1;
    private Order order;

    public DiscountManager(Order order) {
        this.order = order;
    }

    public Map<DiscountType, Integer> getDiscountResults() {
        Map<DiscountType, Integer> discountResults = new HashMap<>();
        int visitDate = order.getVisitDate();
        List<OrderMenu> orderMenus = order.getOrderMenus();
        if (order.calculateTotalOrderAmount() < DiscountAmount.MIN.getAmount()) {
            return discountResults;
        }

        applyDDayDiscount(discountResults, visitDate);
        applySpecialDayDiscount(discountResults, visitDate);
        applyWeekEndDiscount(discountResults, orderMenus, visitDate);
        applyWeekDayDiscount(discountResults, orderMenus, visitDate);
        applyGiveaway(discountResults);
        return discountResults;
    }

    private void applyGiveaway(Map<DiscountType, Integer> discountResults) {
        if (order.calculateTotalOrderAmount() >= DiscountAmount.MIN_GIVEAWAY.getAmount()) {
            discountResults.put(DiscountType.GIVEAWAY, Menu.CHAMPAGNE.getPrice());
        }
    }

    private void applyWeekDayDiscount(Map<DiscountType, Integer> discountResults, List<OrderMenu> orderMenus,
                                      int visitDate) {
        if (Date.isWeekDay(visitDate)) {
            int discountAmount = orderMenus.stream()
                    .filter(orderMenu -> MenuType.DESSERT.getType()
                            .equals(orderMenu.getMenu().getKind()))
                    .mapToInt(orderMenu -> DiscountAmount.WEEKDAY.getAmount() * orderMenu.getQuantity())
                    .sum();
            if (discountAmount != 0){
                discountResults.put(DiscountType.WEEKDAY, discountAmount);
            }
        }
    }

    private void applyWeekEndDiscount(Map<DiscountType, Integer> discountResults, List<OrderMenu> orderMenus,
                                      int visitDate) {
        if (Date.isWeekEnd(visitDate)) {
            int discountAmount = orderMenus.stream()
                    .filter(orderMenu -> MenuType.MAIN_MENU.getType()
                            .equals(orderMenu.getMenu().getKind()))
                    .mapToInt(orderMenu -> DiscountAmount.WEEKEND.getAmount() * orderMenu.getQuantity())
                    .sum();

            if (discountAmount != 0) {
                discountResults.put(DiscountType.WEEKEND, discountAmount);
            }
        }
    }

    private void applySpecialDayDiscount(Map<DiscountType, Integer> discountResults, int visitDate) {
        if (Date.isSpecialDay(visitDate)) {
            discountResults.put(DiscountType.SPECIAL, DiscountAmount.SPECIAL.getAmount());
        }
    }

    private void applyDDayDiscount(Map<DiscountType, Integer> discountResults, int visitDate) {
        if (visitDate <= D_DAY_DISCOUNT_LAST_DAY) {
            int discountAmount = (visitDate - D_DAY_DISCOUNT_FIRST_DAY) * DiscountAmount.ONE_DAY_ADDITIONAL.getAmount()
                    + DiscountAmount.D_DAY_BASIC.getAmount();
            discountResults.put(DiscountType.D_DAY, discountAmount);
        }
    }
}
