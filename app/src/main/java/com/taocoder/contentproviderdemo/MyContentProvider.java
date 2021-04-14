package com.taocoder.contentproviderdemo;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import com.taocoder.contentproviderdemo.data.Database;
import com.taocoder.contentproviderdemo.data.PlanDatabase;
import com.taocoder.contentproviderdemo.data.UserDao;
import com.taocoder.contentproviderdemo.models.User;

public class MyContentProvider extends ContentProvider {

    private static final String PROVIDER_NAME = "com.taocoder.contentproviderdemo.privider.MyContentProvider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/users");

    private static final int USERS = 1;
    private static final int USER = 2;
    private static final UriMatcher uriMatcher;

    private long insertId;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "users", USERS);
        uriMatcher.addURI(PROVIDER_NAME, "users/#", USER);
    }

    public MyContentProvider() {
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        return false;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        PlanDatabase database = new PlanDatabase(getContext());
       if (uriMatcher.match(uri) == USER) {
           return database.deleteUser(uri.getPathSegments().get(1));
       }

       return 0;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                final UserDao userDao = ((App) getContext()).getDatabase().userDao();
                User user = new User();
                user.setName(values.getAsString("name"));
                user.setEmail(values.getAsString("email"));
                user.setPhone(values.getAsString("phone"));
                insertId = userDao.addUser(user);
            }
        });

        return ContentUris.withAppendedId(CONTENT_URI, insertId);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        PlanDatabase database = new PlanDatabase(getContext());
        if (uriMatcher.match(uri) == USER) {
           return database.getUser(uri.getPathSegments().get(1));
        }

        return database.getUsers();
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        PlanDatabase database = new PlanDatabase(getContext());
        if (uriMatcher.match(uri) == USER)
            return database.updateUser(uri.getPathSegments().get(1), values);

        return 0;
    }
}