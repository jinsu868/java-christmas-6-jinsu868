package christmas.domain;

import christmas.constant.MenuType;
import christmas.constant.OrderQuantity;
import christmas.error.ErrorCode;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Order {
    private VisitDate visitDate;
    private List<OrderMenu> orderMenus;

    public Order(VisitDate visitDate, List<OrderMenu> orderMenus) {
        validateMenuDuplication(orderMenus);
        validateOrderAmount(orderMenus);
        validateOnlyDrink(orderMenus);
        this.visitDate = visitDate;
        this.orderMenus = orderMenus;
    }

    public int calculateTotalOrderAmount() {
        return orderMenus.stream()
                .mapToInt(OrderMenu::calculateOrderAmount)
                .sum();
    }

    public int getVisitDate() {
        return visitDate.getDate();
    }

    public List<OrderMenu> getOrderMenus() {
        return Collections.unmodifiableList(orderMenus);
    }

    private void validateOnlyDrink(List<OrderMenu> orderMenus) {
        if (isOnlyDrink(orderMenus)) {
            throw ErrorCode.INVALID_ORDER.getException();
        }
    }

    private boolean isOnlyDrink(List<OrderMenu> orderMenus) {
        return orderMenus.stream()
                .allMatch(orderMenu -> orderMenu.getMenu().getKind().equals(MenuType.DRINK.getType()));
    }

    private void validateMenuDuplication(List<OrderMenu> orderMenus) {
        List<Menu> menus = orderMenus.stream()
                .map(m -> m.getMenu())
                .toList();
        Set<Menu> set = new HashSet<>(menus);
        if (set.size() != orderMenus.size()) {
            throw ErrorCode.INVALID_ORDER.getException();
        }
    }

    private void validateOrderAmount(List<OrderMenu> orderMenus) {
        int totalAmount = getTotalAmount(orderMenus);
        if (totalAmount > OrderQuantity.MAX.getQuantity()) {
            throw ErrorCode.INVALID_ORDER.getException();
        }
    }

    private int getTotalAmount(List<OrderMenu> orderMenus) {
        int totalAmount = orderMenus.stream()
                .mapToInt(m -> m.getQuantity())
                .sum();
        return totalAmount;
    }
}
