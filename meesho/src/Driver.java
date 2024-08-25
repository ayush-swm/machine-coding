import service.OrderService;
import service.ProductService;

public class Driver {
  public static void main(String[] args) throws InterruptedException {
    ProductService productService = new ProductService();
    OrderService orderService = new OrderService();

    productService.createProduct("P123", "Book", 10);
    productService.createProduct("P456", "Pen", 5);
    productService.createProduct("P789", "Copy", 8);

    System.out.println(productService.getInventory("P123"));
    System.out.println(productService.getInventory("P456"));
    System.out.println(productService.getInventory("P789"));
    System.out.println();

    orderService.blockInventory("P123", 1, "ORD123");
    orderService.blockInventory("P456", 2, "ORD456");
    orderService.blockInventory("P789", 3, "ORD789");


    System.out.println(productService.getInventory("P123"));
    System.out.println(productService.getInventory("P456"));
    System.out.println(productService.getInventory("P789"));
    System.out.println();

    //orderService.confirmOrder("ORD123");
    //orderService.confirmOrder("ORD456");
    //orderService.releaseOrder("ORD789");
    Thread.sleep(5000);

    System.out.println(productService.getInventory("P123"));
    System.out.println(productService.getInventory("P456"));
    System.out.println(productService.getInventory("P789"));
    System.out.println();

    orderService.confirmOrder("ORD123");
    orderService.confirmOrder("ORD4567");
    //orderService.releaseOrder("ORD789");
  }
}
