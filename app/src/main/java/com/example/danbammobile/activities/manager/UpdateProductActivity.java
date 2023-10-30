package com.example.danbammobile.activities.manager;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.danbammobile.R;
import com.example.danbammobile.models.ProductModel;

public class UpdateProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);
        ProductModel productModel = (ProductModel) getIntent().getSerializableExtra("product");
        Toast.makeText(this, productModel.getProductName(),Toast.LENGTH_SHORT).show();
    }
}