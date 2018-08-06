
package com.mindvalleytestapp.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import java.util.concurrent.TimeUnit;
import javax.inject.Named;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import com.google.gson.Gson;
import com.mindvalleytestapp.statics.StaticsNames;
import com.mindvalleytestapp.util.URLS;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class ApplicationModule {

    private Context mContext;

    public ApplicationModule(Context context) {
        mContext = context;
    }

    @Singleton
    @Provides
    protected GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    @Named("ok-1")
    protected OkHttpClient provideOkHttpClient1(HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
    }

    @Singleton
    @Provides
    @Named("ok-2")
    protected OkHttpClient provideOkHttpClient2(HttpLoggingInterceptor interceptor) {

        return new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
    }

    @Singleton
    @Provides
    protected HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return interceptor;
    }

    @Singleton
    @Provides
    protected RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }

    @Singleton
    @Provides
    protected Retrofit provideRetrofit(@Named("ok-1") OkHttpClient client, GsonConverterFactory converterFactory,
                             RxJavaCallAdapterFactory adapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(URLS.route)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(adapterFactory)
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    protected Context provideContext() {
        return mContext;
    }


    @Provides
    @Singleton
    protected SharedPreferences provideSharedPreferences(Context context) {

        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    protected SharedPreferences.Editor provideEditor(SharedPreferences sharedPreferences) {
        return sharedPreferences.edit();
    }

    @Provides
    @Singleton
    protected Gson provideGson()
    {
        return new Gson();
    }

    @Provides
    @Singleton
    @Named("normal")
    protected Typeface provideTypeface(Context context)
    {
        return Typeface.createFromAsset(context.getAssets(), StaticsNames.AppFontPath);
    }


    @Provides
    @Singleton
    @Named("bold")
    protected Typeface provideTypefaceBold(Context context)
    {
        return Typeface.createFromAsset(context.getAssets(), StaticsNames.AppFontPath_bold);
    }
}
