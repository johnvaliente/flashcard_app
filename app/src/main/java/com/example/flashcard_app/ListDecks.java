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
import java.util.Collections;

public class ListDecks extends AppCompatActivity implements  View.OnClickListener{

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
    ArrayList<Deck> chosenDeck = new ArrayList<>();
    DatabaseReference reference;

    ListView listView;
    Deck deck;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_decks);
        //findViewById(R.id.newDeck).setOnClickListener(this);
        findViewById(R.id.menu).setOnClickListener(this);

        //receiving userId bundle
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle !=null){
            userId = (String) bundle.getSerializable("userId");

        }

        reference = FirebaseDatabase.getInstance().getReference().child("decks").child(userId);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds : snapshot.getChildren()){
                    if(ds.hasChildren()){

                        count = ds.getChildrenCount();
                        countString = Long.toString(count);
                        String key = ds.getKey();

                        DatabaseReference dbRef = reference.child(key);

                        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                for(DataSnapshot dSnap : snapshot.getChildren()){

                                    String keyIn = ds.getKey();

                                    String deckName = (String) dSnap.child("deckName").getValue();
                                    String fCard = (String) dSnap.child("front").getValue();
                                    String bCard = (String) dSnap.child("back").getValue();

                                    Deck d = new Deck(fCard,bCard,key);
                                    // Deck d = dSnap.getValue(Deck.class);

                                    deckArrayList.add(d);


                                    //each unique deck will be added on to an arraylist
                                    //if the deck name inside nameList is not inside, add it on to nameList

                                    for(Deck n : deckArrayList){

                                        if(nameList.isEmpty()){
                                            nameList.add(d);
                                        }
                                        for(Deck n2 : nameList){

                                            //if the deckName inside nameList is not the same as
                                            //the deckName of the deck in deckArrayList, then add it
                                            //to nameList
                                            if (!n.getDeckName().equals(n2.getDeckName())){
                                                nameList.add(d);
                                            }
                                        }

                                        n.setCount(countString);

                                    }
                                    setListview(nameList);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                    }
                    else{
                        //no decks
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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

                String t = deck.getDeckName();

                //goes through arraylist and picks the cards based on the particular
                //arraylist
                for(Deck d: deckArrayList){

                    if(d.getDeckName().equals(deck.getDeckName())){
                        chosenDeck.add(d);
                    }
                }

                Bundle info = new Bundle();
                //putting edited friend in bundle
                Collections.shuffle(deckArrayList);
                info.putSerializable("list", chosenDeck);
                info.putSerializable("count", countArrayList);

                Intent save = new Intent(ListDecks.this, FrontOfCard.class);
                save.putExtras(info);
                startActivity(save);

            }

        });

    }

    @Override
    public void onClick(View v) {

//        if(v.getId() == R.id.newDeck){
//            Bundle bundle = new Bundle();
//            Intent intent = new Intent(ListDecks.this,NewDeck.class);
//
//            bundle.putString("userId", userId);
//            intent.putExtras(bundle);
//            startActivity(intent);
//        }
//        else
            if(v.getId() == R.id.menu){
            Bundle bundle = new Bundle();
            Intent intent = new Intent(ListDecks.this,Menu.class);

            bundle.putString("userId", userId);
            intent.putExtras(bundle);
            startActivity(intent);

        }

    }

}
