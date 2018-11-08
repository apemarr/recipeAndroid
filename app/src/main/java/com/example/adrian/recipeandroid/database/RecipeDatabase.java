package com.example.adrian.recipeandroid.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.adrian.recipeandroid.User.data.UserContract;

public class RecipeDatabase extends SQLiteOpenHelper {
    private final static String DATABASE_NAME="recipe.db";
    private final static int DATABASE_VERSION=1;

    public RecipeDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+UserContract.TABLE_NAME+
                "(_id INTEGER PRIMARY KEY ON CONFLICT ROLLBACK AUTOINCREMENT,"+
                UserContract.NAME+ " TEXT, "+
                UserContract.EMAIL+ " TEXT);");
        InitializeData(db);
    }

    private void InitializeData(SQLiteDatabase db) {
        db.execSQL("INSERT INTO "+UserContract.TABLE_NAME +" ( "+
                UserContract.NAME+","+UserContract.EMAIL+" ) "+
        "VALUES ('Pepe','pepe@gmail.com'),('Juan','juan@gmail.com'),('Maria','maria@gmail.com')");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+UserContract.TABLE_NAME);
        onCreate(db);
    }
}
