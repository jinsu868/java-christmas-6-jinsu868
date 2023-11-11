package christmas.constant;

public enum Date {
    START(1),
    END(31);

    private int day;

    private Date(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }
}
