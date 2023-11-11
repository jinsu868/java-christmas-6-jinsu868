package christmas.dto;

public class OrderRequest {
    private String menuName;
    private int quantity;

    public OrderRequest(String menuName, int quantity) {
        this.menuName = menuName;
        this.quantity = quantity;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getQuantity() {
        return quantity;
    }
}
