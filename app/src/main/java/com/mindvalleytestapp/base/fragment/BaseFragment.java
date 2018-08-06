
package com.mindvalleytestapp.base.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.io.Serializable;
import javax.inject.Inject;
import javax.inject.Named;
import butterknife.ButterKnife;
import com.mindvalleytestapp.base.activity.BaseActivity;
import com.mindvalleytestapp.ui.activity.MainActivityApp;
import com.mindvalleytestapp.util.Toasts;


public abstract class BaseFragment extends Fragment  {

    @Inject
    public SharedPreferences sharedPreferences;

    @Inject
    public SharedPreferences.Editor editor;

    @Inject
    public AppCompatActivity appCompatActivity;

    @Inject
    public Context context;

    @Inject
    public Toasts Toasts_Helper;

    @Inject
    @Named("normal")
    public Typeface typeface;

    @Inject
    @Named("bold")
    public Typeface typeface_bold;

    private View view;
    private String TAG;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view =  inflater.inflate(getContentView(), container, false);
        ButterKnife.bind(this,view);
        Serializable serializable = null;
        Bundle args = getArguments();
        TAG = getFragmentName();
        if (args!=null)
         serializable = args.getSerializable(getFragmentName());

        onViewReady(savedInstanceState, serializable);

        initializeViews();
        return view;
    }



    @CallSuper
    protected void onViewReady(Bundle savedInstanceState,Serializable serializable) {

        resolveDaggerDependency();
        initializeToolbar();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @CallSuper
    protected void resolveDaggerDependency(){

        if (isAdded())
            BaseActivity.getComponent().inject(this);
    }

    public MainActivityApp getAppMainActivity() {
        return (MainActivityApp)appCompatActivity;
    }
    @Override
    public void onResume() {
        initializeToolbar();
        super.onResume();
    }


    protected abstract int getContentView();
    protected abstract String getFragmentName();
    protected abstract void initializeToolbar();
    protected abstract void initializeViews();

}
