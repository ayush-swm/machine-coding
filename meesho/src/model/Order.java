package model;

public class Order {
  private String orderId;
  private String productId;
  private int quantity;
  private OrderStatus orderStatus;

  public Order(String orderId, String productId, int quantity, OrderStatus orderStatus) {
    this.orderId = orderId;
    this.productId = productId;
    this.quantity = quantity;
    this.orderStatus = orderStatus;
  }

  public String getOrderId() {
    return orderId;
  }

  public String getProductId() {
    return productId;
  }

  public int getQuantity() {
    return quantity;
  }

  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public void setOrderStatus(OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
  }
}
