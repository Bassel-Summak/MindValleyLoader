package com.mindvalleytestapp.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;
import com.mindvalleytestapp.R;
import com.mindvalleytestapp.util.URLS;
import com.mindvalleytestapp.base.activity.BaseActivity;
import com.mindvalleytestapp.custom_views.modified_views.TextViewWithFontBold;
import com.mindvalleytestapp.statics.Fragment_Helper;
import com.mindvalleytestapp.ui.fragment.Fragment_Home;
import butterknife.BindView;
import butterknife.OnClick;

public class MainActivityApp extends BaseActivity {


    @BindView(R.id.toolbar_txt_center) public TextViewWithFontBold toolbar_txtCenter;
    @BindView(R.id.toolbar_main) public Toolbar toolbar_main;


    public Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        showSplash();
    }

    @Override
    protected void initializeToolBar() {
        toolbar_main.setVisibility(View.GONE);
    }

    @OnClick(R.id.toolbar_left_logo_frame)
    protected void MindValleyLogoClick(){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(URLS.MindValleyWebsite));
        startActivity(i);
    }

    @OnClick(R.id.toolbar_frame_center)
    protected void ToolbarTextCenterClick(){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(URLS.GithubMindValleyTestAppLink));
        startActivity(i);
    }

    private void showSplash()
    {
        findViewById(R.id.splash_ui).setVisibility(View.VISIBLE);
        findViewById(R.id.image_splash).setVisibility(View.VISIBLE);
        initializeFragment(3800);

        handler.post(new Runnable() {
            @Override
            public void run() {
                ViewAnimator
                        .animate(findViewById(R.id.image_splash))
                        .fadeIn()
                        .duration(600)
                        .andAnimate(findViewById(R.id.image_splash))
                        .scale(1f, 0.2f ,1f)
                        .duration(1500)
                        .accelerate()
                        .thenAnimate(findViewById(R.id.image_splash))
                        .startDelay(300)
                        .pulse()
                        .duration(800)
                        .accelerate()
                        .thenAnimate(findViewById(R.id.image_splash))
                        .startDelay(300)
                        .scale(1f, 0.1f)
                        .andAnimate(findViewById(R.id.splash_ui))
                        .fadeOut()
                        .duration(800)
                        .accelerate()
                        .onStop(new AnimationListener.Stop() {
                            @Override
                            public void onStop() {
                            }
                        })
                        .start();
            }
        });
    }

    private void initializeFragment(int delay) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Fragment_Helper.FragmentReplace(new Fragment_Home(), MainActivityApp.this, Fragment_Helper.Animation_FADE
                        , false, false);
            }
        },delay);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initializeToolBarClicks() {

    }


    @Override
    public void onBackPressed() {

            super.onBackPressed();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
