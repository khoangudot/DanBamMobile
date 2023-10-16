package com.example.danbammobile.models;

public class CartModel {

    private String UserEmail;
    private int ProductId;
    private int Quantity;
    private int TotalPrice;



    public CartModel() {
    }

    public CartModel(String userEmail, int productId, int quantity, int totalPrice) {
        this.UserEmail = userEmail;
        this.ProductId = productId;
        this.Quantity = quantity;
        this.TotalPrice = totalPrice;
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        this.OrderDate = dateFormat.format(calendar.getTime());

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
