package com.example.myapplication.wordViewAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.TopicRecylerView.TopicRecylerViewAdapter.topicRecyclerViewAdapter;
import com.example.myapplication.recyclerViewClickInterface;

import java.util.ArrayList;

public class wordViewAdapter extends RecyclerView.Adapter<wordViewAdapter.Viewholder>{
    private ArrayList<String> mWords = new ArrayList<>();
    private ArrayList<String> mWordsMeaning = new ArrayList<>();
    private ArrayList<String> mWordPronoun = new ArrayList<>();
    private recyclerViewClickInterface mRecyclerViewClickInterface;
    private Context mcontext;

    public wordViewAdapter(ArrayList<String> words, ArrayList<String> wordsMeaning, ArrayList<String> wordsPronoun,recyclerViewClickInterface recyclerViewClickInterface, Context context ){
        mWords = words;
        mWordsMeaning = wordsMeaning;
        mWordPronoun = wordsPronoun;
        mRecyclerViewClickInterface = recyclerViewClickInterface;
        mcontext = context;
        Log.i(null, "wordViewAdapter: run");
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_card_view,parent,false);
        Log.i(null, "onCreateViewHolder: run");
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.word.setText(mWords.get(position));
        holder.word_meaning.setText(mWordsMeaning.get(position));
        holder.word_pronoun.setText(mWordPronoun.get(position));
        Log.i(null, "onBindViewHolder: run");
    }

    @Override
    public int getItemCount() {
        return mWords.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView word;
        TextView word_meaning;
        TextView word_pronoun;
        public Viewholder(@NonNull View itemView){
            super(itemView);
            Log.i(null, "view create");
            word = itemView.findViewById(R.id.word);
            word_meaning = itemView.findViewById(R.id.word_meaning);
            word_pronoun = itemView.findViewById(R.id.word_pronoun);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRecyclerViewClickInterface.onItemClick(getAdapterPosition(),v);
                }
            });
        }
    }
}