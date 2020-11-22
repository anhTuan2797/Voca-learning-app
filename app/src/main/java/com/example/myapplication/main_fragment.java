package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.TopicRecylerView.TopicRecylerViewAdapter.topicRecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link main_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class main_fragment extends Fragment implements View.OnClickListener, recyclerViewClickInterface{
    NavController navController;
    RecyclerView recyclerView;
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
        initImage();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_fragment, container, false);
        recyclerView = view.findViewById(R.id.topic_list_recycler_view);
        topicRecyclerViewAdapter adapter = new topicRecyclerViewAdapter(mTopicName,mTopicThumb,mTopicDownloadStatus,this,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return view;
    }



    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.add_card_button){
            Log.i(null, "add card button clicked");
        }
    }

    private void initImage(){
        mTopicName.add("topic 2");
        mTopicThumb.add("https://images.pexels.com/photos/5650027/pexels-photo-5650027.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
        mTopicDownloadStatus.add(true);

        mTopicName.add("topic 3");
        mTopicThumb.add("https://images.pexels.com/photos/5650027/pexels-photo-5650027.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
        mTopicDownloadStatus.add(false);

        mTopicName.add("topic 1");
        mTopicThumb.add("https://images.pexels.com/photos/5650027/pexels-photo-5650027.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
        mTopicDownloadStatus.add(false);

    }

    @Override
    public void onItemClick(int position) {
        Log.i(null, mTopicName.get(position));
    }

//    private void initTopicRecyclerView(){
//        RecyclerView topicRecyclerView  = findViewById(R.id.topic_list_recycler_view);
//        topicRecyclerViewAdapter adapter = new topicRecyclerViewAdapter(mTopicName,mTopicThumb,mTopicDownloadStatus,this);
//        topicRecyclerView.setAdapter(adapter);
//        topicRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        Log.i(null,"recycler create");
//    }
}