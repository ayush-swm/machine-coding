package schedular;

import service.OrderService;

import java.util.TimerTask;

public class ReleaseOrderScheduler extends TimerTask {

  private final OrderService orderService;
  private final String orderId;

  public ReleaseOrderScheduler(String orderId) {
    this.orderService = new OrderService();
    this.orderId = orderId;
  }

  @Override
  public void run() {
    orderService.releaseOrder(orderId);
  }
}
