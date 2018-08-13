package com.wakeel.moviesapp.di.component;

import android.app.Application;
import android.content.Context;

import com.wakeel.moviesapp.data.DataManager;
import com.wakeel.moviesapp.di.module.AppModule;
import com.wakeel.moviesapp.di.scope.ApplicationContext;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    @ApplicationContext
    Context context();

    Application application();

    DataManager dataManager();
}
