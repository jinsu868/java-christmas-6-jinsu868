package christmas.controller;

import christmas.constant.DiscountType;
import christmas.domain.Bill;
import christmas.domain.DiscountManager;
import christmas.domain.Order;
import christmas.domain.OrderMenu;
import christmas.domain.VisitDate;
import christmas.dto.OrderRequest;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;
import java.util.Map;

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
        outputView.printBenefitPreviewMessage(visitDate.getDate());
        outputView.printOrderMenus(order);

        DiscountManager discountManager = new DiscountManager(order);

        outputView.printBeforeDiscount(order.calculateTotalOrderAmount());
        outputView.printGiveaway(discountManager.judgeGiveaway());
        Map<DiscountType, Integer> discountResults = discountManager.getDiscountResults();
        Bill bill = new Bill(order, discountResults);

        outputView.printBenefitResults(discountResults);

        outputView.printTotalDiscountAmount(bill.calculateDiscountAmount());
        outputView.printAfterDiscountOrderAmount(bill.getAfterDiscountOrderAmount());
        outputView.printEventBadge(bill.getGrade());
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
