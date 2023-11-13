package christmas.constant;

import java.util.Arrays;
import java.util.List;

public enum Date {
    WEEKEND("주말", Arrays.asList(1, 2, 8, 9, 15, 16, 22, 23, 29, 30)),
    WEEKDAY("평일", Arrays.asList(3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31)),
    SPECIAL("특별", Arrays.asList(3, 10, 17, 24, 25, 31));

    private String type;
    private List<Integer> days;

    private Date(String type, List<Integer> days) {
        this.type = type;
        this.days = days;
    }

    public static boolean isSpecialDay(int day) {
        return Arrays.stream(values())
                .anyMatch(d -> d.getType().equals(Date.SPECIAL.getType()));
    }

    public static boolean isWeekDay(int day) {
        return Arrays.stream(values())
                .anyMatch(d -> d.getType().equals(Date.WEEKDAY.getType()));
    }

    public static boolean isWeekEnd(int day) {
        return Arrays.stream(values())
                .anyMatch(d -> d.getType().equals(Date.WEEKEND.getType()));
    }

    public String getType() {
        return type;
    }

    public List<Integer> getDay() {
        return days;
    }
}
