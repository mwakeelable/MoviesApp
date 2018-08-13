package com.wakeel.moviesapp.presentation.movie_list;

import com.wakeel.moviesapp.data.response.MovieModel;
import com.wakeel.moviesapp.data.response.ResponseModel;
import com.wakeel.moviesapp.presentation.base.BaseView;
import com.wakeel.moviesapp.presentation.base.Presenter;

import java.util.List;

class MovieListContract {
    interface View extends BaseView {

        void showMovies(ResponseModel responseModel);

        void showProgress(boolean show);

        void showError(Throwable error);

        void movieClicked(MovieModel movieModel);
    }

    interface MovieListPresenter extends Presenter<View> {
        void getMovies();
    }
}
