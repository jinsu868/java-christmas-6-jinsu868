package christmas.domain;

import christmas.constant.Menu;
import christmas.error.IllegalArgumentExceptionType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Order {
    private VisitDate visitDate;
    private List<OrderMenu> orderMenus;

    public Order(VisitDate visitDate, List<OrderMenu> orderMenus) {
        validateMenuDuplication(orderMenus);
        this.visitDate = visitDate;
        this.orderMenus = orderMenus;
    }

    private void validateMenuDuplication(List<OrderMenu> orderMenus) {
        List<Menu> menus = orderMenus.stream()
                .map(m -> m.getMenu())
                .toList();
        Set<Menu> set = new HashSet<>(menus);
        if (set.size() != orderMenus.size()) {
            throw IllegalArgumentExceptionType.INVALID_ORDER.getException();
        }
    }
}
