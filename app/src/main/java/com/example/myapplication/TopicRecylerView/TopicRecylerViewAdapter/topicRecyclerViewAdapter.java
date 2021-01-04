package com.example.myapplication.TopicRecylerView.TopicRecylerViewAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.recyclerViewClickInterface;

import java.util.ArrayList;

public class topicRecyclerViewAdapter extends RecyclerView.Adapter<topicRecyclerViewAdapter.ViewHolder>{
    private ArrayList<String> mTopicNames = new ArrayList<>();
    private ArrayList<String> mTopicThumbs = new ArrayList<>();
    private ArrayList<Boolean> mTopicDownloadStatus = new ArrayList<Boolean>();
    private recyclerViewClickInterface mRecyclerViewClickInterface;
    private Context mContext;

    public topicRecyclerViewAdapter(ArrayList<String> topicNames, ArrayList<String> topicThumbs, ArrayList<Boolean> topicDownloadStatus,recyclerViewClickInterface recyclerViewClickInterface, Context context){
        mTopicNames = topicNames;
        mTopicThumbs = topicThumbs;
        mTopicDownloadStatus = topicDownloadStatus;
        mRecyclerViewClickInterface = recyclerViewClickInterface;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_card_view,parent,false);
        return new ViewHolder(view);
    }


    @SuppressLint("WrongConstant")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.i(null,"viewholder create bind");
        Glide.with(mContext)
                .asBitmap()
                .load(mTopicThumbs.get(position))
                .into(holder.topic_thumb);
        holder.topic_name.setText(mTopicNames.get(position));
        if(mTopicDownloadStatus.get(position)){
            holder.topic_download_status.setVisibility(View.VISIBLE);
            Log.i(null,"true");
        }
        else {
            holder.topic_download_status.setVisibility(View.INVISIBLE);
            Log.i(null,"false");
        }
    }

    @Override
    public int getItemCount() {
        return mTopicNames.size();
    }

    public void setData(ArrayList<String> TopicNames, ArrayList<String> TopicThumbs,ArrayList<Boolean> TopicDownloadStatus){
        this.mTopicNames = TopicNames;
        this.mTopicThumbs = TopicThumbs;
        this.mTopicDownloadStatus = TopicDownloadStatus;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView topic_name;
        ImageView topic_thumb;
        ImageView topic_download_status;
        CardView topic_card_view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.i(null,"view create");
            topic_card_view = itemView.findViewById(R.id.topic_card_view);
            topic_name = itemView.findViewById(R.id.topic_name);
            topic_thumb = itemView.findViewById(R.id.topic_thumbnail);
            topic_download_status = itemView.findViewById(R.id.topic_download_status);
           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   mRecyclerViewClickInterface.onItemClick(getAdapterPosition(),v);
               }
           });
        }

    }
}
