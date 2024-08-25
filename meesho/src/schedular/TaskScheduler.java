package schedular;

import java.util.Timer;

public class TaskScheduler {

  private static final long ORDER_RELEASE_TIME = 500;

  public void scheduleReleaseOrderTask(String orderId) {
    Timer timer = new Timer();
    timer.schedule(new ReleaseOrderScheduler(orderId), ORDER_RELEASE_TIME);
  }
}
