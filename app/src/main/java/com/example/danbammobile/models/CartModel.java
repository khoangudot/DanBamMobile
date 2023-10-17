package com.example.danbammobile.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CartModel implements Serializable {

    private String UserEmail;
    private int ProductId;
    private int Quantity;
    private int TotalPrice;
     private String DocumentId;

     String OrderDate;
     private int ProductDiscount;

    public CartModel() {
    }

    public CartModel(String userEmail, int productId, int quantity, int totalPrice, int productDiscount) {
        this.UserEmail = userEmail;
        this.ProductId = productId;
        this.Quantity = quantity;
        this.TotalPrice = totalPrice;
        this.ProductDiscount = productDiscount;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.OrderDate = dateFormat.format(calendar.getTime());
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public int getProductDiscount() {
        return ProductDiscount;
    }

    public void setProductDiscount(int productDiscount) {
        ProductDiscount = productDiscount;
    }

    public String getDocumentId() {
        return DocumentId;
    }

    public void setDocumentId(String documentId) {
        DocumentId = documentId;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        TotalPrice = totalPrice;
    }


}
