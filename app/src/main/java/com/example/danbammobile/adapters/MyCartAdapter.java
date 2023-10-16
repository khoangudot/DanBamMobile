package com.example.danbammobile.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.danbammobile.R;
import com.example.danbammobile.models.CartModel;
import com.example.danbammobile.models.ProductModel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {
    Context context;
    ArrayList<CartModel> cartModels;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

     int totalAmount = 0;

    public MyCartAdapter(Context context, ArrayList<CartModel> cartModels) {
        this.context = context;
        this.cartModels = cartModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_item,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int productId = cartModels.get(position).getProductId();
        //Log.d("MyCartAdapter", "ItemCount: " + productId);
        // Truy vấn Firestore để lấy thông tin sản phẩm dựa trên productId
        db.collection("Product")
                .whereEqualTo("ProductId", productId)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {

                        ProductModel product = queryDocumentSnapshots.getDocuments().get(0).toObject(ProductModel.class);

                        if (product != null) {
                            holder.productName.setText(product.getProductName());
                            holder.productPrice.setText(String.format("%,d", product.getProductPrice()));

                        }
                    } else {
                        // Không tìm thấy sản phẩm với productId tương ứng
                    }
                })
                .addOnFailureListener(e -> {
                    // Xử lý lỗi khi truy vấn Firestore
                });

       holder.quantity.setText(String.valueOf(cartModels.get(position).getQuantity()));
       holder.totalPrice.setText(String.format("%,d", cartModels.get(position).getTotalPrice()));


        //pass totalAmount to My Cart Fragment
        totalAmount = totalAmount + cartModels.get(position).getTotalPrice();
        Intent intent = new Intent("MyTotalAmount");
        intent.putExtra("totalAmount",totalAmount);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    @Override
    public int getItemCount() {

        return cartModels.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName,productPrice, orderDate, quantity, totalPrice,paid;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.my_cart_item_productName);
            productPrice = itemView.findViewById(R.id.my_cart_item_productPrice);
            quantity = itemView.findViewById(R.id.my_cart_item_quantity);
            totalPrice = itemView.findViewById(R.id.my_cart_item_totalPrice);

        }
    }
}
