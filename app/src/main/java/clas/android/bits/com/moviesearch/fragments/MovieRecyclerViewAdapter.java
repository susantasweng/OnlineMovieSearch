package clas.android.bits.com.moviesearch.fragments;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import clas.android.bits.com.moviesearch.R;
import clas.android.bits.com.moviesearch.listeners.OnFragmentInteractionListener;
import clas.android.bits.com.moviesearch.models.MovieDetailsModel;

import java.util.List;

/**
 * Created by Susanta Mandal on 7/21/19.
 *
 * @discussion
 * {@link RecyclerView.Adapter} that can display a {@link MovieDetailsModel} and makes a call to the
 * specified {@link OnFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {

    private List<MovieDetailsModel> mValues;
    private final OnFragmentInteractionListener mListener;

    public MovieRecyclerViewAdapter(List<MovieDetailsModel> items, OnFragmentInteractionListener listener) {
        mListener = listener;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final MovieDetailsModel detail = mValues.get(position);
        final String title = detail.Title;
        final String imdbId = detail.imdbID;
        final String director = detail.Director;
        final String year = detail.Year;
        holder.mDirectorView.setText(director);
        holder.mTitleView.setText(title);
        holder.mYearView.setText(year);

        final String imageUrl;
        if (! detail.Poster.equals("N/A")) {
            imageUrl = detail.Poster;
        } else {
            // Poster placeholder
            imageUrl = holder.mView.getContext().getResources().getString(R.string.default_poster);
            detail.Poster = imageUrl;
        }

        holder.mThumbImageView.layout(0, 0, 0, 0); // invalidate the width so that glide won't use that dimension
        Glide.with(holder.mView.getContext()).load(imageUrl).into(holder.mThumbImageView);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the Activity) that an item has been selected.
                    mListener.onFragmentInteraction(detail);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mValues == null) {
            return 0;
        }
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final TextView mTitleView;
        private final TextView mYearView;
        private final TextView mDirectorView;
        private final ImageView mThumbImageView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = view.findViewById(R.id.movie_title);
            mYearView = view.findViewById(R.id.movie_year);
            mThumbImageView = view.findViewById(R.id.thumbnail);
            mDirectorView = view.findViewById(R.id.movie_director);
        }

    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        Glide.clear(holder.mThumbImageView);
    }

    public void swapData(List<MovieDetailsModel> items) {
        if(items != null) {
            mValues = items;
            notifyDataSetChanged();

        } else {
            mValues = null;
        }
    }
}