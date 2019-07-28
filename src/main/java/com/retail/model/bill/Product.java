package com.retail.model.bill;

public class Product {

    String productId;
    String productName;
    double productPrice;
    ProductType productType;

    public void setProductId(String prodId) {
        this.productId = prodId;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String prodName) {
        this.productName = prodName;
    }

    public void setProductPrice(double prodPrice) {
        this.productPrice = prodPrice;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public ProductType getProductType() {
        return productType;
    }
}
