package com.wakeel.moviesapp.presentation.movie_list;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wakeel.moviesapp.R;
import com.wakeel.moviesapp.data.response.MovieModel;
import com.wakeel.moviesapp.data.response.ResponseModel;
import com.wakeel.moviesapp.di.component.ActivityComponent;
import com.wakeel.moviesapp.presentation.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;

public class MovieListActivity extends BaseActivity implements MovieListContract.View {
    @Inject
    MovieListAdapter adapter;
    @Inject
    MovieListPresenter movieListPresenter;
    @BindView(R.id.movies_list)
    RecyclerView moviesListRecyclerView;
    @BindView(R.id.progress_movies)
    ProgressBar moviesProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moviesListRecyclerView.setHasFixedSize(true);
        int columns = 2;
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, columns);
        moviesListRecyclerView.setLayoutManager(layoutManager);
        moviesListRecyclerView.setAdapter(adapter);
        movieListPresenter.getMovies();
    }

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
        movieListPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        movieListPresenter.detachView();
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void showMovies(ResponseModel responseModel) {
        moviesProgress.setVisibility(View.GONE);
        moviesListRecyclerView.setVisibility(View.VISIBLE);
        adapter.setMovies(responseModel.getResults());
    }

    @Override
    public void showProgress(boolean show) {
        moviesProgress.setVisibility(View.VISIBLE);
        moviesListRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable error) {

    }

    @Override
    public void movieClicked(MovieModel movieModel) {
        Disposable disposable =
                adapter.getMovieClick()
                        .subscribe(movieModel1 -> {
                            Toast.makeText(this, movieModel.getTitle(), Toast.LENGTH_SHORT).show();
                            // to start new activity
                            // startActivity(DetailActivity.getStartIntent(this, post)),
                        }, throwable -> {
                            Toast.makeText(this, throwable.toString(), Toast.LENGTH_SHORT).show();
                        });
        movieListPresenter.addDisposable(disposable);
    }
}
