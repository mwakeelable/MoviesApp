package com.wakeel.moviesapp.presentation.movie_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wakeel.moviesapp.R;
import com.wakeel.moviesapp.data.response.MovieModel;
import com.wakeel.moviesapp.data.response.ResponseModel;
import com.wakeel.moviesapp.di.component.FragmentComponent;
import com.wakeel.moviesapp.presentation.base.BaseFragment;
import com.wakeel.moviesapp.presentation.movie_details.MovieDetailsActivity;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;

public class MovieListFragment extends BaseFragment implements MovieListContract.View {
    @Inject
    MovieListAdapter adapter;
    @Inject
    MovieListPresenter movieListPresenter;
    @BindView(R.id.movies_list)
    RecyclerView moviesListRecyclerView;
    @BindView(R.id.progress_movies)
    ProgressBar moviesProgress;

    public MovieListFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter.setView(this);
        moviesListRecyclerView.setHasFixedSize(true);
        int columns = 2;
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), columns);
        moviesListRecyclerView.setLayoutManager(layoutManager);
        moviesListRecyclerView.setAdapter(adapter);
        movieListPresenter.getMovies();
    }

    @Override
    protected int getLayout() {
        return R.layout.movie_list_fragment;
    }

    @Override
    protected void inject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
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
        moviesProgress.setVisibility(View.GONE);
        Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void movieClicked(MovieModel movieModel) {
        if (((MovieListActivity) getContext()).twoPaneMode) {
            ((MovieListActivity) getContext()).loadMovieFragment(movieModel);
        } else {
            Intent movieDetailsIntent = new Intent(getContext(), MovieDetailsActivity.class);
            Bundle extras = new Bundle();
            extras.putParcelable("movie", movieModel);
            movieDetailsIntent.putExtras(extras);
            startActivity(movieDetailsIntent);
        }
    }
}
