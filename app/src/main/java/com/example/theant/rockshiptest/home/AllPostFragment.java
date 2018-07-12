package com.example.theant.rockshiptest.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.theant.rockshiptest.Constants;
import com.example.theant.rockshiptest.R;
import com.example.theant.rockshiptest.data.PostDataSource;
import com.example.theant.rockshiptest.model.ListPost;
import com.example.theant.rockshiptest.model.Post;
import com.example.theant.rockshiptest.util.JsonUtil;
import com.example.theant.rockshiptest.util.PaginationScrollListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v4.util.Preconditions.checkNotNull;

@SuppressLint("ValidFragment")
class AllPostFragment extends Fragment implements HomeContact.View {
    private static final String TAG = "home_frag";
    @BindView(R.id.listPost)
    RecyclerView listPost;
    @BindView(R.id.loading)
    ProgressBar loadingProgress;

    private PostsAdapter adapter;
    private boolean isLastPage = false;
    private boolean isLoading = false;
    private int currentPage = 0;
    private int totalPage = 0;

    private List<Post> postList;
    private HomeContact.Presenter mPresenter;

    public static AllPostFragment newInstance() {
        AllPostFragment fragment = new AllPostFragment();
        return fragment;
    }

    public AllPostFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter = new HomePresenter(this, PostDataSource.getInstance(getContext()));
        mPresenter.loadPosts(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_all_post, container, false);
        ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    private void initUI() {
        LinearLayoutManager layoutManager
                    = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        listPost.setLayoutManager(layoutManager);
        listPost.setHasFixedSize(true);
        listPost.setItemAnimator(new DefaultItemAnimator());

        adapter = new PostsAdapter(new ArrayList<>());
        listPost.setAdapter(adapter);

        listPost.addOnScrollListener(new PaginationScrollListener(layoutManager) {

            @Override
            protected void loadMoreItems() {
                isLoading = true;
                mPresenter.loadMoreItems(postList, currentPage);
            }

            @Override
            protected int getTotalPageCount() {
                return totalPage;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }

    @Override
    public void setLoading(boolean active) {
        loadingProgress.setVisibility(active ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showPosts(List<Post> posts) {
        initData(posts);
    }

    @Override
    public void loadMoreItems(List<Post> moreItems) {
        isLoading = false;
        adapter.removeLoadingFooter();
        adapter.addAll(moreItems);

        if (currentPage != totalPage)
            adapter.addLoadingFooter();
        else
            isLastPage = true;
    }

    private void initData(List<Post> posts) {
        if (posts != null) {
            postList = (posts);
            if (postList.size() % 10 == 0) {
                totalPage = postList.size() / 10;
            } else {
                totalPage = postList.size() / 10 + 1;
            }
            Log.d(TAG, "initData: " + totalPage);

            adapter.clear();
            adapter.addAll(postList.subList(currentPage, currentPage + 10));

            if (currentPage <= totalPage)
                adapter.addLoadingFooter();
            else
                isLastPage = true;
            currentPage += 10;
        }
    }

}
