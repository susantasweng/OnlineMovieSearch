package clas.android.bits.com.onlinemoviesearch.services;

import java.io.IOException;

import clas.android.bits.com.onlinemoviesearch.BuildConfig;
import clas.android.bits.com.onlinemoviesearch.models.MovieDetailsDataModel;
import clas.android.bits.com.onlinemoviesearch.models.Result;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieSearchService {

    private static final String API_URL = "http://www.omdbapi.com";
    private static IOmdbAPI sOmdbApi;

    private static void setsOmdbApi() {
        if (sOmdbApi == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    HttpUrl originalHttpUrl = original.url();

                    HttpUrl url = originalHttpUrl.newBuilder()
                            .addQueryParameter("apikey", BuildConfig.API_KEY)
                            .build();

                    // Adding required headers
                    Request.Builder requestBuilder = original.newBuilder()
                            .url(url);

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            sOmdbApi = retrofit.create(IOmdbAPI.class);
        }
    }

    public static Result performSearch(String title) throws IOException {
        setsOmdbApi();

        // Call instance to look up the movie names by title.
        Call<Result> call = sOmdbApi.Result(title);

        return call.execute().body();
    }

    public static MovieDetailsDataModel getDetail(String imdbId) throws IOException {
        setsOmdbApi();

        Call<MovieDetailsDataModel> call = sOmdbApi.Detail(imdbId);

        return call.execute().body();
    }
}
