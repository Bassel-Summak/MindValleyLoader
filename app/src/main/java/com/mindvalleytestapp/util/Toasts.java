package com.mindvalleytestapp.util;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateOvershootInterpolator;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import javax.inject.Inject;
import javax.inject.Named;
import com.mindvalleytestapp.R;
import com.mindvalleytestapp.base.activity.BaseActivity;
import com.mindvalleytestapp.custom_views.animation_toast.Style;
import com.mindvalleytestapp.custom_views.animation_toast.SuperActivityToast;
import com.mindvalleytestapp.custom_views.modified_toasts.DropDownWarning;
import com.mindvalleytestapp.statics.StaticsNames;

import static com.mindvalleytestapp.statics.StaticRecycledObject.Statichandler;


public class Toasts {

    private static boolean animateDropDown = true;

    public static DropDownWarning dropDownWarning;

    @Inject  AppCompatActivity appCompatActivity;
    @Inject @Named("rootView")  View rootView;

    @Inject
    public Toasts ()
    {
       BaseActivity.getComponent().inject(this);
    }

    public void showToastWithTimer (final String string)
    {
        appCompatActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (animateDropDown)
                {
                    animateDropDown = false;
                    dropDownWarning = new DropDownWarning.Builder(appCompatActivity
                            ,(ViewGroup) rootView)
                            .animationLength(1000)
                            .interpolatorIn(new AnticipateOvershootInterpolator())
                            .interpolatorOut(new AnticipateOvershootInterpolator())
                            .textHeight(140)
                            .message(string)
                            .foregroundColor(0xffffffff)
                            .backgroundColor(ContextCompat.getColor(appCompatActivity, R.color.white_gray))
                            .build();
                            Statichandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.i("showToastTimer ","show");
                            Log.i("isActivated: ",dropDownWarning.isActivated()+"");

                            dropDownWarning.show();

                            Statichandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Log.i("showToastTimer ","hide ");
                                    dropDownWarning.hide();
                                }
                            },4000);

                            Statichandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    animateDropDown = true;
                                }
                            },4500);

                        }
                    },400);
                }
            }
        });
    }

    public void showMaterialToast_Animate_Green(final String string)
    {
        appCompatActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Typeface typeface = Typeface.createFromAsset(appCompatActivity.getAssets(), StaticsNames.AppFontPath);
                SuperActivityToast.create(appCompatActivity, new Style(), Style.TYPE_STANDARD)
                        .setTouchToDismiss(true)
                        .setProgressBarColor(Color.WHITE)
                        .setText(string)
                        .setTypface(typeface)
                        .setDuration(Style.DURATION_SHORT)
                        .setFrame(Style.FRAME_LOLLIPOP)
                        .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_GREEN))
                        .setAnimations(Style.ANIMATIONS_POP).show();
            }
        });
    }

    public static void hideToast()
    {
        if (dropDownWarning != null)
            dropDownWarning.hide();
    }

}
