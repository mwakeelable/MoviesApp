package com.wakeel.moviesapp.data;

import com.wakeel.moviesapp.data.remote.MoviesAPIs;
import com.wakeel.moviesapp.data.response.ResponseModel;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class DataManager {
    private MoviesAPIs moviesAPIs;

    @Inject
    public DataManager(MoviesAPIs moviesAPIs) {
        this.moviesAPIs = moviesAPIs;
    }

    public Observable<ResponseModel> getMovies() {
        return moviesAPIs.getMovies();
    }
}
