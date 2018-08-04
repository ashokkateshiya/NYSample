package com.nytimesexample.data.source.server;

import android.support.annotation.NonNull;

import com.nytimesexample.AppConstants;
import com.nytimesexample.data.Article;
import com.nytimesexample.data.NYResponse;
import com.nytimesexample.data.source.ArticlesDataSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Eval-Ranjitha on 04-08-2018.
 */

public class ArticlesServerData implements ArticlesDataSource{

    private static ArticlesServerData INSTANCE;

    public synchronized static ArticlesServerData getInstance() {
        if(INSTANCE != null) return INSTANCE;
        return INSTANCE = new ArticlesServerData();
    }

    private ArticlesServerData(){}

    @Override
    public void getArticles(@NonNull final GetArticlesCallbacks callbacks) {
        RetrofitHelper.getServices().getArticles(AppConstants.NY_API_KEY).enqueue(new Callback<NYResponse<Article>>() {
            @Override
            public void onResponse(@NonNull Call<NYResponse<Article>> call, Response<NYResponse<Article>> response) {
                if (response.isSuccessful()) {
                    NYResponse<Article> nyResponse = response.body();
                    if (nyResponse != null && nyResponse.getResults() != null) {
                        callbacks.onArticlesLoaded(nyResponse.getResults());
                    } else {
                        callbacks.onArticlesNotAvailable();
                    }
                } else {
                    callbacks.onArticlesNotAvailable();
                }
            }

            @Override
            public void onFailure(@NonNull Call<NYResponse<Article>> call, Throwable t) {
                callbacks.onArticlesNotAvailable();
            }
        });
    }
}
