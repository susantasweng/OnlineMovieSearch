package clas.android.bits.com.onlinemoviesearch.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import clas.android.bits.com.onlinemoviesearch.R;
import clas.android.bits.com.onlinemoviesearch.fragments.MovieListFragment;
import clas.android.bits.com.onlinemoviesearch.handlers.FragmentTransactionHandler;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity {


    private FragmentTransactionHandler fragmentTransactionHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        fragmentTransactionHandler = new FragmentTransactionHandler(this);
        fragmentTransactionHandler.loadFragment(MovieListFragment.class);


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

}
