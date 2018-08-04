package com.nytimesexample.articles;

import com.nytimesexample.BasePresenter;
import com.nytimesexample.BaseView;
import com.nytimesexample.data.Article;

import java.util.List;

/**
 * Created by Ashok on 04-08-2018.
 */

public interface ArticleContract {

    interface View extends BaseView<Presenter> {
        void showLoading(boolean active);
        void showArticles(List<Article> articles);
        void showNoArticles();
        void showLoadingArticleError();
        boolean isActive();
    }

    interface Presenter extends BasePresenter {
        void loadArticles();
    }
}
