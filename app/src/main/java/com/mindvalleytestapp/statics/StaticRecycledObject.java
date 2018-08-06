package com.mindvalleytestapp.statics;

import android.os.Handler;
import android.os.Looper;

public class StaticRecycledObject {

    public final static Handler Statichandler = new Handler(Looper.getMainLooper());
}
