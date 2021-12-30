package com.example.flashcard_app;

/**
 * this class will show the front and back of the card, need to use dynamic programming so
 * when a user clicks a button it will show the back of the card, or go back to the previous card
 *
 *
 */
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FrontBack extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Deck> deckAL = new ArrayList<>();
    Button next;
    Button back;


    Button frontBC;

    TextView tvFront;
    TextView tvBack;

    LinearLayout linearLayout;


    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front_card);


        linearLayout = findViewById(R.id.layout);

        back = findViewById(R.id.backbtn);
        next = findViewById(R.id.nextbtn);
        tvFront = findViewById(R.id.frontContent);

        back.setOnClickListener(this);
        next.setOnClickListener(this);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if (b != null) {

            deckAL = (ArrayList<Deck>) b.getSerializable("list");
            count = (int) b.getSerializable("count");

        }

        if(count < deckAL.size()){
            Deck d = deckAL.get(count);
            tvFront.setText(d.getFront());
        }else{
            Toast.makeText(FrontBack.this,"No more cards", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backbtn) {
            Toast.makeText(FrontBack.this, "inside if", Toast.LENGTH_SHORT).show();

            backOfCard();


        } else if (v.getId() == R.id.nextbtn) {
            viewNextCard();
        }

    }

    private void viewNextCard() {
        count = count + 1;

        Bundle bundle = new Bundle();
        bundle.putSerializable("list", deckAL);
        bundle.putSerializable("count", count);

        Intent intent = new Intent(FrontBack.this, FrontBack.class);

        intent.putExtras(bundle);
        startActivity(intent);

    }

    private void backOfCard() {
        View deckView = getLayoutInflater().inflate(R.layout.back_card, null, false);
        tvBack = (TextView) deckView.findViewById(R.id.backContent);
        frontBC = deckView.findViewById(R.id.frontbtnBC);

        //  i = +1;
        Toast.makeText(FrontBack.this, "back on click", Toast.LENGTH_SHORT).show();

        Deck d = deckAL.get(count);
        String test = d.getBack();
        //count = count+1;
        tvBack.setText(d.getBack());


        frontBC.setOnClickListener(view -> {


            Bundle bundle = new Bundle();
            bundle.putSerializable("list", deckAL);
            bundle.putSerializable("count", count);

            Intent intent = new Intent(FrontBack.this, FrontBack.class);

            intent.putExtras(bundle);
            startActivity(intent);

        });
        linearLayout.addView(deckView);
    }
}