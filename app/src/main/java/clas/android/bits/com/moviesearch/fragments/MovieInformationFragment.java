package clas.android.bits.com.moviesearch.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import clas.android.bits.com.moviesearch.R;
import clas.android.bits.com.moviesearch.models.MovieDetailsModel;

/**
 * Created by Susanta Mandal on 7/24/19.
 *
 * @discussion
 * A simple {@link Fragment} subclass to show the details of the Movie
 */
public class MovieInformationFragment extends ContentFragment<MovieDetailsModel> {
    private MovieDetailsModel movieDetail;
    private String moviePosterURL;
    private TextView titleView;
    private TextView writersView;
    private TextView actorsView;
    private TextView directorView;
    private TextView genreView;
    private TextView releasedView;
    private TextView plotView;
    private TextView runtimeView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieDetail = getModelObject(null);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_movie_information;
    }

    @Override
    protected void onInjectionHappened(View view, Bundle savedInstanceState) {
        titleView = view.findViewById(R.id.grid_title);
        writersView = view.findViewById(R.id.grid_writers);
        actorsView = view.findViewById(R.id.grid_actors);
        directorView = view.findViewById(R.id.grid_director);
        genreView = view.findViewById(R.id.grid_genre);
        releasedView = view.findViewById(R.id.grid_released);
        plotView = view.findViewById(R.id.grid_plot);
        runtimeView = view.findViewById(R.id.grid_runtime);

        loadUI(view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void loadUI(View view) {
        Glide.with(getActivity()).load(movieDetail.Poster).into((ImageView) view.findViewById(R.id.main_backdrop));
        CollapsingToolbarLayout collapsingToolbarLayout = view.findViewById(R.id.main_collapsing); // set title for the appbar
        collapsingToolbarLayout.setTitle(movieDetail.Title);

        titleView.setText(movieDetail.Title);
        writersView.setText(movieDetail.Writer);
        actorsView.setText(movieDetail.Actors);
        directorView.setText(movieDetail.Director);
        genreView.setText(movieDetail.Genre);
        releasedView.setText(movieDetail.Released);
        plotView.setText(movieDetail.Plot);
        runtimeView.setText(movieDetail.Runtime);
    }
}
