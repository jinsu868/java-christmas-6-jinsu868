package christmas.domain;

import christmas.constant.Menu;
import christmas.error.IllegalArgumentExceptionType;
import java.util.Arrays;
import java.util.EnumSet;

public class OrderMenu {
    private Menu menu;
    private int quantity;

    public OrderMenu(String menuName, int quantity) {
        validateName(menuName);
        validateQuantity(quantity);
        this.quantity = quantity;
        this.menu = Menu.getMenuByName(menuName);
    }

    public int calculateOrderAmount() {
        return menu.getPrice() * quantity;
    }

    public Menu getMenu() {
        return menu;
    }

    private void validateName(String menuName) {
        if (!isExistMenu(menuName)) {
            throw IllegalArgumentExceptionType.INVALID_ORDER.getException();
        }
    }

    private boolean isExistMenu(String menuName) {
        return Arrays.stream(Menu.values())
                .anyMatch(menu -> menu.getName().equals(menuName));
    }

    private void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw IllegalArgumentExceptionType.INVALID_ORDER.getException();
        }
    }
}
