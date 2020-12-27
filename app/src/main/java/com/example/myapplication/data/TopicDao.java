package com.example.myapplication.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TopicDao {
    @Transaction
    @Query("SELECT * FROM topic_table")
    LiveData<List<TopicWithWords>> getAll();

    @Transaction
    @Query("SELECT * FROM topic_table WHERE topicId in (:topicIds) ")
    LiveData<List<TopicWithWords>> getTopicById(int topicIds);

    @Query("DELETE FROM topic_table")
    void deleteAllTopic();

    @Insert
    void insertAll(Topic...topics);

    @Update
    void update(Topic...topics);

    @Delete
    void delete(Topic topic);
}
