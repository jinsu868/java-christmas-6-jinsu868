package christmas.constant;

public enum Message {
    WELCOME_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    BENEFIT_PREVIEW_MESSAGE("12월 %s일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU_PREFIX_MESSAGE("<주문 메뉴"),
    ORDER_MENU_MESSAGE("%s %d개"),
    NONE_MESSAGE("없음"),
    DISCOUNT_RESULTS_MESSAGE("%s: %,+d원"),
    GIVEAWAY_MESSAGE("샴페인 1개");

    private String message;

    private Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
