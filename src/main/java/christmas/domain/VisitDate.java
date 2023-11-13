package christmas.domain;

import christmas.error.IllegalArgumentExceptionType;

public class VisitDate {
    private final static int FIRST_DAY = 1;
    private final static int LAST_DAY = 31;

    private int date;

    public VisitDate(int date) {
        rangeValidate(date);
        this.date = date;
    }

    public int getDate() {
        return date;
    }

    private void rangeValidate(int date) {
        if (date < FIRST_DAY || date > LAST_DAY) {
            throw IllegalArgumentExceptionType.INVALID_VISIT_DATE.getException();
        }
    }
}
