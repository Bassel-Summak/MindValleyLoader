package com.mindvalleytestapp.custom_views.modified_views;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import com.mindvalleytestapp.statics.StaticsNames;


public class TextViewWithFont extends AppCompatTextView {

    public Typeface typeface;

    public TextViewWithFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        typeface = Typeface.createFromAsset(context.getAssets(), StaticsNames.AppFontPath);
        this.setTypeface(typeface);
    }

    public TextViewWithFont(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setTypeface(typeface);
    }
    public TextViewWithFont(Context context) {
        super(context);
        this.setTypeface(typeface);
    }

}