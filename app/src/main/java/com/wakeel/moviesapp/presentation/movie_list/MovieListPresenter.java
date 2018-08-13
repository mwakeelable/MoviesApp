package com.wakeel.moviesapp.presentation.movie_list;

import com.wakeel.moviesapp.data.DataManager;
import com.wakeel.moviesapp.data.response.MovieModel;
import com.wakeel.moviesapp.presentation.base.BasePresenter;
import com.wakeel.moviesapp.utils.SchedulerUtils;

import java.util.List;

import javax.inject.Inject;

public class MovieListPresenter extends BasePresenter<MovieListContract.View> implements MovieListContract.MovieListPresenter {
    private final DataManager dataManager;

    @Inject
    public MovieListPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(MovieListContract.View view) {
        super.attachView(view);
    }



    @Override
    public void getMovies() {
        checkViewAttached();
        getView().showProgress(true);
        dataManager.getMovies()
                .compose(SchedulerUtils.ioToMain())
                .subscribe(responseModel -> {
                    getView().showProgress(false);
                    getView().showMovies(responseModel);
                }, throwable -> {
                    getView().showProgress(false);
                    getView().showError(throwable);
                });
    }
}
