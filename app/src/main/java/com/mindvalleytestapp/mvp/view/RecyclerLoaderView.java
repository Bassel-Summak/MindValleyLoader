package com.mindvalleytestapp.mvp.view;

import java.util.List;


public interface RecyclerLoaderView extends BaseView {

    // OnSuccess
    void onNoData();

    void onLoading();

    void onRetrying();

    void onFailed();

    void onRecyclerData(Object JsonPost,boolean deleteAdapter,Object object, List<?> list_adapter);

    void onConnectionError(boolean deleteAdapter);
}
