package com.mindvalleytestapp.custom_views.modified_views;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.mindvalleytestapp.statics.StaticsNames;


public class TextViewWithFontBold extends AppCompatTextView {

    public Typeface typeface_Bold;

    public TextViewWithFontBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        typeface_Bold = Typeface.createFromAsset(context.getAssets(), StaticsNames.AppFontPath_bold);
        this.setTypeface(typeface_Bold);
    }

    public TextViewWithFontBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setTypeface(typeface_Bold);
    }

    public TextViewWithFontBold(Context context) {
        super(context);
        this.setTypeface(typeface_Bold);
    }

}