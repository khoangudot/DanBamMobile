package com.example.danbammobile.models;

public class HomeVerModel {
    private int image;
    private String productName;
    private String rating;
    private String price;

    public HomeVerModel() {
    }

    public HomeVerModel(int image, String productName, String rating, String price) {
        this.image = image;
        this.productName = productName;
        this.rating = rating;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
