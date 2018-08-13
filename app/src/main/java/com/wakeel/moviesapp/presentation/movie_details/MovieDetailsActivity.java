package com.wakeel.moviesapp.presentation.movie_details;

import android.graphics.Movie;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wakeel.moviesapp.R;
import com.wakeel.moviesapp.data.response.MovieModel;
import com.wakeel.moviesapp.di.component.ActivityComponent;
import com.wakeel.moviesapp.presentation.base.BaseActivity;

public class MovieDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("movie")) {
            MovieModel movie = extras.getParcelable("movie");
            if (movie != null) {
                MovieDetailsFragment movieDetailsFragment = MovieDetailsFragment.getInstance(movie);
                getSupportFragmentManager().beginTransaction().add(R.id.movie_details_container, movieDetailsFragment).commit();
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.movie_details_activity;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void attachView() {

    }

    @Override
    protected void detachPresenter() {

    }
}
