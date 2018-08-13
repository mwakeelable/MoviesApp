package com.wakeel.moviesapp.di.component;

import com.wakeel.moviesapp.di.module.FragmentModule;
import com.wakeel.moviesapp.di.scope.PerFragment;
import com.wakeel.moviesapp.presentation.movie_list.MovieListFragment;

import dagger.Subcomponent;

@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(MovieListFragment movieListFragment);
}
