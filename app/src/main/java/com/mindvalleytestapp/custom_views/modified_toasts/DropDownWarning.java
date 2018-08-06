package com.mindvalleytestapp.custom_views.modified_toasts;

import android.content.Context;
import android.graphics.Typeface;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import com.mindvalleytestapp.custom_views.modified_views.TextViewWithFont;
import com.mindvalleytestapp.statics.StaticsNames;


public class DropDownWarning extends LinearLayout {
    private TextViewWithFont textView;
    private String warningMessage;
    private int backgroundColor;
    private int foregroundColor;
    private Animation fadeIn;
    private Animation fadeOut;
    private ViewGroup parent;
    private int height;
    private Interpolator interpolatorIn;
    private Interpolator interpolatorOut;
    private int animationLength;
    boolean isVisible = false;
    private Typeface typeface;
    public DropDownWarning(Builder builder) {
        super(builder.context);
        this.warningMessage = builder.warningMessage;
        this.backgroundColor = builder.backgroundColor;
        this.foregroundColor = builder.foregroundColor;
        this.height = builder.height;
        this.interpolatorIn = builder.interpolatorIn;
        this.interpolatorOut = builder.interpolatorOut;
        this.animationLength = builder.animationLength;
        this.parent = builder.parent;
        this.addWarningView();
        this.setUpLayoutParams();
        this.initializeAnimation();
        typeface = Typeface.createFromAsset(builder.context.getAssets(), StaticsNames.AppFontPath);

    }

    private void addWarningView() {
        this.textView = new TextViewWithFont(this.getContext());
        this.textView.setText(this.warningMessage);
        this.textView.setBackgroundColor(this.backgroundColor);
        this.textView.setGravity(17);
        this.textView.setPadding(0, 12, 0, 12);
        this.textView.setVisibility(INVISIBLE);
        this.textView.setTypeface(typeface);
        this.textView.setTextColor(this.foregroundColor);
        LayoutParams l = new LayoutParams(-1, this.height);
        this.textView.setLayoutParams(l);
        this.addView(this.textView);
    }

    private void setUpLayoutParams() {
        LayoutParams l = new LayoutParams(-1, -1);
        this.setLayoutParams(l);
        this.parent.addView(this);
    }

    private void initializeAnimation() {
        this.animationLength = 500;
        this.fadeIn = new TranslateAnimation(0.0F, 0.0F, (float)(-this.height), 0.0F);
        this.fadeIn.setDuration((long)this.animationLength);
        this.fadeOut = new TranslateAnimation(0.0F, 0.0F, 0.0F, (float)(-this.height));
        this.fadeOut.setDuration((long)this.animationLength);
        if(this.interpolatorIn != null) {
            this.fadeIn.setInterpolator(this.interpolatorIn);
        }

        if(this.interpolatorOut != null) {
            this.fadeOut.setInterpolator(this.interpolatorOut);
        }

    }

    public void show() {
        if(!this.isVisible) {
            this.textView.setVisibility(VISIBLE);
            this.textView.startAnimation(this.fadeIn);
            this.isVisible = true;
        }

    }

    public void setOnClickListener(OnClickListener listener) {
        this.textView.setOnClickListener(listener);
    }

    public void hide() {
        if(this.isVisible) {
            this.fadeOut.setAnimationListener(new AnimationListener() {
                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    DropDownWarning.this.textView.setVisibility(GONE);
                }

                public void onAnimationRepeat(Animation animation) {
                }
            });
            this.isVisible = false;
            this.textView.startAnimation(this.fadeOut);
        }
    }

    public static class Builder {
        private Interpolator interpolatorIn;
        private Interpolator interpolatorOut;
        private int animationLength;
        private int height;
        private String warningMessage;
        private Context context;
        private ViewGroup parent;
        private int backgroundColor;
        private int foregroundColor;

        public Builder(Context context, ViewGroup parent) {
            this.context = context;
            this.parent = parent;
            this.warningMessage = "My Message";
            this.height = 60;
            this.animationLength = 500;
            this.interpolatorIn = new LinearInterpolator();
            this.interpolatorOut = new LinearInterpolator();
            this.backgroundColor = -1;
            this.foregroundColor = -16777216;
        }

        public Builder interpolatorIn(Interpolator interpolator) {
            this.interpolatorIn = interpolator;
            return this;
        }

        public Builder interpolatorOut(Interpolator interpolator) {
            this.interpolatorOut = interpolator;
            return this;
        }

        public Builder animationLength(int length) {
            this.animationLength = length;
            return this;
        }

        public Builder textHeight(int height) {
            this.height = height;
            return this;
        }

        public Builder message(String message) {
            this.warningMessage = message;
            return this;
        }

        public Builder foregroundColor(int color) {
            this.foregroundColor = color;
            return this;
        }

        public Builder backgroundColor(int color) {
            this.backgroundColor = color;
            return this;
        }

        public DropDownWarning build() {
            return new DropDownWarning(this);
        }
    }
}
