package com.example.flashcard_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeleteDeck extends AppCompatActivity implements  View.OnClickListener{

    Button d;
    EditText etDeleteDeck;

    String userId;
    String delDeck;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_deck);
        findViewById(R.id.deleteButton).setOnClickListener(this);

        etDeleteDeck = (EditText) findViewById(R.id.deckDelete);

        delDeck = etDeleteDeck.getText().toString();

        d = findViewById(R.id.deleteButton);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle!=null){
            userId = (String) bundle.getSerializable("userId");
        }


        d.setOnClickListener(view ->{

            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("decks").child(userId);
            rootRef.addListenerForSingleValueEvent(new ValueEventListener(){

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if(snapshot.hasChild(delDeck)){
                        rootRef.child(delDeck).removeValue();
                        Toast.makeText(DeleteDeck.this,"Successfully deleted!" , Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(DeleteDeck.this,"Deck not found!" , Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



        });


    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.deleteButton){




        }

    }


}
