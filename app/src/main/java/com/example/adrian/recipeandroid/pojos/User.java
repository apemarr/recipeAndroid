package com.example.adrian.recipeandroid.pojos;

import com.example.adrian.recipeandroid.constants.G;

public class User {
    int ID;
    String name;
    String email;

    public User(){
        this.ID= G.SIN_VALOR;
        this.name=G.SIN_VALOR_STRING;
        this.email=G.SIN_VALOR_STRING;
    }

    public User(int ID,String name, String email) {
        this.ID=ID;
        this.name = name;
        this.email = email;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
