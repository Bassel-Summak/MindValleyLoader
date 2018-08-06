package com.mindvalleytestapp.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.mindvalleytestapp.R;
import com.mindvalleytestapp.base.activity.BaseActivity;
import com.mindvalleytestapp.base.adapter.BaseAdapter_WithFooter;
import com.mindvalleytestapp.base.fragment.BaseFragment;
import com.mindvalleytestapp.di.components.BaseFragmentComponent;
import com.mindvalleytestapp.di.components.DaggerBaseFragmentComponent;
import com.mindvalleytestapp.di.module.FragmentRecyclerModule;
import com.mindvalleytestapp.mvp.model.Json_Get;
import com.mindvalleytestapp.mvp.presenter.UsersPresenter;
import com.mindvalleytestapp.mvp.view.RecyclerLoaderView;
import com.mindvalleytestapp.ui.recycler.adapter.Adapter_User;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;

import static android.view.View.VISIBLE;

public class Fragment_Home extends BaseFragment implements RecyclerLoaderView {

    @BindView(R.id.emptyView_nodata_content) protected LinearLayout linearLayout_noData;
    @BindView(R.id.emptyView_progressbar_content) protected CircularProgressView circularProgressView;
    @BindView(R.id.emptyView_retrying_content) protected LinearLayout linearLayout_retrying;
    @BindView(R.id.frg_recycler) protected RecyclerView recyclerView;
    @BindView(R.id.frg_swipe_torefresh) protected SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.floating_button) protected FloatingActionButton floatingActionButton;



    @Inject
    UsersPresenter presenter = new UsersPresenter();


    BaseFragmentComponent baseFragmentComponent;
    private boolean doShowAndHideAnimation = true;
    Handler handler = new Handler(Looper.getMainLooper());

    Json_Get json_get;
    int RecyclerFirstLoad =0;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Serializable serializable) {
        super.onViewReady(savedInstanceState, serializable);

        initializeFloatbutton();
        RecyclerFirstLoad = 1000;
        json_get  = new Json_Get();
        json_get.setPageNum(1);
        presenter.getData(json_get,true);
    }

    private void initializeFloatbutton() {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // recyclerView pass 3000 from the offset - show Float button
                if (recyclerView.computeVerticalScrollOffset()>3000)
                {
                        if (floatingActionButton.getVisibility()==View.INVISIBLE)
                        {
                        handler.postDelayed((new Runnable() {
                            @Override
                            public void run() {
                                if (doShowAndHideAnimation)
                                {
                                    doShowAndHideAnimation = false;
                                    floatingActionButton.setVisibility(View.VISIBLE);
                                    YoYo.with(Techniques.SlideInUp).duration(350).interpolate(new AccelerateDecelerateInterpolator())
                                            .playOn(floatingActionButton);

                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            doShowAndHideAnimation = true;
                                        }
                                    },500);
                                }

                            }
                        }), 200);
                    }

                }

                // recyclerView is less than 3000 from the offset - Hide Float button
                else
                {

                    if (floatingActionButton.getVisibility()==View.VISIBLE)
                    {
                        if (doShowAndHideAnimation)
                        {
                            doShowAndHideAnimation =false;
                            handler.postDelayed((new Runnable() {
                                @Override
                                public void run() {
                                    floatingActionButton.setVisibility(View.VISIBLE);
                                    YoYo.with(Techniques.SlideOutDown).duration(350).interpolate(new AccelerateDecelerateInterpolator())
                                            .playOn(floatingActionButton);

                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            doShowAndHideAnimation = true;
                                            floatingActionButton.setVisibility(View.INVISIBLE);
                                        }
                                    },500);
                                }
                            }), 200);
                        }

                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }
        });

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_home;
    }

    @Override
    protected String getFragmentName() {
        return getClass().getName();
    }

    @Override
    protected void initializeToolbar() {
      getAppMainActivity().toolbar_txtCenter.setText(getResources().getString(R.string.toolbar_txt_center_app_details));
      getAppMainActivity().toolbar_main.setVisibility(VISIBLE);

    }

    @Override
    public void onNoData() {
        swipeRefreshLayout.setRefreshing(false);
        setViewsVisibility(View.INVISIBLE,View.GONE,View.GONE,View.VISIBLE);
    }

    @Override
    public void onLoading() {
        setViewsVisibility(View.VISIBLE,View.INVISIBLE,View.INVISIBLE,View.INVISIBLE);
    }

    @Override
    public void onRetrying() {
        swipeRefreshLayout.setRefreshing(false);
        setViewsVisibility(View.INVISIBLE,View.VISIBLE,View.INVISIBLE,View.INVISIBLE);
    }

    @Override
    public void onFailed() {
    }

    @Override
    public void onRecyclerData(final Object jsonPost,final boolean deleteAdapter,final Object response,final List<?> list_adapter) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (swipeRefreshLayout.isRefreshing()){
                    Toasts_Helper.showMaterialToast_Animate_Green(getResources().getString(R.string.txt_no_list_has_updated_successfully));
                    swipeRefreshLayout.setRefreshing(false);
                }
                setViewsVisibility(View.INVISIBLE,View.INVISIBLE,View.VISIBLE,View.INVISIBLE);
                InitializeRecycler(jsonPost,deleteAdapter,response,list_adapter);
                RecyclerFirstLoad =0;
            }
        }, RecyclerFirstLoad);
    }

    @Override
    protected void resolveDaggerDependency() {
        super.resolveDaggerDependency();

        baseFragmentComponent = DaggerBaseFragmentComponent.builder()
                .baseActivityComponent(BaseActivity.getComponent())
                .fragmentRecyclerModule(new FragmentRecyclerModule(this))
                .build();
        baseFragmentComponent.inject(this);

    }

    @Override
    public void onConnectionError(boolean deleteAdapter) {

        if (recyclerView.getVisibility()!=VISIBLE)
            onRetrying();

        else if (deleteAdapter) {
            swipeRefreshLayout.setRefreshing(false);
        }

        else {
            ((BaseAdapter_WithFooter)recyclerView.getAdapter()).stopFooterFromLoading();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void InitializeRecycler(Object json_post,boolean deleteAdapter,Object data, List list_adapter)
    {
        {
            Adapter_User adapter;

            if (deleteAdapter)
                recyclerView.setAdapter(null);

            if (recyclerView.getAdapter() == null)
            {
                adapter = new Adapter_User(baseFragmentComponent,json_post,data,list_adapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
                recyclerView.scrollToPosition(0);
                Log.i("recycler ","null");
            }
            else
            {
                adapter = (Adapter_User)recyclerView.getAdapter();
                adapter.addItem(json_post,data,list_adapter);
            }
            // isLoadingAvailable = true;
        }
    }

    private void setViewsVisibility(int circularProgressView_Visibility, int linearLayout_retrying_Visibility
            ,int recyclerView_Visibility, int linearLayout_noData_Visibility){

        recyclerView.setVisibility(recyclerView_Visibility);
        swipeRefreshLayout.setVisibility(recyclerView_Visibility);
        circularProgressView.setVisibility(circularProgressView_Visibility);
        linearLayout_retrying.setVisibility(linearLayout_retrying_Visibility);
        linearLayout_noData.setVisibility(linearLayout_noData_Visibility);
    }

    @Override
    protected void initializeViews() {

        swipeRefreshLayout.setColorSchemeResources(R.color.blue, R.color.yellow, R.color.green);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                json_get.setPageNum(1);
                presenter.refreshData(json_get,true);
            }
        });
    }

    @OnClick(R.id.floating_button)
    protected void floatingButtonClick(){
        recyclerView.smoothScrollToPosition(0);
    }

    @OnClick(R.id.emptyView_retrying_content)
    protected void retryingButtonClick(){
        json_get.setPageNum(1);
        onLoading();
        presenter.getData(json_get,true);
    }

}