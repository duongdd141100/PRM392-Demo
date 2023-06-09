package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.entity.User;

import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "demo";

    private final static String USER_TABLE_NAME = "user";

//    private final static String USERNAME = "duongdd";
//    private final static String PASSWORD = "1414";

    private final static String CREATE_USER_TABLE_QUERY = "CREATE TABLE " + USER_TABLE_NAME +
            " (username TEXT NOT NULL PRIMARY KEY," +
            " password TEXT NOT NULL," +
            " role TEXT NOT NULL," +
            " campus TEXT NOT NULL)";

    private final static int VERSION = 1;

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (db.isOpen()) {
            db.execSQL(CREATE_USER_TABLE_QUERY);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void findUserByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username = ?";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] params = new String[] {username};
        Cursor cursor = sqLiteDatabase.rawQuery(sql, params);
        int index = cursor.getColumnIndex("username");
        String usernamee = cursor.getColumnName(index);
    }

    public void insert(String username, String password, String role, String campus) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("role", role);
        contentValues.put("campus", campus);
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert(USER_TABLE_NAME, null, contentValues);
    }
}
