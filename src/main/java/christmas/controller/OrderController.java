package christmas.controller;

import christmas.domain.Badge;
import christmas.domain.DiscountManager;
import christmas.domain.Order;
import christmas.domain.OrderMenu;
import christmas.domain.VisitDate;
import christmas.dto.OrderRequest;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class OrderController {
    private final InputView inputView;
    private final OutputView outputView;

    public OrderController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printWelcomeMessage();
        VisitDate visitDate = getVisitDate();
        Order order = getOrder(visitDate);
        DiscountManager discountManager = new DiscountManager(order);
        Badge badge = Badge.from(discountManager.calculateDiscountAmount());

        showDiscountDetails(discountManager, badge);
    }

    private VisitDate getVisitDate() {
        while (true) {
            try {
                VisitDate visitDate = new VisitDate(inputView.readDate());
                outputView.printBenefitPreviewMessage(visitDate.getDate());
                return visitDate;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private Order getOrder(VisitDate visitDate) {
        while (true) {
            try {
                List<OrderRequest> orderRequests = inputView.readMenus();
                List<OrderMenu> orderMenus = convertOrderMenus(orderRequests);
                Order order = new Order(visitDate, orderMenus);
                outputView.printOrderMenus(order);
                outputView.printBeforeDiscountOrderAmount(order.calculateTotalOrderAmount());
                return new Order(visitDate, orderMenus);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private List<OrderMenu> convertOrderMenus(List<OrderRequest> orderRequests) {
        return orderRequests.stream()
                .map(orderRequest -> new OrderMenu(orderRequest.getMenuName(), orderRequest.getQuantity()))
                .toList();
    }

    private void showDiscountDetails(DiscountManager discountManager, Badge badge) {
        outputView.printGiveaway(discountManager.judgeGiveaway());
        outputView.printBenefitResults(discountManager.getDiscountResults());
        outputView.printTotalDiscountAmount(discountManager.calculateDiscountAmount());
        outputView.printAfterDiscountOrderAmount(discountManager.getAfterDiscountOrderAmount());
        outputView.printEventBadge(badge);
    }
}
