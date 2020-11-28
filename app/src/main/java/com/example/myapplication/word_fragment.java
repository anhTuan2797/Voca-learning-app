package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.WordViewPager2.wordViewPagerAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link word_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class word_fragment extends Fragment {
    ViewPager2 viewPager2;
    ArrayList<String> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_word_fragment, container, false);
        viewPager2 = view.findViewById(R.id.word_viewPager2);
        getData();
        wordViewPagerAdapter adapter = new wordViewPagerAdapter(getContext(),data,viewPager2);
        viewPager2.setAdapter(adapter);
        return view;
    }

    public void getData(){
        data.add("word 1");
        data.add("https://images.pexels.com/photos/5650027/pexels-photo-5650027.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
        data.add("word 2");
    }
}