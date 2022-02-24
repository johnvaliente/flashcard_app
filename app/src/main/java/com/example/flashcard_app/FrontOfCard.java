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

public class FrontOfCard extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Deck> deckAL = new ArrayList<>();
    Button next;
    Button back;
    Button prev;

    TextView tvFront;
    TextView tvHeader;

    LinearLayout linearLayout;
    int count = 0;

    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front_card);

        linearLayout = findViewById(R.id.layout);

        back = findViewById(R.id.backbtn);
        next = findViewById(R.id.nextbtn);
        prev = findViewById(R.id.prevBtn);
        tvFront = findViewById(R.id.frontContent);
        tvHeader = findViewById(R.id.frontHeader);

        findViewById(R.id.menu).setOnClickListener(this);
        findViewById(R.id.list).setOnClickListener(this);
        findViewById(R.id.person).setOnClickListener(this);

        back.setOnClickListener(this);
        next.setOnClickListener(this);
        prev.setOnClickListener(this);



        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if (b != null) {

            deckAL = (ArrayList<Deck>) b.getSerializable("list");
            //how many cards inside the deck
            count = (int) b.getSerializable("count");
            userId = (String) b.getSerializable("userId");

        }
        if(count < deckAL.size()){
            Deck d = deckAL.get(count);
            tvFront.setText(d.getFront());
            tvHeader.setText("Front Card");
        }else{
            Toast.makeText(FrontOfCard.this,"No more cards", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backbtn) {
            Toast.makeText(FrontOfCard.this, "inside if", Toast.LENGTH_SHORT).show();

            Bundle bundle = new Bundle();
            bundle.putSerializable("list", deckAL);
            bundle.putSerializable("count", count);

            Intent intent = new Intent(FrontOfCard.this, BackOfCard.class);

            intent.putExtras(bundle);
            startActivity(intent);

        } else if (v.getId() == R.id.nextbtn) {
            viewNextCard();
        }
        else if (v.getId() == R.id.prevBtn) {
            viewPrevCard();
        }
        else if(v.getId() == R.id.menu){
            Bundle bundle = new Bundle();
            Intent intent = new Intent(FrontOfCard.this,Menu.class);

            bundle.putString("userId", userId);
            intent.putExtras(bundle);
            startActivity(intent);

        }
        else if(v.getId() == R.id.list){
            Bundle bundle = new Bundle();
            Intent intent = new Intent(FrontOfCard.this,ListDecks.class);

            bundle.putString("userId", userId);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(v.getId() == R.id.person){

        }

    }

    private void viewPrevCard() {
        count = count - 1;

        if(count >= 0){
            Bundle bundle = new Bundle();
            bundle.putSerializable("list", deckAL);
            bundle.putSerializable("count", count);

            Intent intent = new Intent(FrontOfCard.this, FrontOfCard.class);

            intent.putExtras(bundle);
            startActivity(intent);

        }
        else{
            Toast.makeText(FrontOfCard.this,"This is the first card!!!", Toast.LENGTH_LONG).show();
        }


    }

    private void viewNextCard() {
        count = count + 1;

        Bundle bundle = new Bundle();
        bundle.putSerializable("list", deckAL);
        bundle.putSerializable("count", count);

        Intent intent = new Intent(FrontOfCard.this, FrontOfCard.class);

        intent.putExtras(bundle);
        startActivity(intent);

    }
}