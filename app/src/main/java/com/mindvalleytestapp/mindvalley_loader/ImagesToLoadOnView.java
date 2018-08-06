package com.mindvalleytestapp.mindvalley_loader;

import android.widget.ImageView;

public class ImagesToLoadOnView {

    private String url;
    private ImageView imageView;

    public ImagesToLoadOnView(String url, ImageView imageView){
        this.url=url;
        this.imageView=imageView;
    }

    public String getUrl() {
        return url;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
