package com.nytimesexample;

import android.app.Application;

import com.nytimesexample.data.source.ArticlesRepo;
import com.nytimesexample.data.source.server.ArticlesServerData;
import com.nytimesexample.data.source.server.RetrofitHelper;

/**
 * Created by Eval-Ranjitha on 04-08-2018.
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitHelper.initRetrofit();
    }

    public static ArticlesRepo getArticleRepo(){
        return ArticlesRepo.getInstance(ArticlesServerData.getInstance());
    }
}
