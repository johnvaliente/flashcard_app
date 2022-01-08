package com.example.flashcard_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
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

    //resources:
    //https://firebase.google.com/docs/firestore/manage-data/add-data#update_elements_in_an_array
    //https://stackoverflow.com/questions/50068510/how-to-append-data-in-firebase
    //https://firebase.google.com/docs/database/admin/save-data
    //https://stackoverflow.com/questions/37397205/google-firebase-check-if-child-exists

    LinearLayout linearLayout;
    Button buttonAdd;
    Button buttonSubmit;
    EditText etDeckName;

    String deckNameString;

    String userId;

    TextView tvHeader;
    String pageTitle = "Create New Deck";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newdeck);

        etDeckName = (EditText) findViewById(R.id.deckName);
        tvHeader = (TextView) findViewById(R.id.header);

        //for dynamic changes
        linearLayout = findViewById(R.id.layout);
        buttonAdd = findViewById(R.id.more);

        buttonAdd.setOnClickListener(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle!=null){
            userId = (String) bundle.getSerializable("userId");
           pageTitle = (String) bundle.getSerializable("title");
        }

        tvHeader.setText(pageTitle);

    }

    @Override
    public void onClick(View v) {

        deckNameString = etDeckName.getText().toString();

        if(v.getId() == R.id.more){
            addview();
        }

    }

    /**
     * This method will displayed when users want to add a front and back of a card.
     * It is connected to another xml page so that users can add as many cards they want.
     */
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

                    //if what is deck already exist
                    if(snapshot.hasChild(deckNameString)){

                        String etFrontStr = etFront.getText().toString();
                        String etBackStr = etBack.getText().toString();

                        //create a "new deck" with existing deck name
                        Deck updateDeck = new Deck(etFrontStr,etBackStr,deckNameString);
                        rootRef.child(deckNameString).push().setValue(updateDeck);

                    }
                    else{
                        //assume that it is a new card and you make one
                        //convert from et to string
                        String etFrontStr = etFront.getText().toString();
                        String etBackStr = etBack.getText().toString();


                        //reference to new deck name
                        DatabaseReference dbRef = rootRef.child(deckNameString);
                        String key = dbRef.push().getKey();

                        Deck newDeck = new Deck(etFrontStr,etBackStr,deckNameString);


                        //creates an attribute under the specific key
                        dbRef.child(key).setValue(newDeck);

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
