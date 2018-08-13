package com.wakeel.moviesapp.presentation.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wakeel.moviesapp.MoviesApplication;
import com.wakeel.moviesapp.di.component.ActivityComponent;
import com.wakeel.moviesapp.di.component.ConfigPersistentComponent;
import com.wakeel.moviesapp.di.component.DaggerConfigPersistentComponent;
import com.wakeel.moviesapp.di.module.ActivityModule;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
        bindViews();
        ConfigPersistentComponent configPersistentComponent;
        configPersistentComponent =
                DaggerConfigPersistentComponent.builder()
                        .appComponent(MoviesApplication.get(this).getComponent())
                        .build();
        ActivityComponent activityComponent =
                configPersistentComponent.activityComponent(new ActivityModule(this));
        inject(activityComponent);
        attachView();
    }

    public void initView() {
    }

    protected abstract int getLayoutId();

    private void bindViews() {
        ButterKnife.bind(this);
    }

    protected abstract void inject(ActivityComponent activityComponent);

    protected abstract void attachView();
}
