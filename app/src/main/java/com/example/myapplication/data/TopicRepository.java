package com.example.myapplication.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TopicRepository {
    private TopicDao topicDao;
    private wordDao wordDao;
    private LiveData<List<TopicWithWords>> allTopic;

    public TopicRepository(Application application){
        TopicDatabase topicDatabase = TopicDatabase.getInstance(application);
        topicDao = topicDatabase.topicDao();
        wordDao = topicDatabase.wordDao();
        allTopic = topicDao.getAll();
    }

    public LiveData<List<TopicWithWords>> getAllTopic(){
        return allTopic;
    }

    public LiveData<List<TopicWithWords>> getTopicById(int id){
        return  topicDao.getTopicById(id);
    }

    public void deleteAllTopic(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                topicDao.deleteAllTopic();
            }
        });
    }

    public void insertAllTopic(Topic...topics){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                topicDao.insertAll(topics);
            }
        });
    }

    public void updateTopic(Topic topic){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                topicDao.update(topic);
            }
        });
    }

    public void deleteTopic(Topic topic){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                topicDao.delete(topic);
            }
        });
    }

    public void insertAllWord(word...words){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                wordDao.insertAll(words);
            }
        });
    }

    public void update(word word){
        wordDao.update(word);
    }

    public void delete(word word){
        wordDao.delete(word);
    }

}
