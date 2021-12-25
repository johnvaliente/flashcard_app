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

public class NewDeck extends AppCompatActivity implements  View.OnClickListener{

    LinearLayout linearLayout;
    Button buttonAdd;
    EditText etDeckName;


    String userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newdeck);
        //findViewById(R.id.newDeck).setOnClickListener(this);

        etDeckName = findViewById(R.id.deckName);

        //for dynamic changes
        linearLayout = findViewById(R.id.layout);
        buttonAdd = findViewById(R.id.more);

        buttonAdd.setOnClickListener(this);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle !=null){
            userId = (String) bundle.getSerializable("userId");

        }

    }

    @Override
    public void onClick(View v) {

        addview();

    }

    private void addview() {

        View deckView = getLayoutInflater().inflate(R.layout.add_front_back,null,false);

        EditText etFront = (EditText) deckView.findViewById(R.id.front);
        EditText etBack = (EditText) deckView.findViewById(R.id.back);


        linearLayout.addView(deckView);


    }
}
