package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.TopicRecylerView.TopicRecylerViewAdapter.topicRecyclerViewAdapter;
import com.example.myapplication.data.Topic;
import com.example.myapplication.data.TopicDatabase;
import com.example.myapplication.data.TopicWithWords;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link main_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class main_fragment extends Fragment implements View.OnClickListener, recyclerViewClickInterface{
    NavController navController;
    RecyclerView recyclerView;
    private TopicViewModel topicViewModel;
    private ArrayList<Integer> mTopicId = new ArrayList<>();
    private ArrayList<String> mTopicName = new ArrayList<>();
    private ArrayList<String> mTopicThumb = new ArrayList<>();
    private ArrayList<Boolean> mTopicDownloadStatus = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        FloatingActionButton button = (FloatingActionButton)view.findViewById(R.id.add_card_button);
        button.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_fragment, container, false);
        recyclerView = view.findViewById(R.id.topic_list_recycler_view);
        topicRecyclerViewAdapter adapter = new topicRecyclerViewAdapter(mTopicName,mTopicThumb,mTopicDownloadStatus,this,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        topicViewModel = new ViewModelProvider(this).get(TopicViewModel.class);
        initImage(adapter);
        return view;
    }

//    for list item
    @Override
    public void onItemClick(int position,View view) {
        Log.i(null, mTopicName.get(position));
        Bundle result = new Bundle();
        result.putInt("topicId",mTopicId.get(position));
        getParentFragmentManager().setFragmentResult("topicId",result);
        Navigation.findNavController(view).navigate(R.id.action_main_fragment_to_topic_fragment2);
    }

    @Override
    public void onItemLongClick(int position, View view) {

    }

    //    For floating button
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.add_card_button){
            Topic topic = new Topic("new topic",
                    "https://images.pexels.com/photos/5650027/pexels-photo-5650027.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
                    "custom");
            topicViewModel.insertAllTopic(topic);
        }
    }

    private void initImage(topicRecyclerViewAdapter adapter){
        topicViewModel.getAllTopic().observe(getViewLifecycleOwner(), new Observer<List<TopicWithWords>>() {
            @Override
            public void onChanged(List<TopicWithWords> topicWithWords) {
                mTopicName.clear();
                mTopicThumb.clear();
                mTopicDownloadStatus.clear();
                for(int i =0;i<topicWithWords.size();i++){
                    mTopicName.add(topicWithWords.get(i).getTopic().getTopicName());
                    mTopicThumb.add(topicWithWords.get(i).getTopic().getTopicImgLink());
                    mTopicDownloadStatus.add(false);
                    mTopicId.add(topicWithWords.get(i).getTopic().getTopicId());
                }
                adapter.setData(mTopicName,mTopicThumb,mTopicDownloadStatus);
            }
//            @Override
//            public void onChanged(List<Topic> topics) {
//                mTopicName.clear();
//                mTopicThumb.clear();
//                mTopicDownloadStatus.clear();
//                Log.i( "","onChanged: size"+topics.size());
//                for(int i=0;i<topics.size();i++){
//                    mTopicName.add(topics.get(i).getTopicName());
//                    mTopicThumb.add(topics.get(i).getTopicImgLink());
//                    mTopicDownloadStatus.add(false);
//                }
//                adapter.setData(mTopicName,mTopicThumb,mTopicDownloadStatus);
//
//            }
        });
//        mTopicName.clear();
//        mTopicThumb.clear();
//        mTopicDownloadStatus.clear();
//
//        mTopicName.add("topic 2");
//        mTopicThumb.add("https://images.pexels.com/photos/5650027/pexels-photo-5650027.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
//        mTopicDownloadStatus.add(true);
//
//        mTopicName.add("topic 3");
//        mTopicThumb.add("https://images.pexels.com/photos/5650027/pexels-photo-5650027.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
//        mTopicDownloadStatus.add(false);
//
//        mTopicName.add("topic 1");
//        mTopicThumb.add("https://images.pexels.com/photos/5650027/pexels-photo-5650027.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
//        mTopicDownloadStatus.add(false);

    }



//    private void initTopicRecyclerView(){
//        RecyclerView topicRecyclerView  = findViewById(R.id.topic_list_recycler_view);
//        topicRecyclerViewAdapter adapter = new topicRecyclerViewAdapter(mTopicName,mTopicThumb,mTopicDownloadStatus,this);
//        topicRecyclerView.setAdapter(adapter);
//        topicRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        Log.i(null,"recycler create");
//    }
}