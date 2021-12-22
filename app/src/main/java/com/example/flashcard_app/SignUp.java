package com.example.flashcard_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class SignUp extends AppCompatActivity{

    TextView fName;
    TextView lName;
    TextView email;
    TextView password;
    Button bRegister;

    FirebaseAuth mAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        fName = findViewById(R.id.fname);
        lName = findViewById(R.id.lname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.pwd);
        bRegister = findViewById(R.id.register);

        mAuth = FirebaseAuth.getInstance();


        bRegister.setOnClickListener(view -> {
            createUser();
        });
    }

    public void createUser(){

        String userFName = fName.getText().toString();
        String userLName = lName.getText().toString();
        String userEmail = email.getText().toString();
        String userPass = password.getText().toString();

        if(TextUtils.isEmpty(userEmail)){
            email.setError("Email cannot be empty");
            email.requestFocus();
        }else if(TextUtils.isEmpty(userPass)){
            password.setError("Password cannot be empty");
            password.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(userEmail,userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        Toast.makeText(SignUp.this, "User is registered successfully", Toast.LENGTH_LONG).show();

                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference();

                        User user = new User(userFName,userLName,userEmail,userPass);

                        reference.child(email).setValue(user);

                        startActivity(new Intent(SignUp.this, Login.class));
                    }else{
                        Toast.makeText(SignUp.this, "User was not registered successfully" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            });
        }



    }
}