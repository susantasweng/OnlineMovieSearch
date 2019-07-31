package clas.android.bits.com.onlinemoviesearch.services;

import clas.android.bits.com.onlinemoviesearch.models.MovieDetailsDataModel;
import clas.android.bits.com.onlinemoviesearch.models.Result;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IOmdbAPI {
    @GET("?type=movie")
    Call<Result> Result(
            @Query("s") String Title);

    @GET("?plot=full")
    Call<MovieDetailsDataModel> Detail(
            @Query("i") String ImdbId);
}


