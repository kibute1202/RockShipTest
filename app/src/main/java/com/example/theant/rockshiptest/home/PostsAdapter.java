package com.example.theant.rockshiptest.home;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.theant.rockshiptest.R;
import com.example.theant.rockshiptest.model.Post;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private List<Post> listPost;
    private boolean isLoadingAdded = false;

    public PostsAdapter(List<Post> listPost) {
        this.listPost = listPost;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = null;
        View view;

        switch (viewType) {
            case ITEM:
                view = inflater.inflate(R.layout.post_item, parent, false);
                viewHolder = new PostVH(view);
                break;
            case LOADING:
                view = inflater.inflate(R.layout.loading_more_item, parent, false);
                viewHolder = new LoadingMoreVH(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Post post = getItem(position);
        switch (getItemViewType(position)) {
            case ITEM:
                ((PostVH) holder).bind(post);
                break;
            case LOADING:
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (position == listPost.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    @Override
    public int getItemCount() {
        return listPost.size();
    }

    public void add(Post post) {
        listPost.add(post);
        notifyItemInserted(listPost.size() - 1);
    }

    public void addAll(List<Post> posts) {
        for (Post story : posts) {
            add(story);
        }
    }

    public void remove(Post story) {
        int position = listPost.indexOf(story);
        if (position > -1) {
            listPost.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        Post post = new Post();
        add(post);
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;
        int position = listPost.size() - 1;
        Post removeStory = getItem(position);
        if (removeStory != null) {
            listPost.remove(position);
            notifyItemRemoved(position);
        }
    }

    private Post getItem(int position) {
        return listPost.get(position);
    }

    public class PostVH extends RecyclerView.ViewHolder {
        @BindView(R.id.avatar)
        ImageView avatar;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.dateTime)
        TextView createdDate;
        @BindView(R.id.category)
        TextView category;
        @BindView(R.id.subCategory)
        TextView subCategory;
        @BindView(R.id.menuItem)
        ImageView menuItem;
        @BindView(R.id.caption)
        TextView caption;
        @BindView(R.id.description)
        ReadMoreTextView description;
        @BindView(R.id.primaryPhotos)
        ImageView primaryPhoto;
        @BindView(R.id.photosMore)
        ImageView photosMore;
        @BindView(R.id.count_photos_more)
        TextView countPhotosMore;
        @BindView(R.id.register_count)
        TextView registerCount;
        @BindView(R.id.like_count)
        TextView likeCount;
        @BindView(R.id.comment_count)
        TextView commentCount;
        @BindView(R.id.view_count)
        TextView viewCount;

        @BindView(R.id.register)
        ConstraintLayout registerButton;
        @BindView(R.id.ic_register)
        ImageView imgRegister;
        @BindView(R.id.text_register)
        TextView textRegister;
        @BindView(R.id.like)
        ConstraintLayout likeButton;
        @BindView(R.id.ic_like)
        ImageView imgLike;
        @BindView(R.id.text_like)
        TextView textLike;
        @BindView(R.id.share)
        ConstraintLayout shareButton;
        @BindView(R.id.ic_share)
        ImageView imgShare;
        @BindView(R.id.text_share)
        TextView textShare;

        public PostVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final Post item) {
            RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .placeholder(R.drawable.placeholder_image)
                        .error(R.drawable.placeholder_image);
            Glide.with(itemView).load(item.getUser().getAvatar()).apply(options).into(avatar);

            name.setText(item.getUser().getName());
            createdDate.setText(item.getCreatedTime());
            category.setText(item.getCategory());

            String subCategoryStr = parseSubCategory(item.getSubCategory());
            subCategory.setText(subCategoryStr);

            caption.setText(item.getCaption());
            description.setText(item.getDescription());

            setPhotos(item.getPhotos());
            registerCount.setText(item.getRegisterCount() + " " + getStringById(R.string.registers_text));
            likeCount.setText(item.getLikeCount() + " " + getStringById(R.string.likes_text));
            commentCount.setText(item.getCommentCount() + " " + getStringById(R.string.comments_text));
            viewCount.setText(item.getViewCount() + " " + getStringById(R.string.views_text));
        }

        private String getStringById(int id) {
            return itemView.getResources().getString(id);
        }

        private void setPhotos(List<String> photos) {
            if (photos.size() > 2) {
                Glide.with(itemView).load(photos.get(0)).into(primaryPhoto);
                Glide.with(itemView).load(photos.get(1)).into(photosMore);
                countPhotosMore.setText(photos.size() - 1 + "+");
            } else if (photos.size() == 2) {
                Glide.with(itemView).load(photos.get(0)).into(primaryPhoto);
                Glide.with(itemView).load(photos.get(1)).into(photosMore);
                countPhotosMore.setVisibility(View.INVISIBLE);
            } else {
                Glide.with(itemView).load(photos.get(0)).into(primaryPhoto);
                photosMore.setVisibility(View.GONE);
                countPhotosMore.setVisibility(View.GONE);
            }
        }

        private String parseSubCategory(List<String> subCategory) {
            String result = "";
            for (String sub : subCategory) {
                result += sub + ", ";
            }
            return  result.substring(0, result.length() - 2);
        }
    }

    public class LoadingMoreVH extends RecyclerView.ViewHolder {

        public LoadingMoreVH(View itemView) {
            super(itemView);
        }
    }
}
