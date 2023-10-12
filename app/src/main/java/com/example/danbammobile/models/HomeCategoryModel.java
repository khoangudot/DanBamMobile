package com.example.danbammobile.models;

public class HomeCategoryModel {
    private String CategoryName;
    private String CategoryImage;

    public HomeCategoryModel() {
    }

    public HomeCategoryModel(String categoryName, String categoryImage) {
        CategoryName = categoryName;
        CategoryImage = categoryImage;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getCategoryImage() {
        return CategoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        CategoryImage = categoryImage;
    }
}
