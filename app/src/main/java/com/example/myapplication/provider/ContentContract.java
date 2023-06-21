package com.example.myapplication.provider;

import android.content.ContentResolver;
import android.content.UriMatcher;
import android.net.Uri;

public class ContentContract {

    public final static String AUTHORITY = "";

    public final static String CONTENT_PATH = "tbl_user";

    public final static Uri uri = Uri.parse("Content://" + AUTHORITY + "/" + CONTENT_PATH);

    public final static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    public final static int ONE_USER = 1;
    public final static int ALL_USER = 2;

    public final static String CONTENT_TYPE_ONE_USER = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/mt_user";
    public final static String CONTENT_TYPE_ALL_USER = ContentResolver.CURSOR_DIR_BASE_TYPE + "/mt_user";

    static {
        uriMatcher.addURI(AUTHORITY, CONTENT_PATH, ALL_USER);
        uriMatcher.addURI(AUTHORITY, CONTENT_PATH + "/*", ONE_USER);
    }

}
