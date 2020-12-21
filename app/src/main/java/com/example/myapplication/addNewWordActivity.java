package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.data.word;

public class addNewWordActivity extends AppCompatActivity {
    private EditText wordEditText;
    private EditText meanEditText;
    private EditText hintEditText;
    private TopicViewModel topicViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_word);

        wordEditText = findViewById(R.id.word_input);
        meanEditText = findViewById(R.id.mean_input);
        hintEditText = findViewById(R.id.hint_input);
        topicViewModel = new ViewModelProvider(this).get(TopicViewModel.class);
    }

    public void addNewWord(View view) {
        Bundle extras = getIntent().getExtras();
        int topicID = 0;
        String topicName = null;
        if(extras != null){
            topicID = (int) extras.get("topicID");
            topicName = (String) extras.get("topicName");
        }

//        Log.i("", "addNewWord:topic id " +topicID);
        Log.i("", "addNewWord:topic name "+topicName);
        String mWord = wordEditText.getText().toString();
        String mMean = meanEditText.getText().toString();
        String mHint = hintEditText.getText().toString();
        if(topicID != 0){
            word word = new word(mWord,mMean,mHint,1,topicID);
            topicViewModel.insertAllWord(word);
        }

    }
}