package christmas.constant;

public enum Separator {
    COMMA(","),
    DASH("-");


    private String division;

    private Separator(String division) {
        this.division = division;
    }

    public String getDivision() {
        return division;
    }
}
