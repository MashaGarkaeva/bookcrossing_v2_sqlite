package com.bookcross;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "bookcrossing.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "bookcrossing.db", null, 6);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int i, int i1) {
        MyDatabase.execSQL("drop Table if exists User");
    }

    public Boolean insertData(String login, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Login", login);
        contentValues.put("Password", password);
        long result = MyDatabase.insert("User", null, contentValues);

        if(result == -1){
            return false;
        } else {
            return  true;
        }

    }
    public Boolean checkLogin(String login){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from User where login = ?", new String[]{login});

        if (cursor.getCount() > 0){
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkLoginPassword (String login, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from User where login = ? and password = ?", new String[]{login, password});

        if (cursor.getCount() > 0){
            return true;
        } else{
            return false;
        }
    }
}
