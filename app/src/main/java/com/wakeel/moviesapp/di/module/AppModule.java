package com.wakeel.moviesapp.di.module;

import android.app.Application;
import android.content.Context;

import com.wakeel.moviesapp.di.scope.ApplicationContext;

import dagger.Module;
import dagger.Provides;

@Module(includes = ApiModule.class)
public class AppModule {
    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }
}
