package com.example.danbammobile.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.danbammobile.R;
import com.example.danbammobile.adapters.HomeHorAdapter;
import com.example.danbammobile.models.HomeHorModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView homeHorizontalRec;
    List<HomeHorModel> homeHorModelList;
    HomeHorAdapter homeHorAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home,container,false);
//        homeHorizontalRec = root.findViewById(R.id.home_hor_rec);
//        homeHorModelList = new ArrayList<>();
//        homeHorModelList.add(new HomeHorModel((R.drawable.pizza),"Pizza"));
//        homeHorModelList.add(new HomeHorModel((R.drawable.hamburger),"Hamburger"));
//        homeHorModelList.add(new HomeHorModel((R.drawable.fried_potatoes),"Fries"));
//        homeHorModelList.add(new HomeHorModel((R.drawable.ice_cream),"Ice Cream"));
//        homeHorModelList.add(new HomeHorModel((R.drawable.sandwich),"Sandwich"));
//
//        homeHorAdapter = new HomeHorAdapter(getActivity(),homeHorModelList);
//        homeHorizontalRec.setAdapter(homeHorAdapter);
//
//        homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
//        homeHorizontalRec.setHasFixedSize(true);
//        homeHorizontalRec.setNestedScrollingEnabled(false);
        return root;
    }
}
