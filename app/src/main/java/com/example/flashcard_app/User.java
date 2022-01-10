package com.example.flashcard_app;

public class User {

    String fName;
    String lName;
    String email;
    String pass;
    String id;

    public User(){}

    public User(String fName, String lName, String email,String pass, String id){

        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.pass = pass;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
