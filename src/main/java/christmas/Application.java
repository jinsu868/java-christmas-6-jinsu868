package christmas;

import christmas.controller.OrderController;
import christmas.view.InputView;

public class Application {
    public static void main(String[] args) {
        OrderController orderController = new OrderController(new InputView());
        orderController.run();
    }
}
