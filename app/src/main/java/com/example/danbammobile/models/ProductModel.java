package com.example.danbammobile.models;

public class ProductModel {
    private int ProductId;
    private String ProductName;
    private String ProductDescription;
    private String ProductImage;
    private int ProductPrice;
    private int ProductDiscount;
    private float ProductRating;
    private int CategoryId;

    public ProductModel() {
    }

    public ProductModel(int productId, String productName, String productDescription, String productImage, int productPrice, int productDiscount, float productRating, int categoryId) {
        ProductId = productId;
        ProductName = productName;
        ProductDescription = productDescription;
        ProductImage = productImage;
        ProductPrice = productPrice;
        ProductDiscount = productDiscount;
        ProductRating = productRating;
        CategoryId = categoryId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String productDescription) {
        ProductDescription = productDescription;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    public int getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(int productPrice) {
        ProductPrice = productPrice;
    }

    public int getProductDiscount() {
        return ProductDiscount;
    }

    public void setProductDiscount(int productDiscount) {
        ProductDiscount = productDiscount;
    }

    public float getProductRating() {
        return ProductRating;
    }

    public void setProductRating(float productRating) {
        ProductRating = productRating;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }
}
