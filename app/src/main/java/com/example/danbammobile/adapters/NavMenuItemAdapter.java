package com.example.danbammobile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.danbammobile.R;
import com.example.danbammobile.models.ProductModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class NavMenuItemAdapter extends RecyclerView.Adapter<NavMenuItemAdapter.ViewHolder> {
    private BottomSheetDialog bottomSheetDialog;
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

        final String fProductName = productModels.get(position).getProductName();
        final String fProductDescription = productModels.get(position).getProductDescription();
        final String fProductImage = productModels.get(position).getProductImage();
        final float fProductRating = productModels.get(position).getProductRating();
        final int fProductPrice = productModels.get(position).getProductPrice();
        final int fProductDiscount = productModels.get(position).getProductDiscount();

        Glide.with(context).load(productModels.get(position).getProductImage()).into(holder.productImage);
        holder.productName.setText(productModels.get(position).getProductName());
        holder.productDescription.setText(productModels.get(position).getProductDescription());
        holder.productRating.setText(String.format("%.1f", productModels.get(position).getProductRating()));
        holder.productPrice.setText("Price "+ String.format("%,d", productModels.get(position).getProductPrice()));
        holder.productDiscount.setText("Discount "+ productModels.get(position).getProductDiscount()+ "%");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bottomSheetDialog = new BottomSheetDialog(context,R.style.BottomSheetTheme);
                View sheetView = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_layout,null);
                sheetView.findViewById(R.id.bottom_sheet_addToCart_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context,"Add to Cart", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    }
                });
                ImageView bottomProductImg = sheetView.findViewById(R.id.bottom_sheet_img);
                TextView bottomProductName = sheetView.findViewById(R.id.bottom_sheet_product_name);
                TextView bottomProductDescription = sheetView.findViewById(R.id.bottom_sheet_product_description);
                TextView bottomProductRating = sheetView.findViewById(R.id.bottom_sheet_product_rating);
                TextView bottomProductPrice = sheetView.findViewById(R.id.bottom_sheet_product_price);
                TextView bottomProductDiscount = sheetView.findViewById(R.id.bottom_sheet_discount);

                Glide.with(context).load(fProductImage).into(bottomProductImg);
                bottomProductName.setText(fProductName);
                bottomProductDescription.setText(fProductDescription);
                bottomProductRating.setText(String.format("%.1f", fProductRating));
                bottomProductPrice.setText("Price "+ String.format("%,d", fProductPrice));
                bottomProductDiscount.setText("Discount "+ fProductDiscount  +"%");
                bottomSheetDialog.setContentView(sheetView);
                bottomSheetDialog.show();
            }
        });
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
