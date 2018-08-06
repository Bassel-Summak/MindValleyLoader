
package com.mindvalleytestapp.mvp.model.extra;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Category {

    @SerializedName("id")
    private Long mId;
    @SerializedName("links")
    private Links mLinks;
    @SerializedName("photo_count")
    private Long mPhotoCount;
    @SerializedName("title")
    private String mTitle;

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Links getLinks() {
        return mLinks;
    }

    public void setLinks(Links links) {
        mLinks = links;
    }

    public Long getPhotoCount() {
        return mPhotoCount;
    }

    public void setPhotoCount(Long photoCount) {
        mPhotoCount = photoCount;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

}
