package repository;

import exception.InvalidInputException;
import model.Order;
import service.OrderService;

public interface IOrderRepository {

  void addOrder(String productId, Integer count, String orderId) throws InvalidInputException;

  boolean markRelease(String orderId);

  Order getOrder(String orderId);

  void markCompleted(String orderId);
}
