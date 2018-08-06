package com.mindvalleytestapp.di.components;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import com.mindvalleytestapp.base.fragment.BaseFragment;
import com.mindvalleytestapp.di.module.FragmentRecyclerModule;
import com.mindvalleytestapp.di.scope.PerFragment;
import com.mindvalleytestapp.mvp.presenter.UsersPresenter;
import com.mindvalleytestapp.ui.fragment.Fragment_Home;
import javax.inject.Named;
import dagger.Component;


@PerFragment
@Component(modules = FragmentRecyclerModule.class,dependencies = BaseActivityComponent.class)
public interface BaseFragmentComponent {

    AppCompatActivity provideAppCompact();
    UsersPresenter provideUsersPresenter();
    SharedPreferences provideSharedPrefrences();
    @Named("normal") Typeface provideTypeFaceNormal();
    @Named("bold") Typeface  provideTypeFaceBold();

    void inject(BaseFragment baseFragment);
    void inject(Fragment_Home fragment_home);
}