package com.nytimesexample.data.source;

import android.support.annotation.NonNull;

/**
 * Created by Ashok on 04-08-2018.
 */

public class ArticlesRepo implements ArticlesDataSource {
    private static ArticlesRepo INSTANCE;

    public synchronized static ArticlesRepo getInstance(@NonNull ArticlesDataSource articlesDataSource) {
        if (INSTANCE != null) return INSTANCE;
        return INSTANCE = new ArticlesRepo(articlesDataSource);
    }

    private ArticlesRepo(@NonNull ArticlesDataSource articlesDataSource) {
        this.mArticlesServerDataSource = articlesDataSource;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private final ArticlesDataSource mArticlesServerDataSource;

    @Override
    public void getArticles(@NonNull final GetArticlesCallbacks callbacks) {
        //load data from server
        mArticlesServerDataSource.getArticles(callbacks);
    }
}
