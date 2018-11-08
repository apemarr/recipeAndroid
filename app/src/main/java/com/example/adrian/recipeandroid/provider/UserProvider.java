package com.example.adrian.recipeandroid.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;

import com.example.adrian.recipeandroid.User.data.UserContract;
import com.example.adrian.recipeandroid.pojos.User;

public class UserProvider {
    public static void insert(ContentResolver resolver,User user){
        Uri uri=Uri.parse(UserContract.CONTENT_URI);

        ContentValues values=new ContentValues();
        values.put(UserContract.NAME,user.getName());
        values.put(UserContract.EMAIL,user.getEmail());

        resolver.insert(uri, values);
    }
}
