package com.wakeel.moviesapp.presentation.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wakeel.moviesapp.MoviesApplication;
import com.wakeel.moviesapp.di.component.ConfigPersistentComponent;
import com.wakeel.moviesapp.di.component.DaggerConfigPersistentComponent;
import com.wakeel.moviesapp.di.component.FragmentComponent;
import com.wakeel.moviesapp.di.module.FragmentModule;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConfigPersistentComponent configPersistentComponent;
        configPersistentComponent =
                DaggerConfigPersistentComponent.builder()
                        .appComponent(MoviesApplication.get(getActivity()).getComponent())
                        .build();
        FragmentComponent fragmentComponent = configPersistentComponent.fragmentComponent(new FragmentModule(this));
        inject(fragmentComponent);
        attachView();
    }

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    protected abstract int getLayout();

    protected abstract void inject(FragmentComponent fragmentComponent);

    protected abstract void attachView();

    protected abstract void detachPresenter();

    @Override
    public void onDestroy() {
        detachPresenter();
        super.onDestroy();
    }
}
