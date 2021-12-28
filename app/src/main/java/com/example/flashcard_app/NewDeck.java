package com.example.flashcard_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class NewDeck extends AppCompatActivity implements  View.OnClickListener{

    LinearLayout linearLayout;
    Button buttonAdd;
    Button buttonSubmit;
    EditText etDeckName;


    EditText etFrontNew;
    EditText etBackNew;

    String deckNameString;
    String frontNewString;
    String backNewString;

    String userId;
    String count;


    DatabaseReference reference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newdeck);
        //findViewById(R.id.newDeck).setOnClickListener(this);

        etDeckName = (EditText) findViewById(R.id.deckName);
//        etFrontNew = (EditText)findViewById(R.id.frontNew);
//        etBackNew = (EditText) findViewById(R.id.backNew);


        //for dynamic changes
        linearLayout = findViewById(R.id.layout);
        buttonAdd = findViewById(R.id.more);

        buttonAdd.setOnClickListener(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle!=null){
            userId = (String) bundle.getSerializable("userId");
        }

    }

    @Override
    public void onClick(View v) {

        deckNameString = etDeckName.getText().toString();
//        frontNewString = etFrontNew.getText().toString();
//        backNewString = etBackNew.getText().toString();

        if(v.getId() == R.id.more){
            addview();
        }
//        else if(v.getId() == R.id.submit1){
//
//            HashMap<String, Object> map = new HashMap<>();
//            map.put("deck_name" , deckNameString);
//            map.put("back" , backNewString);
//            map.put("front" , frontNewString);
//            map.put("id" , userId);
//
//            FirebaseDatabase.getInstance().getReference().child("decks").child(userId).setValue(map);
//        }

    }

    private void addview() {


        deckNameString = etDeckName.getText().toString();

        View deckView = getLayoutInflater().inflate(R.layout.add_front_back,null,false);
        buttonSubmit = deckView.findViewById(R.id.submit1);


        EditText etFront = (EditText) deckView.findViewById(R.id.front);
        EditText etBack = (EditText) deckView.findViewById(R.id.back);



        buttonSubmit.setOnClickListener(view-> {

            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("decks").child(userId);
            rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    int cardNum;
                    if(snapshot.hasChild(deckNameString)){
                        count = (String) snapshot.child(deckNameString).child("count").getValue();
                        cardNum = Integer.valueOf(count) + 1;
                        count = String.valueOf(cardNum);

                        String etFrontStr = etFront.getText().toString();
                        String etBackStr = etBack.getText().toString();

                        HashMap<String, Object> mapUpDeck = new HashMap<>();
                        mapUpDeck.put(deckNameString, new Deck(etFrontStr,etBackStr,count));

                       // FirebaseDatabase.getInstance().getReference().child("decks").child(userId).setValue(mapUpDeck);

                        //rootRef.setValue(mapUpDeck);
                        rootRef.child(deckNameString).push().setValue(mapUpDeck);

                        //resources:
                        //https://firebase.google.com/docs/firestore/manage-data/add-data#update_elements_in_an_array
                        //https://stackoverflow.com/questions/50068510/how-to-append-data-in-firebase
                        //https://firebase.google.com/docs/database/admin/save-data
                        //https://stackoverflow.com/questions/37397205/google-firebase-check-if-child-exists

                    }
                    else{
                        //assume that it is a new card and you make one
                        //convert from et to string
                        String etFrontStr = etFront.getText().toString();
                        String etBackStr = etBack.getText().toString();
                        count = "0";

                        HashMap<String, Object> mapNewCard = new HashMap<>();
                        mapNewCard.put(deckNameString, new Deck(etFrontStr,etBackStr,count));

                        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("decks").child(userId).child(deckNameString);
                        String key = dbRef.push().getKey();

                        Deck newDeck = new Deck(etFrontStr,etBackStr,deckNameString);

                       // FirebaseDatabase.getInstance().getReference().child("decks").child(userId).child(deckNameString).push()..setValue(mapNewCard);

                        //creates an attribute under the specific key
                        dbRef.child(key).setValue(newDeck);
//                        dbRef.child(key).child("back").setValue(etBackStr);
//                        dbRef.child(key).child("count").setValue(count);


                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        });

        linearLayout.addView(deckView);

    }
}
