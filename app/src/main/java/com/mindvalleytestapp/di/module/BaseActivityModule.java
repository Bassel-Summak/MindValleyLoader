package com.mindvalleytestapp.di.module;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import javax.inject.Named;
import dagger.Module;
import dagger.Provides;
import com.mindvalleytestapp.R;
import com.mindvalleytestapp.di.scope.PerActivity;


@Module
public class BaseActivityModule {

    private AppCompatActivity appCompatActivity;

    public BaseActivityModule(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Provides
    @PerActivity
    protected AppCompatActivity provideAppCompatActivity() {
        return appCompatActivity;
    }

    @Provides
    @PerActivity
    @Named("rootView")
    protected View provideRootView(AppCompatActivity appCompatActivity)
    {
        return appCompatActivity.findViewById(R.id.MainUI);
    }

}
