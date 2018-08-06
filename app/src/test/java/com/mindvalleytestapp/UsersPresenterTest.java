package com.mindvalleytestapp;


import android.content.ClipData;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;

import com.mindvalleytestapp.api.UsersApiService;
import com.mindvalleytestapp.mvp.model.Json_Get;
import com.mindvalleytestapp.mvp.model.extra.Category;
import com.mindvalleytestapp.mvp.model.list.ServerItem;
import com.mindvalleytestapp.mvp.presenter.UsersPresenter;
import com.mindvalleytestapp.mvp.view.RecyclerLoaderView;
import com.mindvalleytestapp.util.Toasts;
import com.mindvalleytestapp.util.ToolsFunctions;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.plugins.RxJavaSchedulersHook;
import rx.schedulers.Schedulers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Observable.class, AndroidSchedulers.class, Looper.class})
public class UsersPresenterTest {

    @InjectMocks private UsersPresenter presenter;
    @Mock private UsersApiService mApiService;
    @Mock private RecyclerLoaderView mView;
    @Mock private Observable<List<ServerItem>> mObservable;
    @Mock private Json_Get json;


    @Captor private ArgumentCaptor<Subscriber<List<ServerItem>>> captor;


    private final RxJavaSchedulersHook mRxJavaSchedulersHook = new RxJavaSchedulersHook() {
        @Override
        public Scheduler getIOScheduler() {
            return Schedulers.immediate();
        }

        @Override
        public Scheduler getNewThreadScheduler() {
            return Schedulers.immediate();
        }
    };

    @Before
    public void setUp(){
        initMocks(this);
    }

    @Test
    public void getData() throws Exception {
        PowerMockito.mockStatic(Looper.class);
            when(AndroidSchedulers.mainThread()).thenReturn(mRxJavaSchedulersHook.getComputationScheduler());
            when(mApiService.getData(json.getPageNum())).thenReturn(mObservable);
            presenter.getData(json,true);
            verify(mView, atLeastOnce()).onLoading();

    }



    @Test
    public void onNext() throws Exception {


        List<ServerItem> response = mock(ArrayList.class);

        when(response.get(0)).thenReturn(new ServerItem());
        presenter.onNext(response);

        if (response.size()==0)
            verify(mView, times(1)).onNoData();
        else
            verify(mView, times(1)).onRecyclerData(json,true,response.get(0),response);

    }

}
