package clas.android.bits.com.moviesearch.handlers;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import clas.android.bits.com.moviesearch.MovieApplication;
import clas.android.bits.com.moviesearch.R;
import clas.android.bits.com.moviesearch.activities.BaseActivity;
import clas.android.bits.com.moviesearch.fragments.ContentFragment;
import clas.android.bits.com.moviesearch.models.WrappedModelObject;

import static clas.android.bits.com.moviesearch.activities.BaseActivity.DEFAULT_START_FRAGMENT;

public class FragmentTransactionHandler {

    private BaseActivity baseActivity;
    private FragmentManager fragmentManager;
    private static final String ADD_TRANSACTION = ":add";

    public FragmentTransactionHandler(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
        fragmentManager = baseActivity.getSupportFragmentManager();
    }

    public <V> void switchFragment(Class<? extends ContentFragment<?>> toClazz, V v, Bundle settings) {
        ContentFragment<?> fragment = null;
        try {
            fragment = toClazz.newInstance();
            fragment.setWrappedModelObject(WrappedModelObject.wrap(MovieApplication.getContext(), v));
            fragment.setArguments(settings);

        } catch (Exception e) {
            throw new IllegalStateException("could not instantiate fragment: " + e, e);
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(toClazz.equals(DEFAULT_START_FRAGMENT)) {
            fragmentTransaction.add(R.id.fragment_container, fragment, toClazz.getName());
        } else {
            fragmentTransaction.add(R.id.fragment_container, fragment, toClazz.getName());
        }
        fragmentTransaction.addToBackStack(getAddTransactionName(toClazz));
        fragmentTransaction.commit();
    }

    public ContentFragment<?> getCurrentFragment() {
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if(currentFragment != null) {
            return (ContentFragment<?>) currentFragment;
        }
        return null;
    }

    public int getFragmentAdditions(FragmentManager manager) {
        int count = 0;
        for (int i = 0; i < manager.getBackStackEntryCount(); ++i) {
            if (manager.getBackStackEntryAt(i).getName().contains(ADD_TRANSACTION)) {
                ++count;
            }
        }
        return count;
    }

    /**
     * Handles the default back key functions and return the end of stack status.
     *
     * @return true, if the back stack is empty otherwise return false.
     */
    public boolean onBackButtonPressed() {
        int additions = getFragmentAdditions(fragmentManager);

        // if there is only one fragment left, we kill the activity on back
        if (additions <= 1) {
            return true;
        }

        fragmentManager.popBackStackImmediate();
        //getCurrentFragment().onResume();
        return false;
    }

    /**
     * Append the add transaction string to Fragment class name
     *
     * @param clazz
     *            The fragment class which need to get the TransactionName.
     * @return the string with append the Add transaction to class name
     */
    private static String getAddTransactionName(Class<? extends ContentFragment<?>> clazz) {
        return clazz.getName() + ADD_TRANSACTION;
    }
}
