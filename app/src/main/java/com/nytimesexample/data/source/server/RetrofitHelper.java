package com.nytimesexample.data.source.server;

import android.util.Log;

import com.nytimesexample.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * <p>
 * Retrofit helper class
 */
@SuppressWarnings("unused")
final public class RetrofitHelper {

    private static RetrofitHelper helper;
    private NYAPIService mService;

    private static String API_HOST = "http://api.nytimes.com/svc";

    public static String getHost() {
        return API_HOST;
    }

    private RetrofitHelper() {
        init();
    }

    public static synchronized void initRetrofit() {
        if (helper != null) return;
        helper = new RetrofitHelper();
    }

    public static void reset() {
        helper = null;
        initRetrofit();
    }

    private void init() {
        if (mService != null) return;

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
//                .addInterceptor(new RequestInterceptor())
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES);


        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        OkHttpClient httpClient = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient)
                .baseUrl(API_HOST + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mService = retrofit.create(NYAPIService.class);

        Log.i(getClass().getName(), "Retrofit initialized");
    }

    public static NYAPIService getServices() {
        if (helper == null || helper.mService == null)
            throw new IllegalStateException("retrofit service not initialize yet.");
        return helper.mService;
    }

}
