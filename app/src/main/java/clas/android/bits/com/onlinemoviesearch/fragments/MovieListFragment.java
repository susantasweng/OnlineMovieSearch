package clas.android.bits.com.onlinemoviesearch.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import clas.android.bits.com.onlinemoviesearch.MovieApplication;
import clas.android.bits.com.onlinemoviesearch.R;
import clas.android.bits.com.onlinemoviesearch.listeners.OnListFragmentInteractionListener;
import clas.android.bits.com.onlinemoviesearch.models.MovieDetailsDataModel;
import clas.android.bits.com.onlinemoviesearch.models.MovieSearchResultWithDetails;
import clas.android.bits.com.onlinemoviesearch.services.RetrofitLoader;
import clas.android.bits.com.onlinemoviesearch.utils.AppUtils;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class MovieListFragment extends Fragment implements OnListFragmentInteractionListener,
        LoaderManager.LoaderCallbacks<MovieSearchResultWithDetails> {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private List<MovieDetailsDataModel> movielList = new ArrayList<>();

    private Button mSearchButton;
    private EditText mSearchEditText;
    private RecyclerView mMovieListRecyclerView;
    private String mMovieTitle;
    private ProgressBar mProgressBar;

    private static final int LOADER_ID = 1001;
    private MovieRecyclerViewAdapter mMovieAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MovieListFragment() {
    }

    @SuppressWarnings("unused")
    public static MovieListFragment newInstance(int columnCount) {
        MovieListFragment fragment = new MovieListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {

                // First param is number of columns and second param is orientation i.e Vertical or Horizontal
                StaggeredGridLayoutManager gridLayoutManager =
                        new StaggeredGridLayoutManager(getResources().getInteger(mColumnCount), StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(gridLayoutManager);
            }
            recyclerView.setAdapter(mMovieAdapter = new MovieRecyclerViewAdapter(movielList, this));
        }


        mSearchEditText = (EditText) view.findViewById(R.id.search_edittext);
        // set action for pressing search button on keyboard
        mSearchEditText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || (event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER))) {
                    startSearch();
                    handled = true;
                }
                return handled;
            }
        });
        mSearchButton = (Button) view.findViewById(R.id.search_button);
        mMovieListRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSearch();
            }
        });

        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_spinner);


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onListFragmentInteraction(MovieDetailsDataModel detail) {

    }

    @NonNull
    @Override
    public Loader<MovieSearchResultWithDetails> onCreateLoader(int id, @Nullable Bundle args) {
        return new RetrofitLoader(this.getActivity(), args.getString("movieTitle"));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<MovieSearchResultWithDetails> loader, MovieSearchResultWithDetails resultWithDetail) {
        mProgressBar.setVisibility(View.GONE);
        mMovieListRecyclerView.setVisibility(View.VISIBLE);
        if(resultWithDetail.getResponse().equals("True")) {
            mMovieAdapter.swapData(resultWithDetail.getMovieDetailList());
        } else {
            Snackbar.make(mMovieListRecyclerView,
                    getResources().getString(R.string.snackbar_title_not_found), Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<MovieSearchResultWithDetails> loader) {
        mMovieAdapter.swapData(null);
    }

    private void startSearch() {
        if(AppUtils.isNetworkAvailable(MovieApplication.theInstance.getApplicationContext())) {
            AppUtils.hideSoftKeyboard(this.getActivity());
            String movieTitle = mSearchEditText.getText().toString().trim();
            if (!movieTitle.isEmpty()) {
                Bundle args = new Bundle();
                args.putString("movieTitle", movieTitle);
                this.getActivity().getSupportLoaderManager().restartLoader(LOADER_ID, args, this);
                mMovieTitle = movieTitle;
                mProgressBar.setVisibility(View.VISIBLE);
                mMovieListRecyclerView.setVisibility(View.GONE);
            }
            else
                Snackbar.make(mMovieListRecyclerView,
                        getResources().getString(R.string.snackbar_title_empty),
                        Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(mMovieListRecyclerView,
                    getResources().getString(R.string.network_not_available),
                    Snackbar.LENGTH_LONG).show();
        }
    }
}


