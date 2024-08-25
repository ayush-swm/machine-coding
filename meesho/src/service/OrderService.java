package service;

import exception.InvalidInputException;
import exception.ProductOOSException;
import model.Order;
import model.OrderStatus;
import repository.IOrderRepository;
import repository.IProductRepository;
import repository.impl.OrderInventoryImpl;
import repository.impl.ProductRepositoryImpl;
import schedular.TaskScheduler;

public class OrderService {

  private final IOrderRepository orderRepository;
  private final IProductRepository productRepository;
  private final TaskScheduler taskScheduler;

  public OrderService() {
    orderRepository = new OrderInventoryImpl();
    productRepository = new ProductRepositoryImpl();
    taskScheduler = new TaskScheduler();
  }

  public void blockInventory(String productId, Integer count, String orderId)
      throws InvalidInputException {
    if (productRepository.getProduct(productId) == null) {
      System.out.println("Product doesn't exists");
      return;
    }
    if (orderRepository.getOrder(orderId) != null) {
      System.out.println("Order already exists");
      return;
    }
    try {
      boolean isBlockingSuccess = productRepository.blockProduct(productId, count);
      if (!isBlockingSuccess) {
        throw new ProductOOSException("Not enough inventory");
      }
      orderRepository.addOrder(productId, count, orderId);
      taskScheduler.scheduleReleaseOrderTask(orderId);
    } catch (ProductOOSException e) {
      System.out.println(e.getMessage());
    }
  }

  public boolean confirmOrder(String orderId) {
    Order order = orderRepository.getOrder(orderId);
    if (order == null) {
      System.out.println("Order doesn't exists");
      return false;
    }
    if (order.getOrderStatus().equals(OrderStatus.DISCARDED)
        || order.getOrderStatus().equals(OrderStatus.PURCHASED)) {
      System.out.println("Order already completed");
      return false;
    }
    try {
      boolean isPurchaseSuccess =
          productRepository.purchaseProduct(order.getProductId(), order.getQuantity());
      if (isPurchaseSuccess) {
        orderRepository.markCompleted(orderId);
        return true;
      }
    } catch (ProductOOSException e) {
      System.out.println(e.getMessage());
      return false;
    }
    return false;
  }

  public void releaseOrder(String orderId) {
    Order order = orderRepository.getOrder(orderId);
    if (order == null) {
      System.out.println("Order doesn't exists");
      return;
    }
    if (order.getOrderStatus().equals(OrderStatus.PURCHASED)) {
      System.out.println("order already Purchase");
      return;
    }
    if (order.getOrderStatus().equals(OrderStatus.DISCARDED)) {
      System.out.println("order already Discarded");
      return;
    }
    try {
      boolean isReleaseSuccess = orderRepository.markRelease(orderId);
      if (isReleaseSuccess) {
        productRepository.freeProduct(order.getProductId(), order.getQuantity());
      }
    } catch (ProductOOSException e) {
      System.out.println(e.getMessage());
    }
  }
}
