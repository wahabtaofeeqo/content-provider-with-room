package com.taocoder.contentproviderdemo.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PlanDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "providerdemo.db";
    private static final int VERSION = 1;

    public PlanDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query("User", null, null, null, null, null, null);
    }

    public Cursor getUser(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query("User", null, "id = ?", new String[]{id}, null, null, null);
    }

    public int deleteUser(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("User", "id = ?", new String[]{id});
    }

    public int updateUser(String id, ContentValues values) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.update("User", values, "id = ?", new String[] {id});
    }
}
