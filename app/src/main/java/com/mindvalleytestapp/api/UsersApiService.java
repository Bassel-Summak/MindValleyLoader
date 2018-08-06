

package com.mindvalleytestapp.api;

import com.mindvalleytestapp.mvp.model.list.ServerItem;
import com.mindvalleytestapp.util.URLS;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;


public interface UsersApiService {


    @FormUrlEncoded
    @POST(URLS.ServerDataLink)
    Observable<List<ServerItem>> getData(@Field("page")long pageNum);
}