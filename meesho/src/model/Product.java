package model;

public class Product {
  private String productId;
  private String productName;
  private Integer productTotalCount;
  private Integer productBlockedCount;
  private Integer productPurchasedCount;

  public Product(
      String productId,
      String productName,
      Integer productTotalCount,
      Integer productBlockedCount,
      Integer productPurchasedCount) {
    this.productId = productId;
    this.productName = productName;
    this.productTotalCount = productTotalCount;
    this.productBlockedCount = productBlockedCount;
    this.productPurchasedCount = productPurchasedCount;
  }

  public String getProductId() {
    return productId;
  }

  public String getProductName() {
    return productName;
  }

  public Integer getProductTotalCount() {
    return productTotalCount;
  }

  public Integer getProductBlockedCount() {
    return productBlockedCount;
  }

  public Integer getProductPurchasedCount() {
    return productPurchasedCount;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public void setProductTotalCount(Integer productTotalCount) {
    this.productTotalCount = productTotalCount;
  }

  public void setProductBlockedCount(Integer productBlockedCount) {
    this.productBlockedCount = productBlockedCount;
  }

  public void setProductPurchasedCount(Integer productPurchasedCount) {
    this.productPurchasedCount = productPurchasedCount;
  }
}
