package christmas.domain;

import christmas.constant.OrderQuantity;
import christmas.error.IllegalArgumentExceptionType;
import java.util.Arrays;

public class OrderMenu {
    private Menu menu;
    private int quantity;

    public OrderMenu(String menuName, int quantity) {
        validateQuantity(quantity);
        this.quantity = quantity;
        this.menu = Menu.from(menuName);
    }

    public int calculateOrderAmount() {
        return menu.getPrice() * quantity;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }

    private void validateQuantity(int quantity) {
        if (quantity < OrderQuantity.MIN.getQuantity()) {
            throw IllegalArgumentExceptionType.INVALID_ORDER.getException();
        }
    }
}
