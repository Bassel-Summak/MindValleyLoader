package com.mindvalleytestapp.ui.recycler.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.ablanco.zoomy.Zoomy;
import com.mindvalleytestapp.R;
import com.mindvalleytestapp.base.adapter.BaseAdapter_WithFooter;
import com.mindvalleytestapp.di.components.BaseFragmentComponent;
import com.mindvalleytestapp.mindvalley_loader.MindValleyImageLoader;
import com.mindvalleytestapp.mvp.model.Json_Get;
import com.mindvalleytestapp.mvp.model.list.ServerItem;
import com.mindvalleytestapp.ui.recycler.view_holder.FooterViewHolder;
import com.mindvalleytestapp.ui.recycler.view_holder.User_ViewHolder;
import com.mindvalleytestapp.util.ToolsFunctions;
import java.util.List;


public class Adapter_User extends BaseAdapter_WithFooter<List<ServerItem>,ServerItem,User_ViewHolder,FooterViewHolder> {

    private int lastPosition = -1;
    MindValleyImageLoader imageLoader;
    ToolsFunctions toolsFunctions;
    public Adapter_User(BaseFragmentComponent frgComponent, Object json_post,
                        Object mainAdapterObject, List mainListAdapter) {

        super(frgComponent,json_post,mainAdapterObject, mainListAdapter);
        imageLoader = new MindValleyImageLoader(appCompatActivity);
        toolsFunctions = new ToolsFunctions();
    }

    @Override
    protected void onRecyclerAdapterReady(User_ViewHolder holder, final int position) {
        super.onRecyclerAdapterReady(holder,position);
        setAnimation(holder.mainCard,position);
        InitializeData(holder,position);
    }

    private void InitializeData(final User_ViewHolder holder,final int position) {
        holder.adapterTxtName.setText(listAdapter.get(position).getUser().getName());

        imageLoader.DisplayImage(listAdapter.get(position).getUser().getProfileImage().getMedium(), holder.adapterImagePersonalPicture);
        imageLoader.DisplayImage(listAdapter.get(position).getUrls().getRegular(), holder.adapterImageContent);
        setZoomOnView(holder.adapterImageContent);
        holder.adapterTxtCategories.setText(toolsFunctions.getCategoriesString(listAdapter.get(position).getCategories()));
        holder.adapterTxtDate.setText(toolsFunctions.getDate_YearMonthDay(listAdapter.get(position).getCreatedAt()));
        holder.adapterTxtLikesCount.setText(listAdapter.get(position).getLikes());

    }
    private void setZoomOnView(View view)
    {
        Zoomy.Builder builder = new Zoomy.Builder(appCompatActivity).target(view).enableImmersiveMode(false).animateZooming(true);
        builder.register();
    }

    private void setAnimation(View viewToAnimate, int position)
    {
        if (position > lastPosition)
        {
            Animation animation;

            if (position % 2 ==0)
                animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_left_adapter);
            else
                animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_right_adapter);

            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }


    @Override
    public RecyclerView.ViewHolder getViewHolder_Recycler(ViewGroup parent, int viewType) {
        return new User_ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_user, parent, false));
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder_Footer(ViewGroup parent, int viewType) {
        return new FooterViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.footer_loading, parent, false));
    }

    @Override
    protected void onFooterAdapterReady(FooterViewHolder holder, int position) {
        super.onFooterAdapterReady(holder, position);

        holder.footerProgressbar.setVisibility(View.VISIBLE);
        Json_Get json_post = (Json_Get) adapterPostInfo ;
        json_post.setPageNum(json_post.getPageNum()+1);
        baseFragmentComponent.provideUsersPresenter().getData(json_post,false);
    }

    @Override
    public void onViewDetachedFromWindow(final RecyclerView.ViewHolder holder)
    {
        if (holder instanceof User_ViewHolder)
        ((User_ViewHolder)holder).clearAnimation();
    }

    @Override
    public void stopFooterFromLoading()
    {
        super.stopFooterFromLoading();
        if (Footerholder!=null)
            Footerholder.footerProgressbar.setVisibility(View.GONE);
    }

}
