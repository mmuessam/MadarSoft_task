package com.muslim.taskmadarsoft.Java.data;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface InfooDao {

    @Insert
    void insert(Infoo infoo);

    @Update
    void update(Infoo infoo);

    @Delete
    void delete(Infoo infoo);

    @Query("DELETE FROM Infoo")
    void deleteAllNotes();

    @Query("SELECT * FROM Infoo ORDER BY age DESC")
    LiveData <List<Infoo>> getAllInfoo();
}