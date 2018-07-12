package com.example.theant.rockshiptest.data;

import com.example.theant.rockshiptest.model.Post;

import java.util.List;

public interface DataSource {

    interface LoadPostsCallback {

        void onPostsLoaded(List<Post> posts);

        void onDataNoAvailable();
    }

    void getPosts(LoadPostsCallback callback);
}
