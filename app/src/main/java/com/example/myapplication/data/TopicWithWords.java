package com.example.myapplication.data;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import java.util.List;

public class TopicWithWords {
    @Embedded private Topic topic;
    @Relation(
      parentColumn = "topicId",
      entityColumn = "topicId"
    )
    private List<word> words;

    public List<word> getWords() {
        return words;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public void setWords(List<word> words) {
        this.words = words;
    }
}
