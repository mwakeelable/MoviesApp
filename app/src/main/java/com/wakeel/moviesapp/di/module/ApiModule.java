package com.wakeel.moviesapp.di.module;

import com.wakeel.moviesapp.data.remote.MoviesAPIs;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = NetworkModule.class)
public class ApiModule {
    @Provides
    @Singleton
    MoviesAPIs provideMoviesAPIs(Retrofit retrofit) {
        return retrofit.create(MoviesAPIs.class);
    }
}
