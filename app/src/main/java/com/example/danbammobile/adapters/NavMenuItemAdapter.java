package com.example.danbammobile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.danbammobile.R;
import com.example.danbammobile.models.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class NavMenuItemAdapter extends RecyclerView.Adapter<NavMenuItemAdapter.ViewHolder> {
    Context context;
    List<ProductModel> productModels;

    public NavMenuItemAdapter(Context context, List<ProductModel> productModels) {
        this.context = context;
        this.productModels = productModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_menu_products_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(productModels.get(position).getProductImage()).into(holder.productImage);
        holder.productName.setText(productModels.get(position).getProductName());
        holder.productDescription.setText(productModels.get(position).getProductDescription());
        holder.productRating.setText(String.format("%.1f", productModels.get(position).getProductRating()));
        holder.productPrice.setText("Price "+ String.format("%,d", productModels.get(position).getProductPrice()));
        holder.productDiscount.setText("Discount "+ productModels.get(position).getProductDiscount()+ "%");


    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productDescription, productPrice, productRating, productDiscount;
        Button addToCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.nav_menu_item_product_name);
            productImage = itemView.findViewById(R.id.nav_menu_item_product_img);
            productDescription = itemView.findViewById(R.id.nav_menu_item_product_description);
            productPrice = itemView.findViewById(R.id.nav_menu_item_product_price);
            productRating = itemView.findViewById(R.id.nav_menu_item_product_rating);
            productDiscount = itemView.findViewById(R.id.nav_menu_item_product_discount);
        }
    }
}
