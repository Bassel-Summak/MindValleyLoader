
package com.mindvalleytestapp.mvp.model.list;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.mindvalleytestapp.mvp.model.extra.Category;
import com.mindvalleytestapp.mvp.model.extra.Links;
import com.mindvalleytestapp.mvp.model.extra.Urls;
import com.mindvalleytestapp.mvp.model.extra.User;

@SuppressWarnings("unused")
public class ServerItem {

    @SerializedName("categories")
    private List<Category> mCategories;
    @SerializedName("color")
    private String mColor;
    @SerializedName("created_at")
    private String mCreatedAt;
    @SerializedName("current_user_collections")
    private List<Object> mCurrentUserCollections;
    @SerializedName("height")
    private Long mHeight;
    @SerializedName("id")
    private String mId;
    @SerializedName("liked_by_user")
    private Boolean mLikedByUser;
    @SerializedName("likes")
    private String mLikes;
    @SerializedName("links")
    private Links mLinks;
    @SerializedName("urls")
    private Urls mUrls;
    @SerializedName("user")
    private User mUser;
    @SerializedName("width")
    private Long mWidth;

    public List<Category> getCategories() {
        return mCategories;
    }

    public void setCategories(List<Category> categories) {
        mCategories = categories;
    }

    public String getColor() {
        return mColor;
    }

    public void setColor(String color) {
        mColor = color;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public List<Object> getCurrentUserCollections() {
        return mCurrentUserCollections;
    }

    public void setCurrentUserCollections(List<Object> currentUserCollections) {
        mCurrentUserCollections = currentUserCollections;
    }

    public Long getHeight() {
        return mHeight;
    }

    public void setHeight(Long height) {
        mHeight = height;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public Boolean getLikedByUser() {
        return mLikedByUser;
    }

    public void setLikedByUser(Boolean likedByUser) {
        mLikedByUser = likedByUser;
    }

    public String getLikes() {
        return mLikes;
    }

    public void setLikes(String likes) {
        mLikes = likes;
    }

    public Links getLinks() {
        return mLinks;
    }

    public void setLinks(Links links) {
        mLinks = links;
    }

    public Urls getUrls() {
        return mUrls;
    }

    public void setUrls(Urls urls) {
        mUrls = urls;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public Long getWidth() {
        return mWidth;
    }

    public void setWidth(Long width) {
        mWidth = width;
    }

}
