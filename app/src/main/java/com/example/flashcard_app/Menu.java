package com.example.flashcard_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity implements  View.OnClickListener{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findViewById(R.id.newDeck).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.newDeck){
            Intent intent = new Intent(Menu.this, NewDeck.class);
            startActivity(intent);
        }

    }
}
