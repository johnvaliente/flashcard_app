package com.example.flashcard_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity implements  View.OnClickListener{

    /**
     * This is the dashboard page where it can take users to desired page.
     *
     * https://designs.ai/colors/palette/1425
     */
    String userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findViewById(R.id.listDecks).setOnClickListener(this);
        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.delete).setOnClickListener(this);
        findViewById(R.id.imageView6).setOnClickListener(this);
        findViewById(R.id.user).setOnClickListener(this);

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
        else if(v.getId() == R.id.listDecks){
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
        else if(v.getId() == R.id.user){
            Bundle bundle = new Bundle();
            Intent intent = new Intent(Menu.this,Profile.class);

            bundle.putString("userId", userId);
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }

}
