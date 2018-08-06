package com.mindvalleytestapp.mindvalley_loader;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import android.graphics.Bitmap;

public class CachedMemory {

    private long bitmap_size =0;
    private long Memory_limit =100000000;
    private Map<String, Bitmap> cache_list = Collections.synchronizedMap(new LinkedHashMap<String, Bitmap>(10,1.5f,true));

    public CachedMemory(){
        setMemory_limit(Runtime.getRuntime().maxMemory()/4);
    }

    public void setMemory_limit(long new_limit){
        Memory_limit =new_limit;
    }

    public Bitmap get(String id){
        try{
            if(!cache_list.containsKey(id))
                return null;

            return cache_list.get(id);

        }catch(NullPointerException ex){
            ex.printStackTrace();
            return null;
        }
    }

    public void put(String id, Bitmap bitmap){
        try{
            if(cache_list.containsKey(id))
                bitmap_size -=getSizeInBytes(cache_list.get(id));
            cache_list.put(id, bitmap);
            bitmap_size +=getSizeInBytes(bitmap);
            checkIfSizeIsValid();
        }catch(Throwable th){
            th.printStackTrace();
        }
    }

    private void checkIfSizeIsValid() {

        if(bitmap_size > Memory_limit){

            Iterator<Entry<String, Bitmap>> iterator_list= cache_list.entrySet().iterator();

            while(iterator_list.hasNext()){
                Entry<String, Bitmap> entry_list=iterator_list.next();
                bitmap_size -=getSizeInBytes(entry_list.getValue());
                iterator_list.remove();
                if(bitmap_size <= Memory_limit)
                    break;
            }
        }
    }

    public void clear() {
        try{
            cache_list.clear();
            bitmap_size =0;
        }catch(NullPointerException ex){
            ex.printStackTrace();
        }
    }

    private long getSizeInBytes(Bitmap bitmap) {
        if(bitmap==null)
            return 0;
        return  bitmap.getHeight() * bitmap.getRowBytes();
    }
}