package com.example.theant.rockshiptest.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.theant.rockshiptest.Constants;
import com.example.theant.rockshiptest.R;
import com.example.theant.rockshiptest.model.Post;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    @BindView(R.id.imgUser)
    ImageView imgUser;
    @BindView(R.id.imgFilter)
    ImageView imgFilter;
    @BindView(R.id.edtSearch)
    EditText edtSearch;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.createPost)
    FloatingActionButton btnCreatePost;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    private void initUI() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(fragmentManager);

        AllPostFragment allPostFragment = AllPostFragment.newInstance();
        CommunityFragment communityFragment = CommunityFragment.newInstance();
        OpportunityFragment opportunityFragment = OpportunityFragment.newInstance();

        viewPagerAdapter.addFragment(allPostFragment, Constants.All_POST);
        viewPagerAdapter.addFragment(communityFragment, Constants.COMMUNITY);
        viewPagerAdapter.addFragment(opportunityFragment, Constants.OPPORTUNITY);

        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
    }
}
