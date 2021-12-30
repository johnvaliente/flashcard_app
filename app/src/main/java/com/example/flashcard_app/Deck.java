package com.example.flashcard_app;

import java.io.Serializable;

public class Deck implements Serializable {

    public String front;
    public String back;
    public String deckName;
    public String count;

    public Deck() {
    }

    public Deck(String front, String back, String deckName) {
        this.front = front;
        this.back = back;
        this.deckName = deckName;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String userId) {
        this.count = userId;
    }
}
