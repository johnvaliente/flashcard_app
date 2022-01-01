package com.example.flashcard_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BackOfCard extends AppCompatActivity implements View.OnClickListener {

    TextView backHeader;
    TextView backContent;

    Button backToFront;

    ArrayList<Deck> deckAL = new ArrayList<>();
    int count = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.back_card);

        backContent = findViewById(R.id.backContent);
        backToFront = findViewById(R.id.frontbtnBC);
        backHeader = findViewById(R.id.bHeader);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if (b != null) {

            deckAL = (ArrayList<Deck>) b.getSerializable("list");
            count = (int) b.getSerializable("count");

        }

        Deck d = deckAL.get(count);
        backContent.setText(d.getBack());
        backHeader.setText("Back Card:");

        backToFront.setOnClickListener(view -> {

            Bundle bundle = new Bundle();
            bundle.putSerializable("list", deckAL);
            bundle.putSerializable("count", count);

            Intent in = new Intent(BackOfCard.this, FrontOfCard.class);

            in.putExtras(bundle);
            startActivity(in);

        });


    }

    @Override
    public void onClick(View v) {

    }


}
