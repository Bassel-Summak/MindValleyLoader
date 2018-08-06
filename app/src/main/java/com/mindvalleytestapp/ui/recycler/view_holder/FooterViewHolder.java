package com.mindvalleytestapp.ui.recycler.view_holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import com.mindvalleytestapp.R;
import butterknife.BindView;
import butterknife.ButterKnife;


public class FooterViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.footer_progressbar)
    public FrameLayout footerProgressbar;

    public FooterViewHolder(View itemView) {

        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}
