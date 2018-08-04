package com.nytimesexample.articles;

import android.support.annotation.NonNull;

import com.nytimesexample.data.Article;
import com.nytimesexample.data.source.ArticlesDataSource;
import com.nytimesexample.data.source.ArticlesRepo;

import java.util.List;

/**
 * Created by Eval-Ranjitha on 04-08-2018.
 */

public class ArticlePresenter implements ArticleContract.Presenter {
    private final ArticlesRepo mArticleRepo;
    private final ArticleContract.View mArticleView;

    public ArticlePresenter(@NonNull ArticlesRepo repo, ArticleContract.View articleView) {
        this.mArticleRepo = repo;
        this.mArticleView = articleView;
    }

    @Override
    public void start() {
        loadArticles();
    }

    @Override
    public void loadArticles() {
        mArticleView.showLoading(true);
        mArticleRepo.getArticles(new ArticlesDataSource.GetArticlesCallbacks() {
            @Override
            public void onArticlesLoaded(@NonNull List<Article> articles) {
                if (!mArticleView.isActive()) {
                    return;
                }

                mArticleView.showLoading(false);

                if (articles.isEmpty()) {
                    mArticleView.showNoArticles();
                } else {
                    mArticleView.showArticles(articles);
                }
            }

            @Override
            public void onArticlesNotAvailable() {
                if (!mArticleView.isActive()) {
                    return;
                }

                mArticleView.showLoading(false);
                mArticleView.showLoadingArticleError();
            }
        });

    }
}
