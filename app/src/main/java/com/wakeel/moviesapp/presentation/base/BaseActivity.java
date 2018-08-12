package com.wakeel.moviesapp.presentation.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        bindViews();
    }

    public void initView() {
    }

    protected abstract int getLayoutId();

    private void bindViews() {
        ButterKnife.bind(this);
    }
}
