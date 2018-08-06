package com.mindvalleytestapp.di.components;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import dagger.Component;
import retrofit2.Retrofit;
import com.mindvalleytestapp.base.activity.BaseActivity;
import com.mindvalleytestapp.base.fragment.BaseFragment;
import com.mindvalleytestapp.di.module.BaseActivityModule;
import com.mindvalleytestapp.di.scope.PerActivity;
import com.mindvalleytestapp.util.Toasts;
import javax.inject.Named;


@PerActivity
@Component(modules = BaseActivityModule.class, dependencies = {ApplicationComponent.class})
public interface BaseActivityComponent {

    AppCompatActivity provideAppCompact();
    Retrofit provideRetrofit();

    Context provideContext();

    SharedPreferences provideSharedPreferences();

    SharedPreferences.Editor provideEditor();

    @Named("rootView") View provideRootView();


    @Named("normal")
    Typeface provideTypeFaceNormal();

    @Named("bold") Typeface  provideTypeFaceBold();

    Toasts provideToasts();

    void inject(BaseActivity baseActivity);
    void inject(BaseFragment baseFragment);
    void inject(Toasts toasts);

}