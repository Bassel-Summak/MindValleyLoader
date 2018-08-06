
package com.mindvalleytestapp.base.presenter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import com.mindvalleytestapp.mvp.view.BaseView;
import javax.inject.Inject;
import com.mindvalleytestapp.util.Toasts;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class BasePresenter <V extends BaseView> {

    @Inject protected V mView;

    @Inject
    public Toasts Toasts_Helper;

    @Inject
    public Context context;

    @Inject
    public AppCompatActivity appCompatActivity;


    protected V getView() {
        return mView;
    }

    protected <T> void subscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.newThread())
                .toSingle()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
