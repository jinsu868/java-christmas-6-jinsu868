package christmas.constant;

public enum MenuType {
    APPETIZER("에피타이저"),
    MAIN_MENU("메인메뉴"),
    DESSERT("디저트"),
    DRINK("음료");

    private String type;

    private MenuType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
