package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavAction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.data.TopicWithWords;
import com.example.myapplication.data.word;
import com.example.myapplication.wordViewAdapter.wordViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link topic_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class topic_fragment extends Fragment implements recyclerViewClickInterface{
    Toolbar toolbar;
    NavController navController;
    RecyclerView recyclerView;
    private TopicViewModel topicViewModel;
    private int mTopicId;
    private String mTopicName;
    private static ArrayList<String> mWords = new ArrayList<>();
    private static ArrayList<String> mWordsMeaning = new ArrayList<>();
    private static ArrayList<String> mWordsPronoun = new ArrayList<>();
    private static ArrayList<String> getmWordHint = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_topic_fragment, container, false);
        toolbar = view.findViewById(R.id.word_toolBar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle(mTopicName);
        setHasOptionsMenu(true);
        recyclerView = view.findViewById(R.id.word_recycler_view);
        wordViewAdapter adapter = new wordViewAdapter(mWords,mWordsMeaning,mWordsPronoun,this,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        topicViewModel = new ViewModelProvider(this).get(TopicViewModel.class);
        initWord(adapter);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.word_tool_bar,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.play_button:
                Log.i("", "onOptionsItemSelected: play button click");
                Bundle result = new Bundle();
                ArrayList<String> data = new ArrayList<>();
                for(int i=0;i<mWords.size();i++){
                    data.add(mWords.get(i));
                    data.add(getmWordHint.get(i));
                    data.add(mWordsMeaning.get(i));
                }
                result.putStringArrayList("wordData",data);
                getParentFragmentManager().setFragmentResult("wordData",result);
                Navigation.findNavController(this.getView()).navigate(R.id.action_topic_fragment2_to_word_fragment);
                return true;
            case R.id.add_word_button:
                Log.i("", "onOptionsItemSelected: add word click");
                Intent intent = new Intent(this.getContext(),addNewWordActivity.class);
                intent.putExtra("topicID",mTopicId);
                intent.putExtra("topicName",mTopicName);
                startActivity(intent);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(int position, View view) {
        Log.i(null, "word clicked");
        Bundle wordResult = new Bundle();
        ArrayList<String> data = new ArrayList<>();
        data.add(mWords.get(position));
        Log.i("", "onItemClick: mword " + mWords.get(position));
        data.add(getmWordHint.get(position));
        data.add(mWordsMeaning.get(position));
        wordResult.putStringArrayList("wordData",data);
        getParentFragmentManager().setFragmentResult("wordData",wordResult);
        Navigation.findNavController(view).navigate(R.id.action_topic_fragment2_to_word_fragment);
    }
    private void initWord(wordViewAdapter adapter){
        getParentFragmentManager().setFragmentResultListener("topicId", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                mTopicId = result.getInt("topicId");
                Log.i("", "onFragmentResult: topic id" + Integer.toString(mTopicId));
                topicViewModel.getTopicById(mTopicId).observe(getViewLifecycleOwner(), new Observer<List<TopicWithWords>>() {
                    @Override
                    public void onChanged(List<TopicWithWords> topicWithWords) {
                        mTopicName = topicWithWords.get(0).getTopic().getTopicName();
                        toolbar.setTitle(mTopicName);
                        mWords.clear();
                        mWordsMeaning.clear();
                        mWordsPronoun.clear();
                        List<word> wordList = topicWithWords.get(0).getWords();

                        for(int i=0;i<wordList.size();i++){
                            mWords.add(wordList.get(i).getWord());
                            mWordsMeaning.add(wordList.get(i).getWord_mean());
                            mWordsPronoun.add("pronoun");
                            getmWordHint.add(wordList.get(i).getWord_hint());
                        }
                        adapter.setData(mWords,mWordsMeaning,mWordsPronoun);
                    }
                });
            }
        });
//        mWords.clear();
//        mWordsMeaning.clear();
//        mWordsPronoun.clear();
//
//        mWords.add("word 1");
//        mWordsMeaning.add("meaning");
//        mWordsPronoun.add("pronoun");
//
//        mWords.add("word 2");
//        mWordsMeaning.add("meaning2");
//        mWordsPronoun.add("pronoun2");
//
//        mWords.add("word 3");
//        mWordsMeaning.add("meaning3");
//        mWordsPronoun.add("pronoun3");
    }
}