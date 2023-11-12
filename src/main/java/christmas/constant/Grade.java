package christmas.constant;

public enum Grade {
    SANTA("산타"),
    STAR("별"),
    TREE("트리"),
    NONE("없음");

    private String grade;

    private Grade(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }
}
