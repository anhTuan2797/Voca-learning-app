package com.example.myapplication.data;

import java.util.Comparator;

public class sortByPriority implements Comparator<word> {

    @Override
    public int compare(word o1, word o2) {
        return o2.getPriority()-o1.getPriority();
    }
}
