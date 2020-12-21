package com.example.myapplication.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class word {
    @PrimaryKey(autoGenerate = true)
    private int wordId;

    @ColumnInfo(name = "word")
    private String word;

    @ColumnInfo(name = "word_mean")
    private String word_mean;

    @ColumnInfo(name = "word_hint")
    private String word_hint;

    @ColumnInfo(name = "priority")
    private int priority;

    @ColumnInfo(name = "topicId")
    private int topicId;

    public word(String word,String word_mean,String word_hint,int priority,int topicId){
        this.word = word;
        this.word_mean = word_mean;
        this.word_hint = word_hint;
        this.priority = priority;
        this.topicId = topicId;
    }

    public int getTopicId() {
        return topicId;
    }

    public int getWordId() {
        return wordId;
    }

    public String getWord() {
        return word;
    }

    public String getWord_mean() {
        return word_mean;
    }

    public String getWord_hint() {
        return word_hint;
    }

    public int getPriority() {
        return priority;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }
}
