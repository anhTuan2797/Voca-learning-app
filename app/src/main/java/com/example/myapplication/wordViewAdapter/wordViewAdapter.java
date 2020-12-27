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
import com.example.myapplication.recyclerViewClickInterface;

import java.util.ArrayList;

public class wordViewAdapter extends RecyclerView.Adapter<wordViewAdapter.Viewholder>{
    private ArrayList<String> mWords = new ArrayList<>();
    private ArrayList<String> mWordsMeaning = new ArrayList<>();
    private recyclerViewClickInterface mRecyclerViewClickInterface;
    private Context mcontext;

    public wordViewAdapter(ArrayList<String> words, ArrayList<String> wordsMeaning,recyclerViewClickInterface recyclerViewClickInterface, Context context ){
        mWords = words;
        mWordsMeaning = wordsMeaning;
        mRecyclerViewClickInterface = recyclerViewClickInterface;
        mcontext = context;
        Log.i(null, "wordViewAdapter: run");
    }

    public void setData(ArrayList<String> words, ArrayList<String> wordsMeaning){
        mWords = words;
        mWordsMeaning = wordsMeaning;
        notifyDataSetChanged();
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
        Log.i(null, "onBindViewHolder: run");
    }

    @Override
    public int getItemCount() {
        return mWords.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView word;
        TextView word_meaning;
        public Viewholder(@NonNull View itemView){
            super(itemView);
            Log.i(null, "view create");
            word = itemView.findViewById(R.id.word);
            word_meaning = itemView.findViewById(R.id.word_meaning);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRecyclerViewClickInterface.onItemClick(getAdapterPosition(),v);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mRecyclerViewClickInterface.onItemLongClick(getAdapterPosition(),v);
                    return false;
                }
            });
        }
    }
}
