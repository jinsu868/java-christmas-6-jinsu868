package christmas.controller;

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
        List<OrderMenu> orderMenus = getOrderMenus();
    }

    private List<OrderMenu> getOrderMenus() {
        while (true) {
            try {
                List<OrderRequest> orderRequests = inputView.readMenus();
                return convertOrderMenus(orderRequests);
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
