package com.wakeel.moviesapp.presentation.movie_details;

import com.wakeel.moviesapp.data.response.MovieModel;
import com.wakeel.moviesapp.presentation.base.BasePresenter;

import javax.inject.Inject;

public class MovieDetailsPresenter extends BasePresenter<MovieDetailsContract.View>
        implements MovieDetailsContract.MovieListPresenter {

    @Inject
    public MovieDetailsPresenter() {

    }

    @Override
    public void attachView(MovieDetailsContract.View view) {
        super.attachView(view);
    }

    @Override
    public void getMovieDetails(MovieModel movie) {
        getView().showMovieDetails(movie);
    }
}
