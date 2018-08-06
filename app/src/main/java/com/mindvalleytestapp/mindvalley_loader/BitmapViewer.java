package com.mindvalleytestapp.mindvalley_loader;

import android.graphics.Bitmap;

public class BitmapViewer implements Runnable {

    private Bitmap bitmap;
    private ImagesToLoadOnView imagesToLoadOnView;
    private MindValleyImageLoader mindValleyImageLoader;

    public BitmapViewer(Bitmap bitmap, ImagesToLoadOnView imagesToLoadOnView,MindValleyImageLoader mindValleyImageLoader) {
        this.bitmap=bitmap;
        this.imagesToLoadOnView=imagesToLoadOnView;
        this.mindValleyImageLoader = mindValleyImageLoader;
    }

    public void run()
    {
        if(mindValleyImageLoader.imageViewReusedBefore(imagesToLoadOnView))
            return;

        if(bitmap!=null)
            imagesToLoadOnView.getImageView().setImageBitmap(bitmap);
        else
            imagesToLoadOnView.getImageView().setImageResource(mindValleyImageLoader.getDefaultDrawable());
    }

}
