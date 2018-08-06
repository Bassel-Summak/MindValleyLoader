package com.mindvalleytestapp.di.components;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import com.mindvalleytestapp.application.BaseApplication;
import com.mindvalleytestapp.di.module.ApplicationModule;
import javax.inject.Named;
import javax.inject.Singleton;
import dagger.Component;
import retrofit2.Retrofit;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Retrofit provideRetrofit();

    Context provideContext();

    @Named("normal") Typeface  provideTypeFaceNormal();

    @Named("bold") Typeface  provideTypeFaceBold();

    SharedPreferences provideSharedPreferences();

    SharedPreferences.Editor provideEditor();


    void inject(BaseApplication baseApplication);

}
