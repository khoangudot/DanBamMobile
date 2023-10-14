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
import com.example.danbammobile.models.HotComboModel;

import java.util.List;

public class HomeHotComboAdapter extends RecyclerView.Adapter<HomeHotComboAdapter.ViewHolder> {

    private Context context;
    private List<HotComboModel> hotComboModelList;

    public HomeHotComboAdapter(Context context, List<HotComboModel> hotComboModelList) {
        this.context = context;
        this.hotComboModelList = hotComboModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_hot_combo_items, parent, false)) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.popularProductName.setText(hotComboModelList.get(position).getName());
        Glide.with(context).load(hotComboModelList.get(position).getImg_url()).into(holder.popularProductImage);

        holder.popularProductDescription.setText(hotComboModelList.get(position).getDescription());
        holder.popularProductRating.setText(hotComboModelList.get(position).getRating());
        holder.popularProductPrice.setText(hotComboModelList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return hotComboModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView popularProductImage;
        TextView popularProductName, popularProductDescription, popularProductRating, popularProductPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            popularProductName = itemView.findViewById(R.id.home_hot_combo_name);
            popularProductImage = itemView.findViewById(R.id.home_hot_combo_img);

            popularProductDescription = itemView.findViewById(R.id.home_hot_combo_description);
            popularProductRating = itemView.findViewById(R.id.home_hot_combo_rating);
            popularProductPrice = itemView.findViewById(R.id.home_hot_combo_price);

        }
    }
}
