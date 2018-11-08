package com.example.adrian.recipeandroid.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.SparseArray;

import com.example.adrian.recipeandroid.RecipeApplication;
import com.example.adrian.recipeandroid.User.data.UserContract;
import com.example.adrian.recipeandroid.database.RecipeDatabase;

public class RecipeContentProvider extends ContentProvider {
    private final static int ONE_REG=1;
    private final static int ALL_REG=2;

    private static UriMatcher uriMatcher;
    private static SparseArray<String> mimeTypes;

    static {
        uriMatcher=new UriMatcher(0);
        mimeTypes = new SparseArray<>();

        uriMatcher.addURI(RecipeApplication.AUTHORITY,UserContract.TABLE_NAME,ALL_REG);
        uriMatcher.addURI(RecipeApplication.AUTHORITY,UserContract.TABLE_NAME+"/#",ONE_REG);

        mimeTypes.put(ALL_REG,"vnd.android.cursor.dir/vnd."+UserContract.CONTENT_URI+"."+UserContract.TABLE_NAME);
        mimeTypes.put(ONE_REG,"vnd.android.cursor.item/vnd."+UserContract.CONTENT_URI+"."+UserContract.TABLE_NAME);
    }
    RecipeDatabase dbHelper;

    public RecipeContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase sqlDB = dbHelper.getWritableDatabase();

        String table="";
        switch (uriMatcher.match(uri)){
            case ONE_REG:
                if(null==selection) selection="";
                selection += UserContract._ID + " = " + uri.getLastPathSegment();
                table=UserContract.TABLE_NAME;
                break;
            case ALL_REG:
                table=UserContract.TABLE_NAME;
                break;
        }
        int rows = sqlDB.delete(table,selection,selectionArgs);
        if (rows>0){
            getContext().getContentResolver().notifyChange(uri,null);
            return rows;
        }
        throw new SQLException("Failed to delete row into "+uri);
    }

    @Override
    public String getType(Uri uri) {
       return mimeTypes.get(uriMatcher.match(uri));
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase sqlDB = dbHelper.getWritableDatabase();

        String table="";
        switch (uriMatcher.match(uri)){
            case ALL_REG:
                table=UserContract.TABLE_NAME;
                break;
        }
        long rowId= sqlDB.insert(table,"",values);

        if(rowId>0){
            Uri rowUri=ContentUris.appendId(uri.buildUpon(),rowId).build();
            getContext().getContentResolver().notifyChange(rowUri,null);
            return rowUri;
        }
        throw new SQLException("Failed to insert row into "+uri);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new RecipeDatabase(getContext());
        return dbHelper==null?false:true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        switch (uriMatcher.match(uri)){
          case ALL_REG:
              qb.setTables(UserContract.TABLE_NAME);
              break;
          case ONE_REG:
              qb.setTables(UserContract.TABLE_NAME);
              break;
      }
      Cursor c=qb.query(db,projection,selection,selectionArgs,null,null,sortOrder);
        c.setNotificationUri(getContext().getContentResolver(),uri);
        return c;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase sqlDB = dbHelper.getWritableDatabase();

        String table="";
        switch (uriMatcher.match(uri)){
            case ONE_REG:
                if(null==selection) selection="";
                selection += UserContract._ID + " = " + uri.getLastPathSegment();
                table=UserContract.TABLE_NAME;
                break;
            case ALL_REG:
                table=UserContract.TABLE_NAME;
                break;
        }
        int rows=sqlDB.update(table,values,selection,selectionArgs);
        if (rows>0){
            getContext().getContentResolver().notifyChange(uri,null);
            return rows;
        }
        throw new SQLException("Failed to update row into "+uri);
    }
}
