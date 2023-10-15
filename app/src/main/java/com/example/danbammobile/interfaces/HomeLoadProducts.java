package com.example.danbammobile.interfaces;

import com.example.danbammobile.models.ProductModel;

import java.util.ArrayList;

public interface HomeLoadProducts {
    public void CallBack(int categoryId, ArrayList<ProductModel> productModels);
}
