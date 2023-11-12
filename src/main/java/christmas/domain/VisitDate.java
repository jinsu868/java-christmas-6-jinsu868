package christmas.domain;

import christmas.error.IllegalArgumentExceptionType;

public class VisitDate {
    private int date;

    public VisitDate(int date) {
        rangeValidate(date);
        this.date = date;
    }

    public int getDate() {
        return date;
    }

    private void rangeValidate(int date) {
        if (date < 1 || date > 31) {
            throw IllegalArgumentExceptionType.INVALID_VISIT_DATE.getException();
        }
    }
}
