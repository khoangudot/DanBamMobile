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
import com.example.danbammobile.models.HomeCategoryModel;

import java.util.List;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder> {

    Context context;
    List<HomeCategoryModel> homeCategoryModelList;

    public HomeCategoryAdapter(Context context, List<HomeCategoryModel> homeCategoryModelList) {
        this.context = context;
        this.homeCategoryModelList = homeCategoryModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_category,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(homeCategoryModelList.get(position).getCategoryImage()).into(holder.categoryImage);
        holder.categoryName.setText(homeCategoryModelList.get(position).getCategoryName());
    }

    @Override
    public int getItemCount() {
        return homeCategoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryImage;
        TextView categoryName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.home_category_img);
            categoryName = itemView.findViewById(R.id.home_category_name);

        }
    }
}
