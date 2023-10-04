package com.example.danbammobile.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danbammobile.R;
import com.example.danbammobile.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

public class RegisterActivity extends AppCompatActivity {
    Button register;
    EditText name,email, password;
    TextView login;

    FirebaseAuth auth;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        register =findViewById(R.id.btnRegister);
        name =findViewById(R.id.txtFullName);
        email =findViewById(R.id.txtEmail);
        password =findViewById(R.id.txtPassword);
        login =findViewById(R.id.tvLogin);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateUser();
            }
        });
    }

    private void CreateUser() {
        String userName = name.getText().toString();
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();

        if(TextUtils.isEmpty(userName)){
            Toast.makeText(this, "Name is Empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userEmail)){
            Toast.makeText(this, "Email is Empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userPassword)){
            Toast.makeText(this, "Password is Empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(userPassword.length() <6){
            Toast.makeText(this, "Password Length must be greater then 6 latter!", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    UserModel userModel = new UserModel(userName,userEmail,userPassword);
                    String id = task.getResult().getUser().getUid();
                    database.getReference().child("Users").child(id).setValue(userModel);

                    Toast.makeText(RegisterActivity.this, "Registration Successful",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Error: "+ task.getException(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}