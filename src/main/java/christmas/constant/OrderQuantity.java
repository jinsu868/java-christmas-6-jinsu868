package christmas.constant;

public enum OrderQuantity {
    MIN(1),
    MAX(20);

    private int quantity;

    private OrderQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
