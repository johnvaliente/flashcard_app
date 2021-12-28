package com.example.flashcard_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity implements  View.OnClickListener{

    /**
     * need a method that iterates through the deck, counts how many cards are in each deck, and adds it on a arraylist.
     * Then arraylist will be passed
     * off to an adapter (customized adapter)
     *
     * note: need to create code for multple decks as well.
     */
    String userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findViewById(R.id.newDeck).setOnClickListener(this);

        //receiving userId bundle
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle !=null){
            userId = (String) bundle.getSerializable("userId");

        }


    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.newDeck){
            Bundle bundle = new Bundle();
            Intent intent = new Intent(Menu.this,NewDeck.class);

            bundle.putString("userId", userId);
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }
}
