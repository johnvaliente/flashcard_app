package com.example.flashcard_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity implements  View.OnClickListener{
    public FirebaseAuth mAuth;

    EditText loginEmail;
    EditText loginPass;
    Button buttonLogin;

    //retrieving db
    DatabaseReference reference;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.createnewac).setOnClickListener(this);

        //Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        loginEmail = findViewById(R.id.etemail);
        loginPass = findViewById(R.id.etpass);
        buttonLogin = findViewById(R.id.btnlogin);

        buttonLogin.setOnClickListener(view -> {
            loginUser();
        });

    }

    private void loginUser(){

        String userEmail = loginEmail.getText().toString();
        String userPass = loginPass.getText().toString();

        if(TextUtils.isEmpty(userEmail)){
            loginEmail.setError("Email cannot be empty");
            loginEmail.requestFocus();
        }else if(TextUtils.isEmpty(userPass)){
            loginPass.setError("Password cannot be empty");
            loginPass.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(userEmail,userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Login.this, "User logged in successfully", Toast.LENGTH_LONG).show();

                        reference = FirebaseDatabase.getInstance().getReference().child("users");


                        reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot ds : snapshot.getChildren()){
                                    //refers to the table/reference "users" as an object
                                    //User user = ds.getValue(User.class);

                                    String uEmail = (String)ds.child("email").getValue();


                                    if(userEmail.equals(uEmail)){
                                        //getID and pass it into menu class
                                       // String userId = user.getId();
                                        String uId = (String)ds.child("id").getValue();

                                        Bundle bundle = new Bundle();
                                        Intent intent = new Intent(Login.this,Menu.class);

                                        bundle.putString("userId", uId);
                                        intent.putExtras(bundle);
                                        startActivity(intent);

                                    }
                                    else{
                                        //account not registered
                                    }


                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });



                    }else{
                        Toast.makeText(Login.this, "Wrong user information " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.createnewac){
            Intent intent = new Intent(Login.this, SignUp.class);
            startActivity(intent);
        }

    }
}
