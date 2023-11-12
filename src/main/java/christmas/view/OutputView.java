package christmas.view;

import christmas.constant.DiscountType;
import christmas.constant.Message;
import christmas.domain.Order;
import java.util.Map;

public class OutputView {
    public void printWelcomeMessage() {
        System.out.println(Message.WELCOME_MESSAGE.getMessage());
    }

    public void printBenefitPreviewMessage(int visitDate) {
        System.out.println(String.format(Message.BENEFIT_PREVIEW_MESSAGE.getMessage(), visitDate));
    }

    public void printOrderMenus(Order order) {
        System.out.println(Message.ORDER_MENU_MESSAGE.getMessage());
        order.getOrderMenus().forEach(orderMenu ->
                System.out.println(String.format(Message.ORDER_MENU_MESSAGE.getMessage(),
                        orderMenu.getMenu().getName(), orderMenu.getQuantity())));
    }

    public void printBenefitResults(Map<DiscountType, Integer> discountResults) {
        if (discountResults.size() == 0) {
            System.out.println(Message.NONE_MESSAGE.getMessage());
        }
        if (discountResults.size() != 0) {
            discountResults.forEach((discountType, discountAmount) ->
                    System.out.println(String.format(Message.DISCOUNT_RESULTS_MESSAGE.getMessage(),
                            discountType.getType(), discountAmount)));
        }
    }

    public void printGiveaway(boolean isGiveaway) {
        if (isGiveaway) {
            System.out.println(Message.GIVEAWAY_MESSAGE.getMessage());
        } else {
            System.out.println(Message.NONE_MESSAGE.getMessage());
        }
    }

}
