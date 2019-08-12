package clas.android.bits.com.moviesearch.fragments;

import clas.android.bits.com.moviesearch.activities.BaseActivity;
import clas.android.bits.com.moviesearch.models.WrappedModelObject;

/**
 * Created by Susanta Mandal on 2/16/16.
 *
 * @discussion Abstract Base class for all Legal Info fragment classes. It does the common implementation for Fragments and other related for tasks.
 */
public abstract class ContentFragment<V> extends BaseFragment {

    private WrappedModelObject wrappedObject;

    public boolean onBackButtonPressed() {
        return false;
    };

    public ContentFragment<V> setWrappedModelObject(WrappedModelObject wrappedObject) {
        this.wrappedObject = wrappedObject;
        return this;
    }

    /**
     * Returns the model object associated with this fragment, if any, or null.
     *
     * @return
     */
    public V getModelObject(V defaultObject) {
        if (wrappedObject == null) {
            return defaultObject;
        }
        try {
            return wrappedObject.getData(getActivity());
        } catch (IllegalStateException e) {
            return defaultObject;
        }
    }

    public <T extends BaseActivity> T getBaseActivity() {
        return (T) getActivity();
    };
}
