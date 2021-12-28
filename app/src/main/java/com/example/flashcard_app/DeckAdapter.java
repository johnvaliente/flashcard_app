package com.example.flashcard_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This is a customized adapter class and information was from
 * https://developer.android.com/reference/android/widget/ListView
 *
 * This adapter class
 */

public class DeckAdapter extends ArrayAdapter<Deck> {

    private Context mContext;
    int mResource;
    ArrayList<Deck> deck;


    /**
     * Constructor method
     *
     * @param context
     * @param
     * @param deck
     */

    public DeckAdapter(Context context, int resource,  ArrayList<Deck> deck) {
        super(context,0, deck);
        mContext = context;
        mResource = resource;
        this.deck = deck;

    }

    /**
     * overridden method that gets the View and displays it in a listview format
     *
     * this method reuses tool object to provide user with a smoother experience when scrolling.
     *
     *
     * @param position
     * @param convertView
     * @param parent
     * @return View that will be displayed
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        // gets the info about tools at the position its currently at
        String dName = getItem(position).getDeckName();
        String cardNum = getItem(position).getCount();


        //if this View is null create a new view,
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            //layout file you want to inflate
            //   convertView = inflater.inflate(R.layout.closest_bday, parent,false);
            convertView = inflater.inflate(mResource, parent,false);
        }

        //if convert view is already available reuse it and find using specified ID
        TextView deckName = (TextView) convertView.findViewById(R.id.deckTitle);
        TextView countDown = (TextView) convertView.findViewById(R.id.flashcards);


        //populates the textView with the information retrieved from the getters
        deckName.setText(dName);
        countDown.setText(cardNum + "cards in your deck");

        return convertView;
    }

}
