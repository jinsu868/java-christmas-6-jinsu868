package christmas.domain;

import christmas.error.ErrorCode;
import java.util.Arrays;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6_000, "에피타이저"),
    TAPAS("타파스", 5_500, "에피타이저"),
    CAESAR_SALAD("시저셀러드", 8_000, "에피타이저"),
    T_BONE_STEAK("티본스테이크", 55_000, "메인메뉴"),
    BBQ_RIBS("바비큐립", 54_000, "메인메뉴"),
    SEAFOOD_PASTA("해산물파스타", 35_000, "메인메뉴"),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, "메인메뉴"),
    CHOCOLATE_CAKE("초코케이크", 15_000, "디저트"),
    ICE_CREAM("아이스크림", 5_000, "디저트"),
    ZERO_COKE("제로콜라", 3_000, "음료"),
    RED_WINE("레드와인", 60_000, "음료"),
    CHAMPAGNE("샴페인", 25_000, "음료");

    private String name;
    private int price;
    private String kind;

    private Menu(String name, int price, String kind) {
        this.name = name;
        this.price = price;
        this.kind = kind;
    }

    public static Menu from(String name) {
        return Arrays.stream(values())
                .filter(menu -> menu.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> ErrorCode.INVALID_ORDER.getException());
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getKind() {
        return kind;
    }
}
