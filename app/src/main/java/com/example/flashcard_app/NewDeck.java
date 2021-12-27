package com.example.flashcard_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.google.firebase.database.FirebaseDatabase;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newdeck);
        //findViewById(R.id.newDeck).setOnClickListener(this);

        etDeckName = (EditText) findViewById(R.id.deckName);
        etFrontNew = (EditText)findViewById(R.id.frontNew);
        etBackNew = (EditText) findViewById(R.id.backNew);



        //for dynamic changes
        linearLayout = findViewById(R.id.layout);
        buttonAdd = findViewById(R.id.more);
        buttonSubmit = findViewById(R.id.submit1);

        buttonSubmit.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle !=null){
            userId = (String) bundle.getSerializable("userId");

        }

    }

    @Override
    public void onClick(View v) {

        deckNameString = etDeckName.getText().toString();
        frontNewString = etFrontNew.getText().toString();
        backNewString = etBackNew.getText().toString();

        if(v.getId() == R.id.more){
            addview();
        }
        else if(v.getId() == R.id.submit1){

            HashMap<String, Object> map = new HashMap<>();
            map.put("deck_name" , deckNameString);
            map.put("back" , backNewString);
            map.put("front" , frontNewString);
            map.put("id" , userId);

            FirebaseDatabase.getInstance().getReference().child("decks").child(userId).setValue(map);

        }


    }

    private void addview() {

        View deckView = getLayoutInflater().inflate(R.layout.add_front_back,null,false);

        EditText etFront = (EditText) deckView.findViewById(R.id.front);
        EditText etBack = (EditText) deckView.findViewById(R.id.back);


        linearLayout.addView(deckView);


    }
}
