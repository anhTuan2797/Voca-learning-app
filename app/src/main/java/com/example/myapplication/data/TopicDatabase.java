package com.example.myapplication.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Topic.class,word.class},version = 1)
public abstract class TopicDatabase extends RoomDatabase {

    public abstract TopicDao topicDao();
    public abstract wordDao wordDao();
    private static TopicDatabase mTopicDatabase;

    public static synchronized TopicDatabase getInstance(Context context){
        if (mTopicDatabase == null) {
            mTopicDatabase = Room.databaseBuilder(context.getApplicationContext(),TopicDatabase.class,"appdb")
                                    .fallbackToDestructiveMigration()
                                    .addCallback(roomCallBack)
                                    .build();
        }
        return mTopicDatabase;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(new Runnable() {
                @Override
                public void run() {

                }
            });
        }
    };

}
