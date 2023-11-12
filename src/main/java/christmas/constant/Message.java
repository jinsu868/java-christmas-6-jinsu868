package christmas.constant;

public enum Message {
    WELCOME_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    BENEFIT_PREVIEW_MESSAGE("12월 %s일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU_PREFIX_MESSAGE("<주문 메뉴>"),
    ORDER_MENU_MESSAGE("%s %d개"),
    NONE_MESSAGE("없음"),
    DISCOUNT_RESULTS_MESSAGE("%s: %,+d원"),
    BENEFIT_PREFIX_MESSAGE("<혜택 내역>"),
    GIVEAWAY_PREFIX_MESSAGE("<증정 메뉴>"),
    GIVEAWAY_MESSAGE("샴페인 1개"),
    BEFORE_DISCOUNT_MESSAGE("<할인 전 총주문 금액>\n %d원"),
    EVENT_BADGE_MESSAGE("<12월 이벤트 배지>\n%s"),
    TOTAL_DISCOUNT_AMOUNT_MESSAGE("<총혜택 금액>\n-%,d원"),
    AFTER_DISCOUNT_ORDER_AMOUNT_MESSAGE("<할인 후 예상 결제 금액>\n-%,d원");

    private String message;

    private Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
