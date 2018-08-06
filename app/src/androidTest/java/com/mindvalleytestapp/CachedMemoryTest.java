package com.mindvalleytestapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.mindvalleytestapp.mindvalley_loader.CachedMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class CachedMemoryTest {

    private CachedMemory cachedMemory;
    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private Bitmap bitmap3;

    @Before
    public void setUp() throws Exception{
        bitmap1=Bitmap.createBitmap(500,500, Bitmap.Config.ARGB_8888);
        bitmap2=Bitmap.createBitmap(400,350, Bitmap.Config.ARGB_8888);
        bitmap3=Bitmap.createBitmap(200,100, Bitmap.Config.ARGB_8888);
        cachedMemory = new CachedMemory();
    }

    @Test
    public void saveCachedBitmapTest() {
        cachedMemory.put("abc.png",bitmap1);
        cachedMemory.put("efg.png",bitmap2);
        cachedMemory.put("hij.png",bitmap3);
        Assert.assertEquals(bitmap2,cachedMemory.get("efg.png"));
    }

    @Test
    public void clearMemoryTest() {
        cachedMemory.put("first.png",bitmap1);
        cachedMemory.put("second.png",bitmap2);
        cachedMemory.put("third.png",bitmap3);
        cachedMemory.clear();
        Assert.assertEquals(null,cachedMemory.get("second.png"));
    }
}
