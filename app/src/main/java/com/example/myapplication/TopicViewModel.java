package com.example.myapplication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.Topic;
import com.example.myapplication.data.TopicRepository;
import com.example.myapplication.data.TopicWithWords;
import com.example.myapplication.data.word;

import java.util.List;

public class TopicViewModel extends AndroidViewModel {
    private TopicRepository topicRepository;
    private LiveData<List<TopicWithWords>> allTopic;

    public TopicViewModel(@NonNull Application application) {
        super(application);
        topicRepository = new TopicRepository(application);
        allTopic = topicRepository.getAllTopic();
    }

    public void insertAllTopic(Topic...topics){
        topicRepository.insertAllTopic(topics);
    }

    public void updateTopic(Topic topic){
        topicRepository.updateTopic(topic);
    }

    public void deleteTopic(Topic topic){
        topicRepository.deleteTopic(topic);
    }

    public void deleteAllTopic(){
        topicRepository.deleteAllTopic();
    }

    public LiveData<List<TopicWithWords>> getTopicById(int id){
        return topicRepository.getTopicById(id);
    }
    public LiveData<List<TopicWithWords>> getAllTopic(){
        return topicRepository.getAllTopic();
    }

    public void insertAllWord(word...words){
        topicRepository.insertAllWord(words);
    }

}
