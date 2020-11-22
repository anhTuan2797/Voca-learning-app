package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavAction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.wordViewAdapter.wordViewAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link topic_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class topic_fragment extends Fragment implements recyclerViewClickInterface{
    Toolbar toolbar;
    NavController navController;
    RecyclerView recyclerView;
    private ArrayList<String> mWords = new ArrayList<>();
    private ArrayList<String> mWordsMeaning = new ArrayList<>();
    private ArrayList<String> mWordsPronoun = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_topic_fragment, container, false);
        toolbar = view.findViewById(R.id.word_toolBar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("test");
        setHasOptionsMenu(true);
        initWord();
        recyclerView = view.findViewById(R.id.word_recycler_view);
        wordViewAdapter adapter = new wordViewAdapter(mWords,mWordsMeaning,mWordsPronoun,this,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
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
    public void onItemClick(int position, View view) {
        Log.i(null, "word clicked");
        Navigation.findNavController(view).navigate(R.id.action_topic_fragment2_to_word_fragment);
    }
    private void initWord(){
        mWords.clear();
        mWordsMeaning.clear();
        mWordsPronoun.clear();

        mWords.add("word 1");
        mWordsMeaning.add("meaning");
        mWordsPronoun.add("pronoun");

        mWords.add("word 2");
        mWordsMeaning.add("meaning2");
        mWordsPronoun.add("pronoun2");

        mWords.add("word 3");
        mWordsMeaning.add("meaning3");
        mWordsPronoun.add("pronoun3");
    }
}