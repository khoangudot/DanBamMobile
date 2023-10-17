package com.example.danbammobile.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.danbammobile.R;
import com.example.danbammobile.adapters.HomeCategoryAdapter;
import com.example.danbammobile.adapters.HomeHighestRatingAdapter;
import com.example.danbammobile.adapters.HomeCategoryProductsAdapter;
import com.example.danbammobile.interfaces.HomeLoadProducts;
import com.example.danbammobile.models.CategoryModel;
import com.example.danbammobile.models.ProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HomeFragment extends Fragment implements HomeLoadProducts {

    ScrollView scrollView;
    ProgressBar progressBar;
    RecyclerView homeHighestRatingRecyclerView, homeCategoryRecyclerView,homeMenuRecyclerView;
    FirebaseFirestore db;

    // Home Highest Rating
    ArrayList<ProductModel> homeHighestRatingList;
    HomeHighestRatingAdapter homeHighestRatingAdapter;

    //Home Category
    ArrayList<CategoryModel> categoryModelList;
    HomeCategoryAdapter homeCategoryAdapter;

    //Home Menu
    ArrayList<ProductModel> homeProducts;
    HomeCategoryProductsAdapter homeCategoryProductsAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home,container,false);

        db = FirebaseFirestore.getInstance();
        homeHighestRatingRecyclerView = root.findViewById(R.id.rec_home_highest_rating);
        homeCategoryRecyclerView = root.findViewById(R.id.rec_home_category);
        homeMenuRecyclerView = root.findViewById(R.id.rec_home_menu);
        scrollView = root.findViewById(R.id.home_scroll_view);
        progressBar = root.findViewById(R.id.home_progressbar);

        //Progressbar
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        // Highest Rating
        homeHighestRatingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        homeHighestRatingList = new ArrayList<>();
        homeHighestRatingAdapter = new HomeHighestRatingAdapter(getActivity(), homeHighestRatingList);
        homeHighestRatingRecyclerView.setAdapter(homeHighestRatingAdapter);

        db.collection("Product")
                .orderBy("ProductRating", Query.Direction.DESCENDING) // Sắp xếp theo rating giảm dần
                .limit(10) // Giới hạn kết quả trả về 10 sản phẩm
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            homeHighestRatingList.clear(); // Xóa danh sách cũ
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ProductModel productModel = document.toObject(ProductModel.class);
                                homeHighestRatingList.add(productModel);
                            }
                            homeHighestRatingAdapter.notifyDataSetChanged(); // Cập nhật RecyclerView
                            progressBar.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(getActivity(), "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        // Home category
        homeCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryModelList = new ArrayList<>();
        homeCategoryAdapter = new HomeCategoryAdapter(getContext(),categoryModelList,getActivity(),this);
        homeCategoryRecyclerView.setAdapter(homeCategoryAdapter);

        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CategoryModel categoryModel = document.toObject(CategoryModel.class);
                                categoryModelList.add(categoryModel);
                                homeCategoryAdapter.notifyDataSetChanged();
                            }
                            categoryModelList.sort(Comparator.comparing(CategoryModel::getCategoryId));
                            homeCategoryAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getActivity(),"Err"+task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

       


        return root;
    }

    @Override
    public void CallBack(int position, ArrayList<ProductModel> productModels) {
        homeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        homeCategoryProductsAdapter =  new HomeCategoryProductsAdapter(getContext(),productModels);
        homeCategoryProductsAdapter.notifyDataSetChanged();
        homeMenuRecyclerView.setAdapter(homeCategoryProductsAdapter);
    }
}
