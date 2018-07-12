package com.example.theant.rockshiptest.home;

import com.example.theant.rockshiptest.BasePresenter;
import com.example.theant.rockshiptest.BaseView;
import com.example.theant.rockshiptest.model.Post;

import java.util.List;

public interface HomeContact {

    interface View extends BaseView<Presenter> {

        void setLoading(boolean active);

        void showPosts(List<Post> posts);

        void loadMoreItems(List<Post> moreItems);
    }

    interface Presenter extends BasePresenter {

        void setView(View view);

        void destroy();

        void result(int resultCode);

        void loadPosts(boolean showLoading);

        void loadMoreItems(List<Post> postList, int currentPage);
    }
}
