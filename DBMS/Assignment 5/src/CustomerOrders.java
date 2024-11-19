public class CustomerOrders {
    private String OrderId;
    private String orderDate;
    private int totalAmount;
    private String userId;
    private String orderStatus;

    public CustomerOrders(String orderId, String orderDate, int totalAmount, String userId, String orderStatus) {
        this.OrderId = orderId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
        this.userId = userId;
    }

    public String getOrderId() {
        return OrderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public String getUserId() {
        return userId;
    }

}
