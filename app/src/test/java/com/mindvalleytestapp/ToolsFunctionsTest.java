package com.mindvalleytestapp;

import android.support.v7.app.AppCompatActivity;

import com.mindvalleytestapp.application.BaseApplication;
import com.mindvalleytestapp.base.activity.BaseActivity;
import com.mindvalleytestapp.di.components.ApplicationComponent;
import com.mindvalleytestapp.di.components.BaseActivityComponent;
import com.mindvalleytestapp.di.components.BaseFragmentComponent;
import com.mindvalleytestapp.di.components.DaggerBaseFragmentComponent;
import com.mindvalleytestapp.di.module.ApplicationModule;
import com.mindvalleytestapp.di.module.BaseActivityModule;
import com.mindvalleytestapp.di.module.FragmentRecyclerModule;
import com.mindvalleytestapp.mvp.model.Json_Get;
import com.mindvalleytestapp.mvp.model.extra.Category;
import com.mindvalleytestapp.mvp.model.list.ServerItem;
import com.mindvalleytestapp.mvp.view.RecyclerLoaderView;
import com.mindvalleytestapp.ui.recycler.adapter.Adapter_User;
import com.mindvalleytestapp.util.ToolsFunctions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import it.cosenonjaviste.daggermock.DaggerMockRule;
import it.cosenonjaviste.daggermock.InjectFromComponent;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.plugins.RxJavaSchedulersHook;
import rx.schedulers.Schedulers;

public class ToolsFunctionsTest {


    List<Category> categoryList;
    ToolsFunctions toolsFunctions;

    @Before
    public void setUp(){

        toolsFunctions = new ToolsFunctions();

        Category category1 = new Category();
        category1.setTitle("Sport");
        Category category2 = new Category();
        category2.setTitle("People");
        Category category3 = new Category();
        category3.setTitle("Nature");
        categoryList = new ArrayList<>();
        categoryList.add(category1);
        categoryList.add(category2);
        categoryList.add(category3);
    }

    @Test
    public void getCategoriesString() {
        Assert.assertEquals("Sport People Nature ",toolsFunctions.getCategoriesString(categoryList));
    }

    @Test
    public void getDate_YearMonthDay() {
        Assert.assertEquals("2016-05-29",toolsFunctions.getDate_YearMonthDay("2016-05-29T15:42:02-04:00"));
    }

}
