package com.wakeel.moviesapp.presentation.movie_details;

import com.wakeel.moviesapp.data.response.MovieModel;
import com.wakeel.moviesapp.data.response.ResponseModel;
import com.wakeel.moviesapp.presentation.base.BaseView;
import com.wakeel.moviesapp.presentation.base.Presenter;

public class MovieDetailsContract {
    interface View extends BaseView {
        void showMovieDetails(MovieModel movieModel);
    }

    interface MovieListPresenter extends Presenter<View> {
        void getMovieDetails(MovieModel movie);
    }
}
