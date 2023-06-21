package com.example.myapplication.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.dao.DatabaseHandler;

public class UserContentProvider extends ContentProvider {

    private DatabaseHandler databaseHandler = null;

    @Override
    public boolean onCreate() {
        databaseHandler = new DatabaseHandler(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri,
                        @Nullable String[] projection,
                        @Nullable String selection,
                        @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
        Cursor cursor = null;
        switch (ContentContract.uriMatcher.match(uri)) {
            case ContentContract.ONE_USER:
                cursor = databaseHandler.getReadableDatabase().query("tbl_table",
                        null, selection, selectionArgs, null, null, null);
                break;
            case ContentContract.ALL_USER:
                cursor = databaseHandler.getReadableDatabase().query("tbl_table",
                        null, selection, selectionArgs, null, null, sortOrder);
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (ContentContract.uriMatcher.match(uri)) {
            case ContentContract.ONE_USER:
                return ContentContract.CONTENT_TYPE_ONE_USER;
            case ContentContract.ALL_USER:
                return ContentContract.CONTENT_TYPE_ALL_USER;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        databaseHandler.getWritableDatabase().insert("tbl_user", null, values);
        Uri uri1 = Uri.parse(ContentContract.CONTENT_PATH + "/*" + values.get("username"));
        return uri1;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
