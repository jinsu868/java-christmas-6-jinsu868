package christmas.constant;

public enum DiscountAmount {
    SPECIAL(1000),
    WEEKDAY(2023),
    WEEKEND(2023),
    D_DAY_BASIC(1000),
    ONE_DAY_ADDITIONAL(100),
    MIN(10000),
    MIN_GIVEAWAY(120000);

    private int amount;

    private DiscountAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
