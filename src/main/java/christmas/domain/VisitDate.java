package christmas.domain;

import christmas.constant.Date;
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
        if (date < Date.START.getDay() || date > Date.END.getDay()) {
            throw IllegalArgumentExceptionType.INVALID_VISIT_DATE.getException();
        }
    }
}
