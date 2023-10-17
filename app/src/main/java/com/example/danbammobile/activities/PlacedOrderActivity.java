package com.example.danbammobile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.danbammobile.R;
import com.example.danbammobile.models.CartModel;
import com.example.danbammobile.models.ProductModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class PlacedOrderActivity extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placed_order);

       auth =  FirebaseAuth.getInstance();
       db = FirebaseFirestore.getInstance();


    }
}