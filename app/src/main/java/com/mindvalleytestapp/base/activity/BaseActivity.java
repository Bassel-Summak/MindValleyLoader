
package com.mindvalleytestapp.base.activity;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import javax.inject.Inject;
import com.mindvalleytestapp.R;
import com.mindvalleytestapp.application.BaseApplication;
import com.mindvalleytestapp.custom_views.animation_toast.SuperActivityToast;
import com.mindvalleytestapp.di.components.ApplicationComponent;
import butterknife.ButterKnife;
import com.mindvalleytestapp.di.components.BaseActivityComponent;
import com.mindvalleytestapp.di.components.DaggerBaseActivityComponent;
import com.mindvalleytestapp.di.module.BaseActivityModule;
import com.mindvalleytestapp.util.Toasts;

public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    public SharedPreferences sharedPreferences;

    @Inject
    public SharedPreferences.Editor editor;


    private static BaseActivityComponent BaseActivityComponent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        updatingRecentUi();
        ButterKnife.bind(this);
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager!=null)
        notificationManager.cancelAll();
        onViewReady(savedInstanceState, getIntent());
    }

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {

        resolveDaggerDependency();
        initializeToolBar();
        initializeToolBarClicks();
    }


    protected abstract void initializeToolBar();

    protected void resolveDaggerDependency() {

        Log.i("resolve","activity");

        BaseActivityComponent = DaggerBaseActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .baseActivityModule(new BaseActivityModule(this))
                .build();

        BaseActivityComponent.inject(this);

    }

    protected void updatingRecentUi()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.small_logo_white);
            ActivityManager.TaskDescription taskDescription =
                    new ActivityManager.TaskDescription(getResources().getString(R.string.app_name), bm,
                            ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark));
            setTaskDescription(taskDescription);
        }
    }


    protected ApplicationComponent getApplicationComponent() {
        return BaseApplication.getApplicationComponent();
    }

    protected abstract int getContentView();

    protected abstract void initializeToolBarClicks();


    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toasts.hideToast();
        SuperActivityToast.cancelAllSuperToasts();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public static BaseActivityComponent getComponent()
    {
        return BaseActivityComponent;
    }

}
