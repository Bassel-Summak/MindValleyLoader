package com.mindvalleytestapp.base.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.mindvalleytestapp.di.components.ApplicationComponent;
import com.mindvalleytestapp.di.components.BaseFragmentComponent;
import java.util.List;

public abstract class BaseAdapter_WithFooter<O,L,R,F extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public AppCompatActivity appCompatActivity;

    public Context context;

    public SharedPreferences sharedPreferences;

    public SharedPreferences.Editor editor;

    public Typeface typeface;
    public Typeface typeface_bold;

    public BaseFragmentComponent baseFragmentComponent;

    final private int RECYCLER = 1;

    final private int FOOTER = 2;

    public O Response_Data;

    public List <L> listAdapter;

    public Object adapterPostInfo;
    public F Footerholder;


    public BaseAdapter_WithFooter(BaseFragmentComponent frgComponent, Object adapterPostInfo
            , Object mainAdapterObject, List<L> mainListAdapter) {
        Response_Data = (O) mainAdapterObject;
        listAdapter = mainListAdapter;
        this.adapterPostInfo = adapterPostInfo;
        this.baseFragmentComponent = frgComponent;
        initializeInjectedObjects(frgComponent);
    }

    private void initializeInjectedObjects(BaseFragmentComponent frgComponent)
    {
        appCompatActivity = frgComponent.provideAppCompact();
        context = frgComponent.provideAppCompact();
        sharedPreferences = frgComponent.provideSharedPrefrences();
        typeface = frgComponent.provideTypeFaceNormal();
        typeface_bold = frgComponent.provideTypeFaceBold();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == RECYCLER)
            return getViewHolder_Recycler(parent,viewType);
        else
            return getViewHolder_Footer(parent,viewType);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (position != listAdapter.size())
        {
            R holder = (R)viewHolder;
            onRecyclerAdapterReady(holder,position);
        }
        else
        {
            F holder = (F)viewHolder;
            Footerholder = holder;
            onFooterAdapterReady(holder,position);
        }

    }


    @CallSuper
    protected void onFooterAdapterReady(F holder,int position) {

    }

    @CallSuper
    protected void onRecyclerAdapterReady(R holder, int position) {
    }

    @Override
    public int getItemViewType(int position) {

        if (position != listAdapter.size())
        {
            return RECYCLER;
        }
        else {
            return FOOTER;
        }
    }




    @Override
    public int getItemCount() {
        return listAdapter.size() + 1;
    }

    public abstract RecyclerView.ViewHolder getViewHolder_Recycler(ViewGroup parent,int viewType);

    public abstract RecyclerView.ViewHolder getViewHolder_Footer(ViewGroup parent,int viewType);

    public void removeItem(int position)
    {
        listAdapter.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(Object jsonObject,Object objectNew, List<L> list_new) {

        listAdapter.addAll(list_new);
        Response_Data = (O)objectNew;
        this.adapterPostInfo =jsonObject;
        notifyDataSetChanged();
    }

    public void UpdateItem(int position,L data)
    {
        listAdapter.set(position, data);
        notifyDataSetChanged();
    }

    @CallSuper
    public void stopFooterFromLoading()
    {

    }
}
