package com.mindvalleytestapp.mindvalley_loader;

import java.io.File;
import android.content.Context;

public class FileCache {

    private File cacheDir;

    public FileCache(Context context){

        cacheDir=context.getCacheDir();

        if(!cacheDir.exists()){
            cacheDir.mkdirs();
        }
    }

    public File getCachedFile(String urlFile){
        return new File(cacheDir, String.valueOf(urlFile.hashCode()));
    }

    public void clearCachedMemory(){

        File[] listFiles=cacheDir.listFiles();
        if(listFiles==null)
            return;

        for(File file:listFiles)
            file.delete();
    }

}
