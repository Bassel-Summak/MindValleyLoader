package com.mindvalleytestapp.mindvalley_loader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.mindvalleytestapp.R;

public class MindValleyImageLoader {

    private Map<ImageView, String> imageViews = Collections.synchronizedMap(new WeakHashMap<ImageView, String>());
    private CachedMemory memoryCache = new CachedMemory();
    private int defaultDrawable = R.color.thumbnail_gray;
    private FileCache fileCache;
    private ExecutorService executorService;
    private Tools tools;

    Handler handler = new Handler();

    public MindValleyImageLoader(Context context){

        tools = new Tools();
        fileCache = new FileCache(context);
        executorService=Executors.newFixedThreadPool(5);

    }

    public int getDefaultDrawable() {
        return defaultDrawable;
    }

    public void setDefaultDrawable(int defaultDrawable) {
        this.defaultDrawable = defaultDrawable;
    }

    public void DisplayImage(String url, ImageView imageView)
    {
        imageViews.put(imageView, url);
        Bitmap bitmap = memoryCache.get(url);

        if(bitmap!=null){
            imageView.setImageBitmap(bitmap);
        }
        else
        {
            listThePhotoWithView(url, imageView);
            imageView.setImageResource(getDefaultDrawable());
        }
    }

    private void listThePhotoWithView(String url, ImageView imageView)
    {
        ImagesToLoadOnView p = new ImagesToLoadOnView(url, imageView);
        executorService.submit(new PictureLoaderService(p));
    }

    public void setCacheMemory_limit(long new_limit)
    {
        memoryCache.setMemory_limit(new_limit);
    }


    class PictureLoaderService implements Runnable {
        ImagesToLoadOnView imagesToLoadOnView;

        PictureLoaderService(ImagesToLoadOnView imagesToLoadOnView){
            this.imagesToLoadOnView=imagesToLoadOnView;
        }

        @Override
        public void run() {
            try{
                if(imageViewReusedBefore(imagesToLoadOnView))
                    return;
                Bitmap bitmap = getBitmapFromUrl(imagesToLoadOnView.getUrl());

                memoryCache.put(imagesToLoadOnView.getUrl(), bitmap);

                if(imageViewReusedBefore(imagesToLoadOnView))
                    return;

                BitmapViewer bd=new BitmapViewer(bitmap, imagesToLoadOnView,MindValleyImageLoader.this);
                handler.post(bd);

            }catch(Throwable th){
                th.printStackTrace();
            }
        }
    }

    private Bitmap getBitmapFromUrl(String url)
    {
        File CachedFile =fileCache.getCachedFile(url);
        Bitmap b = tools.decodeAndReadTheFile(CachedFile);
        if(b!=null)
            return b;

        InputStream inputStream = null;
        try {
            Bitmap bitmap=null;
            URL imageUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)imageUrl.openConnection();
            conn.setConnectTimeout(35000);
            conn.setReadTimeout(35000);
            conn.setInstanceFollowRedirects(true);
            inputStream =conn.getInputStream();
            OutputStream outputStream = new FileOutputStream(CachedFile);
            tools.CopyFromStream(inputStream, outputStream);
            bitmap = tools.decodeAndReadTheFile(CachedFile);
            outputStream.close();
            conn.disconnect();
            return bitmap;

        } catch (Throwable throwable){

            if(throwable instanceof OutOfMemoryError) {
                try {
                    if (inputStream !=null)
                        return BitmapFactory.decodeStream(inputStream);
                }
                catch(Throwable throwable_decodeException){

                }
            }

            return null;
        }
    }


    public boolean imageViewReusedBefore(ImagesToLoadOnView imagesToLoadOnView){

        String image_tag=imageViews.get(imagesToLoadOnView.getImageView());
        if(image_tag==null || !image_tag.equals(imagesToLoadOnView.getUrl()))
            return true;
        return false;
    }


    public void clearAllCache() {
        memoryCache.clear();
        fileCache.clearCachedMemory();
    }

}