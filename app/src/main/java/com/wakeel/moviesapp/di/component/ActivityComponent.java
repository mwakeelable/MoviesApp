package com.wakeel.moviesapp.di.component;

import com.wakeel.moviesapp.di.module.ActivityModule;
import com.wakeel.moviesapp.di.scope.PerActivity;
import com.wakeel.moviesapp.presentation.movie_details.MovieDetailsActivity;
import com.wakeel.moviesapp.presentation.movie_list.MovieListActivity;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MovieListActivity mainActivity);

    void inject(MovieDetailsActivity movieDetailsActivity);
}
