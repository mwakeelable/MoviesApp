package com.wakeel.moviesapp.presentation.movie_list;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.wakeel.moviesapp.R;
import com.wakeel.moviesapp.di.component.ActivityComponent;
import com.wakeel.moviesapp.presentation.base.BaseActivity;

import butterknife.BindView;

public class MovieListActivity extends BaseActivity{
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void attachView() {
    }

    @Override
    protected void detachPresenter() {
    }
}
