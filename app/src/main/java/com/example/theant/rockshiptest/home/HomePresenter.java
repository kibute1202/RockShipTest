package com.example.theant.rockshiptest.home;

import com.example.theant.rockshiptest.data.DataSource;
import com.example.theant.rockshiptest.data.PostDataSource;
import com.example.theant.rockshiptest.model.Post;

import java.util.List;

import static android.support.v4.util.Preconditions.checkNotNull;

public class HomePresenter implements HomeContact.Presenter {

    private HomeContact.View mHomeView;

    private PostDataSource postDataSource;

    public HomePresenter(HomeContact.View homeView, PostDataSource postDataSource) {
        mHomeView = homeView;
        this.postDataSource = postDataSource;
    }

    @Override
    public void start() {

    }

    @Override
    public void setView(HomeContact.View view) {
        this.mHomeView = view;
    }

    @Override
    public void destroy() {
        this.mHomeView = null;
    }

    @Override
    public void result(int resultCode) {

    }

    @Override
    public void loadPosts(boolean showLoading) {
        if (showLoading) mHomeView.setLoading(true);
        postDataSource.getPosts(new DataSource.LoadPostsCallback() {
            @Override
            public void onPostsLoaded(List<Post> posts) {
                mHomeView.showPosts(posts);
                mHomeView.setLoading(false);
            }

            @Override
            public void onDataNoAvailable() {

            }
        });
    }

    @Override
    public void loadMoreItems(List<Post> postList, int currentPage) {
        List<Post> moreItems = postList.subList(currentPage, currentPage + 10);
        mHomeView.loadMoreItems(moreItems);
    }

}
