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

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //https://firebase.google.com/docs/auth/android/email-link-auth?authuser=0
    //https://firebase.google.com/docs/auth/android/start
    TextView fName;
    TextView lName;
    TextView email;
    TextView password;
    Button bRegister;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnlogin).setOnClickListener(this);
        findViewById(R.id.btnRegister).setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btnlogin){
            startActivity(new Intent(MainActivity.this, Login.class));
        }
        else if(v.getId() == R.id.btnRegister){
            startActivity(new Intent(MainActivity.this, SignUp.class));
        }


    }
}