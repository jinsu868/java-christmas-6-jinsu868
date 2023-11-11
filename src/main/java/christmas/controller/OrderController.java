package christmas.controller;

import christmas.domain.VisitDate;
import christmas.error.IllegalArgumentExceptionType;
import christmas.view.InputView;

public class OrderController {
    private final InputView inputView;

    public OrderController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        VisitDate visitDate = getVisitDate();
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
