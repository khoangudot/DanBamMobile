package com.example.danbammobile.ui.MyCart;

import static android.content.Context.MODE_PRIVATE;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.danbammobile.R;
import com.example.danbammobile.activities.PlacedOrderActivity;
import com.example.danbammobile.adapters.MyCartAdapter;
import com.example.danbammobile.models.CartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class MyCartFragment extends Fragment {
     Context context ;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    RecyclerView recyclerView;
    MyCartAdapter myCartAdapter;
    ArrayList<CartModel> cartModels;
    TextView totalAmount;
    Button buyNow;

    public MyCartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_cart,container,false);
        recyclerView = root.findViewById(R.id.myCart_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        cartModels =  new ArrayList<>();
        myCartAdapter =  new MyCartAdapter(getActivity(), cartModels);
        recyclerView.setAdapter(myCartAdapter);

        // Lấy email từ SharedPreferences
        context=getActivity();
        String userEmail = getUserEmailFromSharedPreferences();
        //Log.d("MyCartAdapter", "ItemCount: " + userEmail);
        db.collection("AddToCart")
                .whereEqualTo("userEmail",userEmail)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                String documentId = document.getId();

                                CartModel cartModel = document.toObject(CartModel.class);

                                cartModel.setDocumentId(documentId);

                                cartModels.add(cartModel);

                            }
                            myCartAdapter.notifyDataSetChanged();
                            //Log.d("MyCartAdapter", "ItemCount: " + orderDetails.size());
                        }

                        else {

                        }
                    }
                });

        //Nhan totalAmount tu MyCartAdapter
         totalAmount = root.findViewById(R.id.my_cart_totalAmount);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageReceiver,new IntentFilter("MyTotalAmount"));

        //Buy Now
        buyNow = root.findViewById(R.id.my_cart_btnBuyNow);
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PlacedOrderActivity.class);
                intent.putExtra("itemList", cartModels);
                startActivity(intent);
            }
        });

        return root;
    }

    private String getUserEmailFromSharedPreferences() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return sharedPreferences.getString("userEmail", ""); // Trả về email lưu trong SharedPreferences
    }
    public BroadcastReceiver mMessageReceiver =  new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int totalBill = intent.getIntExtra("totalAmount",0);
            totalAmount.setText("Total Bill: "+String.format("%,d", totalBill)+" VNĐ");
        }
    };
}