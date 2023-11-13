package christmas.constant;

public enum Message {
    WELCOME_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    VISIT_DAY_INPUT_MESSAGE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    ORDER_MENU_INPUT_MESSAGE("주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    BENEFIT_PREVIEW_MESSAGE("12월 %s일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n"),
    ORDER_MENU_PREFIX_MESSAGE("<주문 메뉴>"),
    ORDER_MENU_MESSAGE("%s %d개"),
    NONE_MESSAGE("없음\n"),
    DISCOUNT_RESULTS_MESSAGE("%s: %,d원"),
    BENEFIT_PREFIX_MESSAGE("<혜택 내역>"),
    GIVEAWAY_PREFIX_MESSAGE("<증정 메뉴>"),
    GIVEAWAY_MESSAGE("샴페인 1개\n"),
    BEFORE_DISCOUNT_MESSAGE("<할인 전 총주문 금액>\n%,d원\n"),
    EVENT_BADGE_MESSAGE("<12월 이벤트 배지>\n%s"),
    TOTAL_DISCOUNT_AMOUNT_MESSAGE("<총혜택 금액>\n%,d원\n"),
    AFTER_DISCOUNT_ORDER_AMOUNT_MESSAGE("<할인 후 예상 결제 금액>\n%,d원\n");

    private String message;

    private Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
