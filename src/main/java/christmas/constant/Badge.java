package christmas.constant;

public enum Badge {
    SANTA("산타"),
    STAR("별"),
    TREE("트리"),
    NONE("없음");

    private String badge;

    private Badge(String badge) {
        this.badge = badge;
    }

    public String getBadge() {
        return badge;
    }
}
