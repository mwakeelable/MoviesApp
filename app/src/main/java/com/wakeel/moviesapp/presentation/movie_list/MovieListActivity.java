package com.wakeel.moviesapp.presentation.movie_list;

import android.os.Bundle;

import com.wakeel.moviesapp.R;
import com.wakeel.moviesapp.di.component.ActivityComponent;
import com.wakeel.moviesapp.presentation.base.BaseActivity;

public class MovieListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {

    }

    @Override
    protected void attachView() {

    }

    @Override
    public void initView() {
        super.initView();
    }
}
