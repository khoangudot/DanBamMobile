package com.example.danbammobile.models;

public class PopularProductModel {
    private String name;
    private  String description;
    private String type;
    private String rating;
    private String price;
    private String img_url;

    public PopularProductModel() {
    }

    public PopularProductModel(String name, String description, String type, String rating, String price, String img_url) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.rating = rating;
        this.price = price;
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
