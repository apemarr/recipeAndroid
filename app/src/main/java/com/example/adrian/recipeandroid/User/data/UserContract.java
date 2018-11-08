package com.example.adrian.recipeandroid.User.data;

import android.provider.BaseColumns;

import com.example.adrian.recipeandroid.RecipeApplication;

public class UserContract implements BaseColumns {

    public static String TABLE_NAME="users";
    public static String CONTENT_URI="content://"+RecipeApplication.AUTHORITY+"/"+TABLE_NAME;
    public static String NAME="name";
    public static String EMAIL="email";

}
