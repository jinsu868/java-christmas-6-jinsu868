package christmas.view;

import christmas.constant.DiscountType;
import christmas.constant.Badge;
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
        order.getOrderMenus().forEach(orderMenu ->
                System.out.println(String.format(Message.ORDER_MENU_MESSAGE.getMessage(),
                        orderMenu.getMenu().getName(), orderMenu.getQuantity())));
    }

    public void printBenefitResults(Map<DiscountType, Integer> discountResults) {
        System.out.println(Message.BENEFIT_PREFIX_MESSAGE.getMessage());
        if (discountResults.size() == 0) {
            System.out.println(Message.NONE_MESSAGE.getMessage());
            return;
        }

        discountResults.forEach((discountType, discountAmount) ->
                System.out.println(String.format(Message.DISCOUNT_RESULTS_MESSAGE.getMessage(),
                        discountType.getType(), discountAmount)));
    }

    public void printGiveaway(boolean isGiveaway) {
        System.out.println(Message.GIVEAWAY_PREFIX_MESSAGE.getMessage());
        if (isGiveaway) {
            System.out.println(Message.GIVEAWAY_MESSAGE.getMessage());
            return;
        }
        System.out.println(Message.NONE_MESSAGE.getMessage());
    }

    public void printBeforeDiscountOrderAmount(int amount) {
        System.out.println(String.format(Message.BEFORE_DISCOUNT_MESSAGE.getMessage(), amount));
    }

    public void printEventBadge(Badge badge) {
        System.out.println(String.format(Message.EVENT_BADGE_MESSAGE.getMessage(), badge.getBadge()));
    }

    public void printTotalDiscountAmount(int amount) {
        System.out.println(String.format(Message.TOTAL_DISCOUNT_AMOUNT_MESSAGE.getMessage(), amount));
    }

    public void printAfterDiscountOrderAmount(int amount) {
        System.out.println(String.format(Message.AFTER_DISCOUNT_ORDER_AMOUNT_MESSAGE.getMessage(), amount));
    }
}
