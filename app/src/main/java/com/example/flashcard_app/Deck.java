package com.example.flashcard_app;

public class Deck {

    public String front;
    public String back;
    public String deckName;
    public String userId;

    public Deck() {

    }

    public Deck(String front, String back, String deckName, String userId) {
        this.front = front;
        this.back = back;
        this.deckName = deckName;
        this.userId = userId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
