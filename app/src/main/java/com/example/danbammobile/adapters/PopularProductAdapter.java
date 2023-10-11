package com.example.danbammobile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.danbammobile.R;
import com.example.danbammobile.models.PopularProductModel;

import java.util.List;

public class PopularProductAdapter extends RecyclerView.Adapter<PopularProductAdapter.ViewHolder> {

    private Context context;
    private List<PopularProductModel> popularProductModelList;

    public PopularProductAdapter(Context context, List<PopularProductModel> popularProductModelList) {
        this.context = context;
        this.popularProductModelList = popularProductModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_products, parent, false)) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.popularProductName.setText(popularProductModelList.get(position).getName());
        Glide.with(context).load(popularProductModelList.get(position).getImg_url()).into(holder.popularProductImage);

        holder.popularProductDescription.setText(popularProductModelList.get(position).getDescription());
        holder.popularProductRating.setText(popularProductModelList.get(position).getRating());
        holder.popularProductDiscount.setText(popularProductModelList.get(position).getDiscount());
    }

    @Override
    public int getItemCount() {
        return popularProductModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView popularProductImage;
        TextView popularProductName, popularProductDescription, popularProductRating, popularProductDiscount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            popularProductName = itemView.findViewById(R.id.popular_product_name);
            popularProductImage = itemView.findViewById(R.id.popular_product_img);

            popularProductDescription = itemView.findViewById(R.id.popular_product_description);
            popularProductRating = itemView.findViewById(R.id.popular_product_rating);
            popularProductDiscount = itemView.findViewById(R.id.popular_product_discount);

        }
    }
}
