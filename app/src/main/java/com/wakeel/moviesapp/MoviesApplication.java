package com.wakeel.moviesapp;

import android.app.Application;
import android.content.Context;

import com.wakeel.moviesapp.data.URLs;
import com.wakeel.moviesapp.di.component.AppComponent;
import com.wakeel.moviesapp.di.component.DaggerAppComponent;
import com.wakeel.moviesapp.di.module.AppModule;
import com.wakeel.moviesapp.di.module.NetworkModule;

public class MoviesApplication extends Application {
    private AppComponent appComponent;

    public static MoviesApplication get(Context context){
        return (MoviesApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public AppComponent getComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .networkModule(new NetworkModule(this, URLs.BASE_URL))
                    .appModule(new AppModule(this))
                    .build();
        }
        return appComponent;
    }

    public void setComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
    }
}
