package com.wakeel.moviesapp.data.remote;

import com.wakeel.moviesapp.data.response.ResponseModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesAPIs {
    @GET("3/discover/movie?api_key=d3f9f87e5cc0c9e1f3c980a933d7ced9&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1")
    Observable<ResponseModel> getMovies();
}
