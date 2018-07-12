package com.example.theant.rockshiptest.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.theant.rockshiptest.R;

@SuppressLint("ValidFragment")
class WorldFragment extends Fragment {

    public static WorldFragment newInstance() {
        return new WorldFragment();
    }

    public WorldFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_world, container, false);
        return view;
    }
}
