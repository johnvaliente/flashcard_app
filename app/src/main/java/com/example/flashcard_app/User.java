package com.example.flashcard_app;

public class User {

    String fName;
    String lName;
    String email;
    String pass;

    public User(){}

    public User(String fName, String lName, String email,String pass){

        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.pass = pass;
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
