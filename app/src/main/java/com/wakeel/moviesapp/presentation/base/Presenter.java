package com.wakeel.moviesapp.presentation.base;

public interface Presenter<V extends BaseView> {

    void attachView(V view);

    void detachView();

    boolean isViewAttached();

    V getView();
}
