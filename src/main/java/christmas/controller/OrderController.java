package christmas.controller;

import christmas.domain.Order;
import christmas.domain.OrderMenu;
import christmas.domain.VisitDate;
import christmas.dto.OrderRequest;
import christmas.error.IllegalArgumentExceptionType;
import christmas.view.InputView;
import java.util.List;

public class OrderController {
    private final InputView inputView;

    public OrderController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        VisitDate visitDate = getVisitDate();
        getOrder(visitDate);
    }

    private Order getOrder(VisitDate visitDate) {
        while (true) {
            try {
                List<OrderRequest> orderRequests = inputView.readMenus();
                List<OrderMenu> orderMenus = convertOrderMenus(orderRequests);
                return new Order(visitDate, orderMenus);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private List<OrderMenu> convertOrderMenus(List<OrderRequest> orderRequests) {
        return orderRequests.stream()
                .map(orderRequest -> new OrderMenu(orderRequest.getMenuName(), orderRequest.getQuantity()))
                .toList();
    }

    private VisitDate getVisitDate() {
        while (true) {
            try {
                VisitDate visitDate = new VisitDate(inputView.readDate());
                return visitDate;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
