package com.mindvalleytestapp.mvp.presenter;

import com.mindvalleytestapp.R;
import com.mindvalleytestapp.api.UsersApiService;
import com.mindvalleytestapp.base.presenter.BasePresenter;
import com.mindvalleytestapp.mvp.model.Json_Get;
import com.mindvalleytestapp.mvp.model.list.ServerItem;
import com.mindvalleytestapp.mvp.view.RecyclerLoaderView;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Observer;


public class UsersPresenter extends BasePresenter<RecyclerLoaderView> implements Observer<List<ServerItem>> {

    @Inject protected UsersApiService mApiService;

    private Json_Get json;
    private boolean deleteAdapter;
    private boolean isLoadingAvailable =true;


    @Inject
    public UsersPresenter() {
    }

    public void getData(Json_Get json, boolean  deleteAdapter) {

        if (isLoadingAvailable)
        {
            if (deleteAdapter)
            getView().onLoading();

            isLoadingAvailable = false;
            this.json =json;
            this.deleteAdapter = deleteAdapter;
            Observable<List<ServerItem>> ResponseObservable = mApiService.getData(json.getPageNum());
            subscribe(ResponseObservable, this);
        }
    }

    public void refreshData(Json_Get json, boolean  deleteAdapter) {

        if (isLoadingAvailable)
        {
            isLoadingAvailable = false;
            this.json =json;
            this.deleteAdapter = deleteAdapter;
            Observable<List<ServerItem>> ResponseObservable = mApiService.getData(json.getPageNum());
            subscribe(ResponseObservable, this);
        }
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        e.getMessage();
        isLoadingAvailable = true;
        getView().onConnectionError(deleteAdapter);
        Toasts_Helper.showToastWithTimer(context.getResources().getString(R.string.txt_no_internet_connection));
    }

    @Override
    public void onNext(List<ServerItem> response) {
        isLoadingAvailable = true;

        if (response !=null)
        {
            if (response.size()==0)
                getView().onNoData();
            else
                getView().onRecyclerData(json,deleteAdapter,response.get(0),response);

        }
        else
        {
            Toasts_Helper.showToastWithTimer(context.getResources().getString(R.string.txt_no_internet_connection));
            getView().onRetrying();
        }

    }

}
