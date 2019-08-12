package clas.android.bits.com.moviesearch.activities;

import android.os.Bundle;
import android.view.KeyEvent;

import androidx.appcompat.app.AppCompatActivity;

import clas.android.bits.com.moviesearch.R;
import clas.android.bits.com.moviesearch.fragments.ContentFragment;
import clas.android.bits.com.moviesearch.fragments.MovieInformationFragment;
import clas.android.bits.com.moviesearch.fragments.MovieListFragment;
import clas.android.bits.com.moviesearch.handlers.FragmentTransactionHandler;
import clas.android.bits.com.moviesearch.listeners.OnFragmentInteractionListener;
/**
 * Created by Susanta Mandal on 7/20/19.
 *
 * @discussion
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class BaseActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    public static final Class<? extends ContentFragment<?>> DEFAULT_START_FRAGMENT = MovieListFragment.class;
    public FragmentTransactionHandler fragmentTransactionHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_main);
        fragmentTransactionHandler = new FragmentTransactionHandler(this);

        int listGridCount = getResources().getInteger(R.integer.grid_column_count);
        if (savedInstanceState == null) {
            fragmentTransactionHandler.switchFragment(DEFAULT_START_FRAGMENT, listGridCount, null);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    public <V> void switchFragment(Class<? extends ContentFragment<?>> toClazz, V v, Bundle settings) {
        fragmentTransactionHandler.switchFragment(toClazz, v, settings);
    }

    private void handleBackPressEvent() {
        ContentFragment<?> currentFragment = fragmentTransactionHandler.getCurrentFragment();
        boolean isHandledByChild = currentFragment.onBackButtonPressed();

        if (!isHandledByChild) {
            if (fragmentTransactionHandler.onBackButtonPressed()) {
                this.finish();
            }
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            handleBackPressEvent();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public <V> void onFragmentInteraction(V v) {
        switchFragment(MovieInformationFragment.class, v, null);
    }
}
