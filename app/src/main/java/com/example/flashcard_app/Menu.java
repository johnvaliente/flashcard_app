package com.example.flashcard_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

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
        findViewById(R.id.imageView5).setOnClickListener(this);
        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.delete).setOnClickListener(this);
        findViewById(R.id.imageView6).setOnClickListener(this);


        //receiving userId bundle
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle !=null){
            userId = (String) bundle.getSerializable("userId");

        }


    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.add){
            Bundle bundle = new Bundle();
            Intent intent = new Intent(Menu.this,NewDeck.class);

            bundle.putString("userId", userId);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(v.getId() == R.id.imageView5){
            Bundle bundle = new Bundle();
            Intent intent = new Intent(Menu.this,ListDecks.class);

            bundle.putString("userId", userId);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(v.getId() == R.id.delete){
            Bundle bundle = new Bundle();
            Intent intent = new Intent(Menu.this,DeleteDeck.class);

            bundle.putString("userId", userId);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(v.getId() == R.id.imageView6){
            Bundle bundle = new Bundle();
            Intent intent = new Intent(Menu.this,NewDeck.class);

            bundle.putString("userId", userId);
            bundle.putString("title", "Add Card");
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }

}
