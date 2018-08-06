package com.mindvalleytestapp.ui.recycler.view_holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.mindvalleytestapp.R;
import com.mindvalleytestapp.custom_views.modified_views.TextViewWithFont;
import com.mindvalleytestapp.custom_views.modified_views.TextViewWithFontBold;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class User_ViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.adapter_image_personal_picture)
    public CircleImageView adapterImagePersonalPicture;
    @BindView(R.id.adapter_linear_personal_picture)
    public LinearLayout adapterLinearPersonalPicture;
    @BindView(R.id.adapter_txt_name)
    public TextViewWithFontBold adapterTxtName;
    @BindView(R.id.adapter_txt_categories)
    public TextViewWithFont adapterTxtCategories;
    @BindView(R.id.adapter_txt_likes_count)
    public TextViewWithFont adapterTxtLikesCount;
    @BindView(R.id.linear_likes_details)
    public LinearLayout linearLikesDetails;
    @BindView(R.id.adapter_image_content)
    public ImageView adapterImageContent;
    @BindView(R.id.adapter_txt_date)
    public TextViewWithFont adapterTxtDate;
    @BindView(R.id.main_card)
    public CardView mainCard;

    public User_ViewHolder(View itemView) {

        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void clearAnimation()
    {
        mainCard.clearAnimation();
    }


}
