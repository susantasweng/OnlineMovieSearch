package clas.android.bits.com.moviesearch.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import clas.android.bits.com.moviesearch.MovieApplication;
import clas.android.bits.com.moviesearch.R;
import clas.android.bits.com.moviesearch.listeners.OnFragmentInteractionListener;
import clas.android.bits.com.moviesearch.models.MovieDetailsModel;
import clas.android.bits.com.moviesearch.models.MovieSearchResultWithDetails;
import clas.android.bits.com.moviesearch.services.RetrofitLoader;
import clas.android.bits.com.moviesearch.utils.AppUtils;

/**
 * Created by Susanta Mandal on 7/21/19.
 *
 * @discussion
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class MovieListFragment extends ContentFragment<Integer> implements
        LoaderManager.LoaderCallbacks<MovieSearchResultWithDetails>, OnFragmentInteractionListener {

    private int mColumnCount = 1;
    private List<MovieDetailsModel> movielList = new ArrayList<>();
    private LinearLayout rootLayout;
    private SearchView mSearchEditText;
    private RecyclerView movieRecyclerView;
    private String mMovieTitle;
    private ProgressBar mProgressBar;

    private static final int LOADER_ID = 1001;
    private MovieRecyclerViewAdapter mMovieAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mColumnCount = getModelObject(null);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_movie_list;
    }

    @Override
    protected void onInjectionHappened(View view, Bundle savedInstanceState) {

        rootLayout = view.findViewById(R.id.list_root_layout);
        movieRecyclerView = view.findViewById(R.id.recycler_view);

        if (mColumnCount <= 1) {
            movieRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        } else {
            StaggeredGridLayoutManager gridLayoutManager =
                    new StaggeredGridLayoutManager(mColumnCount, StaggeredGridLayoutManager.VERTICAL);
            movieRecyclerView.setLayoutManager(gridLayoutManager);
        }

        movieRecyclerView.setAdapter(mMovieAdapter = new MovieRecyclerViewAdapter(movielList, this));


        mSearchEditText = view.findViewById(R.id.search_edittext);

        mSearchEditText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                startSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_spinner);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @NonNull
    @Override
    public Loader<MovieSearchResultWithDetails> onCreateLoader(int id, @Nullable Bundle args) {
        return new RetrofitLoader(this.getActivity(), args.getString("movieTitle"));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<MovieSearchResultWithDetails> loader, MovieSearchResultWithDetails resultWithDetail) {
        mProgressBar.setVisibility(View.GONE);
        movieRecyclerView.setVisibility(View.VISIBLE);
        if (resultWithDetail.getResponse().equals("True")) {
            mMovieAdapter.swapData(resultWithDetail.getMovieDetailList());
        } else {
            Snackbar.make(movieRecyclerView,
                    getResources().getString(R.string.snackbar_title_not_found), Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<MovieSearchResultWithDetails> loader) {
        mMovieAdapter.swapData(null);
    }

    private void startSearch(String movieTitle) {
        if (AppUtils.isNetworkAvailable(MovieApplication.theInstance.getApplicationContext())) {
            AppUtils.hideSoftKeyboard(this.getActivity());

            if (!movieTitle.isEmpty()) {
                Bundle args = new Bundle();
                args.putString("movieTitle", movieTitle);
                this.getActivity().getSupportLoaderManager().restartLoader(LOADER_ID, args, this);
                mMovieTitle = movieTitle;
                mProgressBar.setVisibility(View.VISIBLE);
                movieRecyclerView.setVisibility(View.GONE);
            } else
                Snackbar.make(movieRecyclerView,
                        getResources().getString(R.string.snackbar_title_empty),
                        Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(movieRecyclerView,
                    getResources().getString(R.string.network_not_available),
                    Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public <V> void onFragmentInteraction(V v) {
        rootLayout.requestFocus();
        AppUtils.hideSoftKeyboard(this.getActivity());
        mListener.onFragmentInteraction(v);
    }
}


