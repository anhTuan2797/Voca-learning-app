package com.example.myapplication.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "topic_table")
public class Topic {
    @PrimaryKey(autoGenerate = true)
    private int topicId;

    @ColumnInfo(name = "topic_name")
    private String topicName;

    @ColumnInfo(name = "topic_img_link")
    private String topicImgLink;

    @ColumnInfo(name = "topic_type")
    private String topicType;

    public Topic(String topicName, String topicImgLink, String topicType){
        this.topicName = topicName;
        this.topicImgLink = topicImgLink;
        this.topicType= topicType;
    }

    public int getTopicId() {
        return topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public String getTopicImgLink() {
        return topicImgLink;
    }

    public String getTopicType() {
        return topicType;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
