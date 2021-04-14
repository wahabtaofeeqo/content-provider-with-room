package com.taocoder.contentproviderdemo;

import android.app.Application;

import androidx.room.Room;

import com.taocoder.contentproviderdemo.data.Database;

public class App extends Application {

    private Database database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(this, Database.class, Database.DB_NAME).fallbackToDestructiveMigration().build();
    }

    public Database getDatabase() {
        return database;
    }
}