package clas.android.bits.com.moviesearch.services;

import clas.android.bits.com.moviesearch.models.MovieDetailsModel;
import clas.android.bits.com.moviesearch.models.Result;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IOmdbAPI {
    @GET("?type=movie")
    Call<Result> Result(
            @Query("s") String Title);

    @GET("?plot=full")
    Call<MovieDetailsModel> Detail(
            @Query("i") String ImdbId);
}


