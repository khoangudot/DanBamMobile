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
import com.example.danbammobile.adapters.HomeHotComboAdapter;
import com.example.danbammobile.adapters.HomeCategoryProductsAdapter;
import com.example.danbammobile.interfaces.HomeLoadProducts;
import com.example.danbammobile.models.CategoryModel;
import com.example.danbammobile.models.HotComboModel;
import com.example.danbammobile.models.ProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HomeFragment extends Fragment implements HomeLoadProducts {

    ScrollView scrollView;
    ProgressBar progressBar;
    RecyclerView homeHotComboRecyclerView, homeCategoryRecyclerView,homeMenuRecyclerView;
    FirebaseFirestore db;

    // Home Hot combo
    List<HotComboModel> hotComboModelList;
    HomeHotComboAdapter homeHotComboAdapter;

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
        homeHotComboRecyclerView = root.findViewById(R.id.rec_home_hot_combo);
        homeCategoryRecyclerView = root.findViewById(R.id.rec_home_category);
        homeMenuRecyclerView = root.findViewById(R.id.rec_home_menu);
        scrollView = root.findViewById(R.id.home_scroll_view);
        progressBar = root.findViewById(R.id.home_progressbar);

        //Progressbar
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        // Home Hot Combo
        homeHotComboRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        hotComboModelList = new ArrayList<>();
        homeHotComboAdapter = new HomeHotComboAdapter(getActivity(), hotComboModelList);
        homeHotComboRecyclerView.setAdapter(homeHotComboAdapter);

        db.collection("PopularProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                HotComboModel hotComboModel = document.toObject(HotComboModel.class);
                                hotComboModelList.add(hotComboModel);
                                homeHotComboAdapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(getActivity(),"Err"+task.getException(),Toast.LENGTH_SHORT).show();
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
