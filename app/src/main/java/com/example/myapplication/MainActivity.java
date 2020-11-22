package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myapplication.TopicRecylerView.TopicRecylerViewAdapter.topicRecyclerViewAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//    private ArrayList<String> mTopicName = new ArrayList<>();
//    private ArrayList<String> mTopicThumb = new ArrayList<>();
//    private ArrayList<Boolean> mTopicDownloadStatus = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initImage();
//        initTopicRecyclerView();
    }

//    private void initImage(){
//        mTopicName.add("topic 1");
//        mTopicThumb.add("https://images.pexels.com/photos/5650027/pexels-photo-5650027.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
//        mTopicDownloadStatus.add(true);
//
//        mTopicName.add("topic 2");
//        mTopicThumb.add("https://images.pexels.com/photos/5650027/pexels-photo-5650027.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
//        mTopicDownloadStatus.add(false);
//
//        mTopicName.add("topic 3");
//        mTopicThumb.add("https://images.pexels.com/photos/5650027/pexels-photo-5650027.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
//        mTopicDownloadStatus.add(false);
//
//    }

//    private void initTopicRecyclerView(){
//        RecyclerView topicRecyclerView  = findViewById(R.id.topic_list_recycler_view);
//        topicRecyclerViewAdapter adapter = new topicRecyclerViewAdapter(mTopicName,mTopicThumb,mTopicDownloadStatus,this);
//        topicRecyclerView.setAdapter(adapter);
//        topicRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        Log.i(null,"recycler create");
//    }

}