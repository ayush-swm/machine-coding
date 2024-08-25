package service;

import exception.InvalidInputException;
import repository.IProductRepository;
import repository.impl.ProductRepositoryImpl;

public class ProductService {

  private final IProductRepository productRepository;

  public ProductService() {
    this.productRepository = new ProductRepositoryImpl();
  }

  public void createProduct(String productId, String productName, Integer productCount)
      throws InvalidInputException {
    if (productRepository.getProduct(productId) != null) {
      System.out.println("Product already exists");
      return;
    }
    productRepository.addProduct(productId, productName, productCount);
  }

  public Integer getInventory(String productId) {
    if (productRepository.getProduct(productId) == null) {
      System.out.println("Product doesn't exists");
      return 0;
    }
    try {
      return productRepository.getInventory(productId);
    } catch (InvalidInputException e) {
      System.out.println(e.getMessage());
      return 0;
    }
  }
}
