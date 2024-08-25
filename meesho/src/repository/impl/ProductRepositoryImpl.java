package repository.impl;

import exception.InvalidInputException;
import java.util.HashMap;
import java.util.Map;
import model.Product;
import repository.IProductRepository;

public class ProductRepositoryImpl implements IProductRepository {

  private static final Map<String, Product> productMap = new HashMap<>();

  @Override
  public synchronized void addProduct(String productId, String productName, Integer productCount)
      throws InvalidInputException {
    if (productMap.containsKey(productId)) {
      throw new InvalidInputException("Product already exists");
    }
    Product product = new Product(productId, productName, productCount, 0, 0);
    productMap.put(productId, product);
  }

  @Override
  public Integer getInventory(String productId) throws InvalidInputException {
    Product product = productMap.get(productId);
    if (product == null) {
      throw new InvalidInputException("Product doesn't exists");
    }
    return product.getProductTotalCount()
        - product.getProductBlockedCount()
        - product.getProductPurchasedCount();
  }

  @Override
  public Product getProduct(String productId) {
    return productMap.get(productId);
  }

  @Override
  public synchronized boolean blockProduct(String productId, Integer count) {
    Product product = productMap.get(productId);
    if (product == null) {
      throw new InvalidInputException("Product doesn't exists");
    }
    Integer availableCount =
        product.getProductTotalCount()
            - product.getProductBlockedCount()
            - product.getProductPurchasedCount();
    if (availableCount < count) {
      return false;
    }
    product.setProductBlockedCount(product.getProductBlockedCount() + count);
    return true;
  }

  @Override
  public synchronized boolean purchaseProduct(String productId, int quantity) {
    Product product = productMap.get(productId);
    if (product == null) {
      throw new InvalidInputException("Product doesn't exists");
    }
    Integer availableCount =
        product.getProductTotalCount()
            - product.getProductBlockedCount()
            - product.getProductPurchasedCount();
    if (availableCount < quantity) {
      return false;
    }
    product.setProductPurchasedCount(product.getProductPurchasedCount() + quantity);
    product.setProductBlockedCount(product.getProductBlockedCount() - quantity);
    return true;
  }

  @Override
  public synchronized void freeProduct(String productId, int quantity) {
    Product product = productMap.get(productId);
    if (product == null) {
      throw new InvalidInputException("Product doesn't exists");
    }
    product.setProductBlockedCount(product.getProductBlockedCount() - quantity);
  }
}
