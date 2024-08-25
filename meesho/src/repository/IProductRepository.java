package repository;

import exception.InvalidInputException;
import model.Product;

public interface IProductRepository {
  void addProduct(String productId, String productName, Integer productCount)
      throws InvalidInputException;

  Integer getInventory(String productId) throws InvalidInputException;

  Product getProduct(String productId);

  boolean blockProduct(String productId, Integer count);

  boolean purchaseProduct(String productId, int quantity);

  void freeProduct(String productId, int quantity);
}
