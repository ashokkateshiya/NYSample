package com.nytimesexample.data.source;

import android.support.annotation.NonNull;

import com.nytimesexample.data.Article;

import java.util.List;

/**
 * Created by Ashok on 04-08-2018.
 */

public interface ArticlesDataSource {

    interface GetArticlesCallbacks{
        void onArticlesLoaded(@NonNull List<Article> articles);
        void onArticlesNotAvailable();
    }

    void getArticles(@NonNull GetArticlesCallbacks callbacks);
}
