package clas.android.bits.com.onlinemoviesearch.handlers;

import android.content.Intent;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import clas.android.bits.com.onlinemoviesearch.R;
import clas.android.bits.com.onlinemoviesearch.activities.MainActivity;
import clas.android.bits.com.onlinemoviesearch.fragments.MovieInformationFragment;
import clas.android.bits.com.onlinemoviesearch.fragments.MovieListFragment;

public class FragmentTransactionHandler {

    private MainActivity mainActivity;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public FragmentTransactionHandler(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        fragmentManager = mainActivity.getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
    }

    public void loadFragment(Class<MovieListFragment> movieListFragmentClass) {
        MovieListFragment movieListFragment = MovieListFragment.newInstance(R.integer.grid_column_count);
        fragmentTransaction.add(R.id.fragment_container, movieListFragment);
        fragmentTransaction.commit();
    }

    //public void loadFragment(Class<MovieInformationFragment> movieInformationFragmentClass) {
        /*Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        // Pass data object in the bundle and populate details activity.
        intent.putExtra(DetailActivity.MOVIE_DETAIL, detail);
        intent.putExtra(DetailActivity.IMAGE_URL, imageUrl);

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(MainActivity.this,
                        holder.mThumbImageView, "poster");
        startActivity(intent, options.toBundle());*/
    //}
}
