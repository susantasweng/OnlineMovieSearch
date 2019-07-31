package clas.android.bits.com.onlinemoviesearch.services;

import android.content.Context;
import android.util.Log;

import androidx.loader.content.AsyncTaskLoader;

import java.io.IOException;

import clas.android.bits.com.onlinemoviesearch.models.MovieDataModel;
import clas.android.bits.com.onlinemoviesearch.models.MovieSearchResultWithDetails;
import clas.android.bits.com.onlinemoviesearch.models.Result;

public class RetrofitLoader extends AsyncTaskLoader<MovieSearchResultWithDetails> {

    private static final String LOG_TAG = "RetrofitLoader";

    private final String mTitle;

    private MovieSearchResultWithDetails mData;

    public RetrofitLoader(Context context, String title) {
        super(context);
        mTitle = title;
    }

    @Override
    public MovieSearchResultWithDetails loadInBackground() {
        try {
            Result result =  MovieSearchService.performSearch(mTitle);
            MovieSearchResultWithDetails resultWithDetail = new MovieSearchResultWithDetails(result);
            if(result.Search != null) {
                for(MovieDataModel movie: result.Search) {
                    resultWithDetail.addToList(MovieSearchService.getDetail(movie.imdbID));
                }
            }
            return  resultWithDetail;
        } catch(final IOException e) {
            Log.e(LOG_TAG, "Error in api access", e);
        }
        return null;
    }

    @Override
    protected void onStartLoading() {
        if (mData != null) {
            // previously loaded data will be delivered immediately and it makes loaderManager to call onLoadFinished
            deliverResult(mData);
        } else {
            // calling load in background
            forceLoad();
        }
    }


    @Override
    protected void onReset() {
        Log.d(LOG_TAG, "onReset");
        super.onReset();
        mData = null;
    }

    @Override
    public void deliverResult(MovieSearchResultWithDetails data) {
        if (isReset()) {
            // The Loader has been reset; ignore the result and invalidate the data.
            return;
        }

        // Hold a reference to the old data so it doesn't get garbage collected.
        // We must protect it until the new data has been delivered.
        MovieSearchResultWithDetails oldData = mData;
        mData = data;

        if (isStarted()) {
            // If the Loader is in a started state, deliver the results to the
            // client. The superclass method does it.
            super.deliverResult(data);
        }

    }
}
