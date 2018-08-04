package com.nytimesexample.data.source.server;

import com.nytimesexample.data.Article;
import com.nytimesexample.data.NYResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ashok on 04-08-2018.
 */

public interface NYAPIService {

    @GET("mostpopular/v2/mostviewed/all-sections/7.json")
    Call<NYResponse<Article>> getArticles(@Query("api-key") String apiKey);

}
