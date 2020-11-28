package com.example.myapplication.WordViewPager2;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Locale;

public class wordViewPagerAdapter extends RecyclerView.Adapter<wordViewPagerAdapter.ViewHolder>{
    private ArrayList<String> mData;
    private Context mContext;
    private ViewPager2 mViewPager2;
    private TextToSpeech textToSpeech;

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
//            String text = mData.get(position);
//            if(URLUtil.isValidUrl(text)){
//                holder.wordContent.setText("");
//                holder.wordImage.setVisibility(View.VISIBLE);
//                Glide.with(mContext)
//                        .asBitmap()
//                        .load(mData.get(position))
//                        .into(holder.wordImage);
//            }
//            else {
//                holder.wordContent.setText(mData.get(position));
//                textToSpeech = new TextToSpeech(mContext, new TextToSpeech.OnInitListener() {
//                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//                    @Override
//                    public void onInit(int status) {
//                        if(status == TextToSpeech.ERROR){
//                            Log.e("TTS", "onInit: engine install fail");
//                        }
//                        else{
//                            Log.e("TTS", "onInit: engine install success");
//                            textToSpeech.setLanguage(Locale.US);
//                            textToSpeech.setSpeechRate((float) 0.5);
//                            textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null,null);
//                        }
//                    }
//                });
//            }
        if(position % 4 == 0){
            String text = mData.get(position);
            holder.wordContent.setText(text);
            holder.speakButton.setVisibility(View.VISIBLE);
            textToSpeech = new TextToSpeech(mContext, new TextToSpeech.OnInitListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onInit(int status) {
                    if(status == TextToSpeech.ERROR){
                        Log.e("TTS", "onInit: engine install fail" );
                    }
                    else {
                        Log.e("TTS", "onInit: engine install success");
                        textToSpeech.setLanguage(Locale.US);
                        textToSpeech.setSpeechRate((float) 0.5);
                        textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null,null);
                    }
                }
            });
            holder.speakButton.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                        textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null,null);
                }
            });
        }
        else if((position-1)%4 == 0){
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
        private FloatingActionButton speakButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wordContent =itemView.findViewById(R.id.word_content);
            wordImage = itemView.findViewById(R.id.word_image);
            speakButton = itemView.findViewById(R.id.speak_button);
        }
    }
}
