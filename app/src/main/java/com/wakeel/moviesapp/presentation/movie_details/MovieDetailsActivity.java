package com.wakeel.moviesapp.presentation.movie_details;

import com.wakeel.moviesapp.R;
import com.wakeel.moviesapp.di.component.ActivityComponent;
import com.wakeel.moviesapp.presentation.base.BaseActivity;

public class MovieDetailsActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.movie_details_activity;
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
