package clas.android.bits.com.moviesearch.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Class WrappedModelObject
 * Copyright (c) 2016 MBRDNA. All rights reserved.
 * Created by Susanta Mandal on 2/16/16.
 * @discussion A simple wrapper that simplifies the serialization of model
 * data for the purpose of being able to save and restore them from
 * an activity's or fragment's bundle state.
 * <p>
 * The data are serialized into a JSON string, together with the
 * name of the class and the backend version, with which the data
 * structure was originally created.
 */

public final class WrappedModelObject implements Parcelable {
    private int apiVersion;
    private Object object;

    // ATTENTION: The order of arguments has to match the order in which these
    // arguments are written to the Parcel in writeToParcel()!
    private WrappedModelObject(int apiVersion, String clazzName, String json, String keyClass, String valueClass) {
        this.apiVersion = apiVersion;
    }

    private WrappedModelObject(Context context, Object object, Class<?> keyClass, Class<?> valueClass) {
        this.object = object;
    }

    public int getAPIVersion() {
        return apiVersion;
    }

    @SuppressWarnings({"unchecked"})
    public <T> T getData(Context context) {
        // if we have the object still available, return it directly
        if (object != null) {
            return (T) object;
        }
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Creator<WrappedModelObject> CREATOR = new Creator<WrappedModelObject>() {
        @Override
        public WrappedModelObject createFromParcel(Parcel in) {
            return new WrappedModelObject(
                    in.readInt(),
                    in.readString(),
                    in.readString(),
                    in.readString(),
                    in.readString());
        }

        @Override
        public WrappedModelObject[] newArray(int size) {
            return new WrappedModelObject[size];
        }
    };

    public static WrappedModelObject wrap(Context context, Object obj) {
        return new WrappedModelObject(context, obj, Void.class, Void.class);
    }

    public static WrappedModelObject wrap(Context context, Object obj, Class<?> keyClass) {
        return new WrappedModelObject(context, obj, keyClass, Void.class);
    }

    public static WrappedModelObject wrap(Context context, Object obj, Class<?> keyClass, Class<?> valueClass) {
        return new WrappedModelObject(context, obj, keyClass, valueClass);
    }
}
