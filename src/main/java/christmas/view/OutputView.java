package christmas.view;

import christmas.constant.DiscountType;
import christmas.constant.Grade;
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
        System.out.println(Message.ORDER_MENU_PREFIX_MESSAGE);
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
        System.out.println(Message.GIVEWAY_PREFIX_MESSAGE.getMessage());
        if (isGiveaway) {
            System.out.println(Message.GIVEAWAY_MESSAGE.getMessage());
        } else {
            System.out.println(Message.NONE_MESSAGE.getMessage());
        }
    }

    public void printBeforeDiscount(int amount) {
        System.out.println(String.format(Message.BEFORE_DISCOUNT_MESSAGE.getMessage(), amount));
    }

    public void printEventBadge(Grade grade) {
        System.out.println(String.format(Message.EVENT_BADGE_MESSAGE.getMessage(), grade));
    }

    public void printTotalDiscountAmount(int amount) {
        System.out.println(String.format(Message.TOTAL_DISCOUNT_AMOUNT_MESSAGE.getMessage(), amount));
    }

    public void printAfterDiscountOrderAmount(int amount) {
        System.out.println(String.format(Message.AFTER_DISCOUNT_ORDER_AMOUNT_MESSAGE.getMessage(), amount));
    }
}
