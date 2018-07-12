package com.example.theant.rockshiptest.data;

import android.content.Context;

import com.example.theant.rockshiptest.Constants;
import com.example.theant.rockshiptest.model.ListPost;
import com.example.theant.rockshiptest.util.JsonUtil;
import com.google.gson.Gson;

public class PostDataSource implements DataSource {

    private static PostDataSource INSTANCE = null;

    private Context context;

    private PostDataSource(Context context) {
        this.context = context;
    }

    public static PostDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (PostDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PostDataSource(context);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getPosts(LoadPostsCallback callback) {
        String dataJson = JsonUtil.loadDataJsonFromAssets(context.getAssets(), Constants.DATA_JSON);
        Gson gson = new Gson();
        final ListPost listPost = gson.fromJson(dataJson, ListPost.class);
        if (listPost != null) {
            callback.onPostsLoaded(listPost.getData());
        } else {
            callback.onDataNoAvailable();
        }
    }
}
