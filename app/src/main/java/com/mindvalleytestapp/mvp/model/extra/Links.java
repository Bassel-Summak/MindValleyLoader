
package com.mindvalleytestapp.mvp.model.extra;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Links {

    @SerializedName("download")
    private String mDownload;
    @SerializedName("html")
    private String mHtml;
    @SerializedName("likes")
    private String mLikes;
    @SerializedName("photos")
    private String mPhotos;
    @SerializedName("self")
    private String mSelf;

    public String getDownload() {
        return mDownload;
    }

    public void setDownload(String download) {
        mDownload = download;
    }

    public String getHtml() {
        return mHtml;
    }

    public void setHtml(String html) {
        mHtml = html;
    }

    public String getLikes() {
        return mLikes;
    }

    public void setLikes(String likes) {
        mLikes = likes;
    }

    public String getPhotos() {
        return mPhotos;
    }

    public void setPhotos(String photos) {
        mPhotos = photos;
    }

    public String getSelf() {
        return mSelf;
    }

    public void setSelf(String self) {
        mSelf = self;
    }

}
