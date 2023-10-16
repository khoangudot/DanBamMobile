package com.example.danbammobile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.danbammobile.R;
import com.example.danbammobile.models.CartModel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class PlacedOrderActivity extends AppCompatActivity {

    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placed_order);

        db = FirebaseFirestore.getInstance();
        ArrayList<CartModel> cartModels = (ArrayList<CartModel>) getIntent().getSerializableExtra("itemList");


    }
}