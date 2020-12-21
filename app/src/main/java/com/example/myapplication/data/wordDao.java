package com.example.myapplication.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface wordDao {
    @Query("SELECT * FROM word_table")
    LiveData<List<word>> getAll();

    @Query("DELETE FROM word_table")
    void deleteAllWord();

    @Insert
    void insertAll(word...words);

    @Update
    void update(word word);

    @Delete
    void delete(word word);
}
