package com.example.a24_18078281_nguyenhuuthang.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.a24_18078281_nguyenhuuthang.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User")
    List<User> findAll();

    @Insert
    void addUser(User...users);

    @Delete
    void deleteUser(User...users);

    @Update
    void updateUser(User...users);

    @Query("SELECT * FROM User WHERE Name like :name")
    List<User> searchByName(String name);
}
