package com.example.adrian.recipeandroid.pojos;

import android.graphics.Bitmap;

import com.example.adrian.recipeandroid.constants.G;

import java.sql.Blob;

public class User {
    int ID;
    String name;
    String email;
    Bitmap imagen;

    public User(){
        this.ID= G.SIN_VALOR;
        this.name=G.SIN_VALOR_STRING;
        this.email=G.SIN_VALOR_STRING;
        this.setImagen(null);
    }

    public User(int ID,String name, String email,Bitmap imagen) {
        this.ID=ID;
        this.name = name;
        this.email = email;
        this.imagen=imagen;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
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
