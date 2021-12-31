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
    long count = 0;
    String countString;

    ArrayList<Deck> deckArrayList = new ArrayList<>();
    ArrayList<Deck> nameList = new ArrayList<>();
    DatabaseReference reference;

    ListView listView;
    Deck deck;
    String cardNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findViewById(R.id.newDeck).setOnClickListener(this);
        findViewById(R.id.listDeck).setOnClickListener(this);
        findViewById(R.id.menu).setOnClickListener(this);

        //receiving userId bundle
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle !=null){
            userId = (String) bundle.getSerializable("userId");

        }



    }

    private void setListview(ArrayList<Deck> arrayList) {

        int countArrayList = 0;
        listView = findViewById(R.id.listView);
        DeckAdapter myAdapter = new DeckAdapter(this,R.layout.activity_deck,arrayList);

        listView.setAdapter(myAdapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                deck = (Deck) listView.getItemAtPosition(position);

                Bundle info = new Bundle();
                //putting edited friend in bundle
                info.putSerializable("list", deckArrayList);
                info.putSerializable("count", countArrayList);

                Intent save = new Intent(Menu.this, FrontOfCard.class);
                save.putExtras(info);
                startActivity(save);

            }

        });


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
        else if(v.getId() == R.id.listDeck){
            Bundle bundle = new Bundle();
            Intent intent = new Intent(Menu.this,ListDecks.class);

            bundle.putString("userId", userId);
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }

}
