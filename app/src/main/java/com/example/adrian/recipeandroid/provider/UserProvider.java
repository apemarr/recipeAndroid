package com.example.adrian.recipeandroid.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

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
    public static void delete(ContentResolver resolver,int userid){
        Uri uri=Uri.parse(UserContract.CONTENT_URI+"/"+userid);
        resolver.delete(uri, null, null);
    }
    public static void update(ContentResolver resolver,User user){
        Uri uri=Uri.parse(UserContract.CONTENT_URI+"/"+user.getID());
        ContentValues values=new ContentValues();
        values.put(UserContract.NAME,user.getName());
        values.put(UserContract.EMAIL,user.getEmail());
        resolver.update(uri, values, null,null);
    }
    public static User readRecord(ContentResolver resolver,int userId){
        Uri uri=Uri.parse(UserContract.CONTENT_URI+"/"+userId);
        String[] projection={
            UserContract.NAME,
            UserContract.EMAIL
        };
        Log.i("userId readRecord",String.valueOf(userId));
        Cursor cursor=resolver.query(uri, projection, null, null,null);

        if(cursor.moveToFirst()){
            User user=new User();
            user.setID(userId);
            user.setName(cursor.getString(cursor.getColumnIndex(UserContract.NAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(UserContract.EMAIL)));
            Log.i("objeto user------->",user.toString());
            return user;
        }

        return null;
    }
}
