package com.taocoder.contentproviderdemo.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.ForeignKey;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.taocoder.contentproviderdemo.models.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User where id = :id")
    public User getById(int id);

    @Query("SELECT * FROM User")
    public List<User> getUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long addUser(User user);

    @Delete
    public void deleteUser(User user);
}