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

import java.util.concurrent.ThreadLocalRandom;

//info on structuring db on firebase
//https://firebase.google.com/docs/database/admin/structure-data

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
        String userId = randomId();

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
                        reference = rootNode.getReference("users");

                        User user = new User(userFName,userLName,userEmail,userPass,userId);

                        reference.child(userId).setValue(user);

                        startActivity(new Intent(SignUp.this, Login.class));
                    }else{
                        Toast.makeText(SignUp.this, "User was not registered successfully" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
    }

    /**
     * Using a thread local random to generate a random 5 user id
     * thread local random is beneficial for multi threaded environment
     * @return
     */
    public String randomId(){
        int randomId = ThreadLocalRandom.current().nextInt(11111, 99999 +1);

        String parseInt = Integer.toString(randomId);

        return parseInt;
    }
}