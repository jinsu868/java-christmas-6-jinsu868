package christmas.domain;

import christmas.constant.Date;
import christmas.constant.DiscountType;
import christmas.constant.Menu;
import christmas.constant.MenuType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountManager {
    private Order order;


    public DiscountManager(Order order) {
        this.order = order;
    }

    public boolean judgeGiveaway() {
        if (order.calculateTotalOrderAmount() >= 120000) {
            return true;
        }
        return false;
    }

    public Map<DiscountType, Integer> getDiscountResults() {
        Map<DiscountType, Integer> discountResults = new HashMap<>();
        int visitDate = order.getVisitDate();
        List<OrderMenu> orderMenus = order.getOrderMenus();

        applyDDayDiscount(discountResults, visitDate);
        applySpecialDayDiscount(discountResults, visitDate);
        applyWeekEndDiscount(discountResults, orderMenus, visitDate);
        applyWeekDayDiscount(discountResults, orderMenus, visitDate);
        applyGiveaway(discountResults);

        return discountResults;
    }

    private void applyGiveaway(Map<DiscountType, Integer> discountResults) {
        if (order.calculateTotalOrderAmount() >= 120000) {
            discountResults.put(DiscountType.GIVEAWAY, Menu.CHAMPAGNE.getPrice());
        }
    }

    private void applyWeekDayDiscount(Map<DiscountType, Integer> discountResults, List<OrderMenu> orderMenus,
                                      int visitDate) {
        if (Date.isWeekDay(visitDate)) {
            int discountAmount = orderMenus.stream()
                    .filter(orderMenu -> MenuType.DESSERT.getType()
                            .equals(orderMenu.getMenu().getKind()))
                    .mapToInt(orderMenu -> 2023 * orderMenu.getQuantity())
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
                    .mapToInt(orderMenu -> 2023 * orderMenu.getQuantity())
                    .sum();

            if (discountAmount != 0) {
                discountResults.put(DiscountType.WEEKDAY, discountAmount);
            }
        }
    }

    private void applySpecialDayDiscount(Map<DiscountType, Integer> discountResults, int visitDate) {
        if (Date.isSpecialDay(visitDate)) {
            discountResults.put(DiscountType.SPECIAL, 1000);
        }
    }

    private void applyDDayDiscount(Map<DiscountType, Integer> discountResults, int visitDate) {
        if (visitDate <= 25) {
            int discountAmount = (visitDate - 1) * 100 + 1000;
            discountResults.put(DiscountType.D_DAY, discountAmount);
        }
    }
}
