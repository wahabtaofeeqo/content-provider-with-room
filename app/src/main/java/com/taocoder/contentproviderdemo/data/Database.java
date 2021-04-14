package com.taocoder.contentproviderdemo.data;


import androidx.room.RoomDatabase;

import com.taocoder.contentproviderdemo.models.User;

@androidx.room.Database(entities = {User.class}, version = 1)
public abstract class Database extends RoomDatabase {

    public abstract UserDao userDao();

    public static final String DB_NAME = "providerdemo.db";
}