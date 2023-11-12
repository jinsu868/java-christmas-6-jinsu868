package christmas.constant;

public enum DiscountType {
    D_DAY("디데이할인"),
    SPECIAL("특별할인"),
    WEEKEND("주말할인"),
    WEEKDAY("평일할인"),
    GIVEAWAY("증정할인");

    private String type;

    private DiscountType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
