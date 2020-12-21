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
//            mViewPager2.setPageTransformer(new ViewPager2.PageTransformer() {
//                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//                @Override
//                public void transformPage(@NonNull View page, float position) {
//                    int pageWidth = page.getWidth();
//
//                    if(position < -1) {
//                        page.setAlpha(0f);
//                    } else if(position <= 0){
//                        page.setAlpha(1f);
//                        page.setTranslationX(0f);
//                        page.setTranslationZ(0f);
//                        page.setScaleX(1f);
//                        page.setScaleY(1f);
//                    } else if(position <= 1){
//                        page.setAlpha(1 - position);
//                        page.setTranslationX(pageWidth*-position);
//                        page.setTranslationZ(-1f);
//                        float scaleFactor = 0.75f + (1 - 0.75f)*(1- Math.abs(position));
//                        page.setScaleX(scaleFactor);
//                        page.setScaleY(scaleFactor);
//                    }else {
//                        page.setAlpha(0f);
//                    }
//                }
//            });
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
//        if(position % 3 == 0){
//            Log.i("", String.valueOf(position));
//            String text = mData.get(position);
//            holder.wordContent.setText(text);
//            holder.speakButton.setVisibility(View.VISIBLE);
//            textToSpeech = new TextToSpeech(mContext, new TextToSpeech.OnInitListener() {
//                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//                @Override
//                public void onInit(int status) {
//                    if(status == TextToSpeech.ERROR){
//                        Log.e("TTS", "onInit: engine install fail" );
//                    }
//                    else {
//                        Log.e("TTS", "onInit: engine install success");
//                        textToSpeech.setLanguage(Locale.US);
//                        textToSpeech.setSpeechRate((float) 0.5);
//                        textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null,null);
//                    }
//                }
//            });
//            holder.speakButton.setOnClickListener(new View.OnClickListener() {
//                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//                @Override
//                public void onClick(View v) {
//                        textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null,null);
//                }
//            });
//        }
//        else if((position-1)%3 == 0){
//            Log.i("", String.valueOf(position));
//            holder.wordContent.setText("");
//            holder.wordImage.setVisibility(View.VISIBLE);
//            Glide.with(mContext)
//                    .asBitmap()
//                    .load(mData.get(position))
//                    .into(holder.wordImage);
//        }
//        else {
//            Log.i("", String.valueOf(position));
//            holder.wordContent.setText(mData.get(position));
//        }
        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                if(position %3 == 0){
                    Log.i("", String.valueOf(position));
                    String text = mData.get(position);
                    holder.wordImage.setVisibility(View.INVISIBLE);
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
                else if((position-1)%3 == 0){
                    if(URLUtil.isValidUrl(mData.get(position))) {
                        Log.i("", String.valueOf(position));
                        holder.wordContent.setText("");
                        holder.wordImage.setVisibility(View.VISIBLE);
                        holder.speakButton.setVisibility(View.INVISIBLE);
                        Glide.with(mContext)
                                .asBitmap()
                                .load(mData.get(position))
                                .into(holder.wordImage);
                    }
                    else {
                        Log.i("", String.valueOf(position));
                        holder.wordImage.setVisibility(View.INVISIBLE);
                        holder.speakButton.setVisibility(View.INVISIBLE);
                        holder.wordContent.setText(mData.get(position));
                    }
                }
                else if((position-2)%3 == 0) {
                    Log.i("", String.valueOf(position));
                    holder.wordImage.setVisibility(View.INVISIBLE);
                    holder.speakButton.setVisibility(View.INVISIBLE);
                    holder.wordContent.setText(mData.get(position));
                }
                super.onPageSelected(position);
            }
        });
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
