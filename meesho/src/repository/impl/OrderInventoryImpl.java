package repository.impl;

import exception.InvalidInputException;
import java.util.HashMap;
import java.util.Map;
import model.Order;
import model.OrderStatus;
import repository.IOrderRepository;

public class OrderInventoryImpl implements IOrderRepository {

  private static final Map<String, Order> orderMap = new HashMap<>();

  @Override
  public synchronized void addOrder(String productId, Integer count, String orderId)
      throws InvalidInputException {
    if (orderMap.containsKey(orderId)) {
      throw new InvalidInputException("Order already exists!");
    }
    Order order = new Order(orderId, productId, count, OrderStatus.INITIATED);
    orderMap.put(orderId, order);
  }

  @Override
  public synchronized boolean markRelease(String orderId) {
    Order order = orderMap.get(orderId);
    if (order == null) {
      throw new InvalidInputException("Order doesn't exists!");
    }
    if (order.getOrderStatus().equals(OrderStatus.PURCHASED)) {
      return false;
    }
    if (order.getOrderStatus().equals(OrderStatus.DISCARDED)) {
      return false;
    }
    order.setOrderStatus(OrderStatus.DISCARDED);
    return true;
  }

  @Override
  public Order getOrder(String orderId) {
    return orderMap.get(orderId);
  }

  @Override
  public synchronized void markCompleted(String orderId) {
    Order order = orderMap.get(orderId);
    if (order == null) {
      throw new InvalidInputException("Order doesn't exists!");
    }
    order.setOrderStatus(OrderStatus.PURCHASED);
  }
}
