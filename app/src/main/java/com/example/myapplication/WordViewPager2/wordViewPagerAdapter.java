package com.example.myapplication.WordViewPager2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.util.ArrayList;

public class wordViewPagerAdapter extends RecyclerView.Adapter<wordViewPagerAdapter.ViewHolder>{
    private ArrayList<String> mData;
    private Context mContext;
    private ViewPager2 mViewPager2;

    public wordViewPagerAdapter(Context context, ArrayList<String> data, ViewPager2 viewPager2){
            mContext = context;
            mData = data;
            mViewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            String text = mData.get(position);
            if(URLUtil.isValidUrl(text)){
                holder.wordContent.setText("");
                holder.wordImage.setVisibility(View.VISIBLE);
                Glide.with(mContext)
                        .asBitmap()
                        .load(mData.get(position))
                        .into(holder.wordImage);
            }
            else {
                holder.wordContent.setText(mData.get(position));
            }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView wordContent;
        private ImageView wordImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wordContent =itemView.findViewById(R.id.word_content);
            wordImage = itemView.findViewById(R.id.word_image);
        }
    }
}
