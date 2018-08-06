
package com.mindvalleytestapp.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import com.mindvalleytestapp.di.components.ApplicationComponent;
import com.mindvalleytestapp.di.components.DaggerApplicationComponent;
import com.mindvalleytestapp.di.module.ApplicationModule;



public class BaseApplication extends Application {

    private static ApplicationComponent mApplicationComponent;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initializeApplicationComponent();
    }

    private void initializeApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}
