package com.example.danbammobile.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.danbammobile.R;
import com.example.danbammobile.adapters.HomeCategoryAdapter;
import com.example.danbammobile.adapters.PopularProductAdapter;
import com.example.danbammobile.models.HomeCategoryModel;
import com.example.danbammobile.models.PopularProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView popularProductRecyclerView, homeCategoryRecyclerView;
    FirebaseFirestore db;

    // Popular Products
    List<PopularProductModel> popularProductModelList;
    PopularProductAdapter popularProductAdapter;

    //Home Category
    List<HomeCategoryModel> homeCategoryModelList;
    HomeCategoryAdapter homeCategoryAdapter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home,container,false);

        db = FirebaseFirestore.getInstance();
        popularProductRecyclerView = root.findViewById(R.id.rec_popular_products);
        homeCategoryRecyclerView = root.findViewById(R.id.rec_home_category);


        // Popular items
        popularProductRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        popularProductModelList = new ArrayList<>();
        popularProductAdapter = new PopularProductAdapter(getActivity(),popularProductModelList);
        popularProductRecyclerView.setAdapter(popularProductAdapter);




        db.collection("PopularProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PopularProductModel popularProductModel = document.toObject(PopularProductModel.class);
                                popularProductModelList.add(popularProductModel);
                                popularProductAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(),"Err"+task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        // Home category
        homeCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        homeCategoryModelList = new ArrayList<>();
        homeCategoryAdapter = new HomeCategoryAdapter(getActivity(),homeCategoryModelList);
        homeCategoryRecyclerView.setAdapter(homeCategoryAdapter);




        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                HomeCategoryModel homeCategoryModel = document.toObject(HomeCategoryModel.class);
                                homeCategoryModelList.add(homeCategoryModel);
                                homeCategoryAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(),"Err"+task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return root;
    }
}
