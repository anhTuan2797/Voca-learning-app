package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.data.word;

public class addNewWordActivity extends AppCompatActivity {
    private Bundle extras;
    private EditText wordEditText;
    private EditText meanEditText;
    private EditText hintEditText;
    private TopicViewModel topicViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_word);

        extras = getIntent().getExtras();

        TextView topicNameHeader = findViewById(R.id.topic_name_header);
        wordEditText = findViewById(R.id.word_input);
        meanEditText = findViewById(R.id.mean_input);
        hintEditText = findViewById(R.id.hint_input);
        topicViewModel = new ViewModelProvider(this).get(TopicViewModel.class);
        String header = "ADD NEW WORD FOR\n" + (String) extras.get("topicName");
        topicNameHeader.setText(header);
    }

    public void addNewWord(View view) {
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
            if(TextUtils.isEmpty(mWord) || TextUtils.isEmpty(mMean) || TextUtils.isEmpty(mHint)){
                Toast.makeText(this,"please enter all field",Toast.LENGTH_SHORT).show();
            }
            else {
                word word = new word(mWord,mMean,mHint,1,topicID);
                topicViewModel.insertAllWord(word);
                Toast.makeText(this,"word added",Toast.LENGTH_SHORT).show();

                wordEditText.setText("");
                meanEditText.setText("");
                hintEditText.setText("");
            }

        }

    }
}