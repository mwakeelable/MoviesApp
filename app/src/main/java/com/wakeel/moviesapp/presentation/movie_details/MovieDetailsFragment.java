package com.wakeel.moviesapp.presentation.movie_details;

import android.graphics.Movie;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wakeel.moviesapp.R;
import com.wakeel.moviesapp.data.URLs;
import com.wakeel.moviesapp.data.response.MovieModel;
import com.wakeel.moviesapp.di.component.FragmentComponent;
import com.wakeel.moviesapp.presentation.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MovieDetailsFragment extends BaseFragment implements MovieDetailsContract.View {
    @Inject
    MovieDetailsPresenter movieDetailsPresenter;

    public MovieDetailsFragment() {
    }

    @Override
    protected int getLayout() {
        return R.layout.movie_details_fragment;
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void attachView() {
        movieDetailsPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        movieDetailsPresenter.detachView();
    }

    @BindView(R.id.movie_poster)
    ImageView poster;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.movie_name)
    TextView title;
    @BindView(R.id.movie_year)
    TextView releaseDate;
    @BindView(R.id.movie_rating)
    TextView rating;
    @BindView(R.id.movie_description)
    TextView overview;
    @BindView(R.id.toolbar)
    @Nullable
    Toolbar toolbar;

    private Unbinder unbinder;

    MovieModel movieModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        setToolbar();
        if (getArguments() != null) {
            MovieModel movieModel = (MovieModel) getArguments().get("movie");
            this.movieModel = movieModel;
            movieDetailsPresenter.getMovieDetails(movieModel);
        }
    }

    @Override
    public void showMovieDetails(MovieModel movie) {
        Glide.with(getContext()).load(URLs.getImagePath(movie.getPosterPath())).into(poster);
        title.setText(movie.getTitle());
        releaseDate.setText(String.format(getString(R.string.release_date), movie.getReleaseDate()));
        rating.setText(String.format(getString(R.string.rating), String.valueOf(movie.getVoteAverage())));
        overview.setText(movie.getOverview());
    }

    private void setToolbar() {
        collapsingToolbar.setContentScrimColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        collapsingToolbar.setTitle("Movie Details");
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedToolbar);
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedToolbar);
        collapsingToolbar.setTitleEnabled(true);

        if (toolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        } else {
            // Don't inflate. Tablet is in landscape mode.
        }
    }
}
