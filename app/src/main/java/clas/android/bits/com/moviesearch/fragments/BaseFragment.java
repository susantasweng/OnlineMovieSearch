package clas.android.bits.com.moviesearch.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import clas.android.bits.com.moviesearch.listeners.OnFragmentInteractionListener;

/**
 * Created by Susanta Mandal on 2/16/16.
 *
 * @discussion Abstract Base class for Contentfragment, This will
 * initialise the layout and all other base tasks related for fragment.
 */

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public abstract class BaseFragment extends Fragment {

    /**
     * The Constant SETTINGS.
     */
    private static final String SETTINGS = "settings";

    /**
     * The settings.
     */
    private Bundle settings = new Bundle();

    protected OnFragmentInteractionListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            settings = savedInstanceState.getParcelable(SETTINGS);
        }

        //TODO: Update the root to container
        return inflater.inflate(getLayout(), null);
    }

    /**
     * Returns the ID of the layout to load into this fragment.
     *
     * @return the layout
     */
    protected abstract int getLayout();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        onInjectionHappened(view, savedInstanceState);
    }

    //TODO: Apply the Generics later and enable it
    /*public static BaseFragment newInstance() {
        
        Bundle args = new Bundle();
        
        BaseFragment fragment = new BaseFragment();
        fragment.setArguments(args);
        return fragment;
    }*/

    protected abstract void onInjectionHappened(View view, Bundle savedInstanceState);

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * android.support.v4.app.Fragment#onSaveInstanceState(android.os.Bundle)
     */
    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putParcelable(SETTINGS, settings);
    }

    /**
     * Sets the fragment's settings.
     *
     * @param settings the new settings
     */
    public void setSettings(Bundle settings) {
        if (settings != null) {
            this.settings = settings;
        }
    }

    /**
     * Returns a setting that is saved to and restored from the bundle state or
     * null if the setting could not be found.
     *
     * @param <T> the generic type
     * @param key the key
     * @return the setting
     */
    protected <T> T getSetting(String key) {
        return getSetting(key, null);
    }

    /**
     * Returns a setting that is saved to and restored from the bundle state or
     * the supplied default value if the setting could not be found.
     *
     * @param <T> the generic type
     * @param key the key
     * @param def the def
     * @return the setting
     */
    @SuppressWarnings("unchecked")
    protected <T> T getSetting(String key, T def) {
        if (settings == null) {
            return def;
        }

        T value = (T) settings.get(key);
        if (value == null) {
            return def;
        }
        return value;
    }

    /**
     * Puts a new setting that is saved to and restored from the bundle state.
     *
     * @param <T>   the generic type
     * @param key   the key
     * @param value the value
     */
    protected <T> void putSetting(String key, T value) {
        if (settings == null) {
            settings = new Bundle();
        }
        if (value instanceof Boolean) {
            settings.putBoolean(key, (Boolean) value);
        } else if (value instanceof String) {
            settings.putString(key, (String) value);
        } else if (value instanceof Integer) {
            settings.putInt(key, (Integer) value);
        } else if (value instanceof Parcelable) {
            settings.putParcelable(key, (Parcelable) value);
        } else {
            throw new IllegalStateException("FIXME: unsupported setting type " + value.getClass());
        }
    }

    /**
     * Removes a previously remembered setting.
     *
     * @param key the key
     */
    protected void removeSetting(String key) {
        settings.remove(key);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
}
