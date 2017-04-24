package com.app.capstone.xcampus.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.capstone.xcampus.R;

/**
 * Created by pavle on 2017-04-17.
 */

public class LikesFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_my_likes, container, false);




        return view;
    }

}
