package com.example.theant.rockshiptest.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.theant.rockshiptest.R;

import butterknife.ButterKnife;

@SuppressLint("ValidFragment")
class OpportunityFragment extends Fragment {

    public static OpportunityFragment newInstance() {
        OpportunityFragment fragment = new OpportunityFragment();
        return fragment;
    }

    public OpportunityFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_opportunity, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
