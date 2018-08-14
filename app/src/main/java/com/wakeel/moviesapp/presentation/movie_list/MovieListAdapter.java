package com.wakeel.moviesapp.presentation.movie_list;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.wakeel.moviesapp.R;
import com.wakeel.moviesapp.data.URLs;
import com.wakeel.moviesapp.data.response.MovieModel;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private List<MovieModel> movies;
    private Context context;
    MovieListContract.View moviesListView;

    @Inject
    MovieListAdapter() {
        movies = Collections.emptyList();
    }

    public void setView(MovieListContract.View moviesListView) {
        this.moviesListView = moviesListView;
    }

    public void setMovies(List<MovieModel> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_grid_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.movieContainer.setOnClickListener(holder);
        holder.movie = movies.get(position);
        holder.name.setText(holder.movie.getTitle());

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.HIGH);

        Glide.with(context)
                .asBitmap()
                .load(URLs.getImagePath(holder.movie.getPosterPath()))
                .apply(options)
                .into(new BitmapImageViewTarget(holder.poster) {
                    @Override
                    public void onResourceReady(Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                        super.onResourceReady(bitmap, transition);
                        holder.movieItemProgress.setVisibility(View.GONE);
                        Palette.from(bitmap).generate(palette -> setBackgroundColor(palette, holder));
                    }
                });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    private void setBackgroundColor(Palette palette, ViewHolder holder) {
        holder.titleBackground.setBackgroundColor(palette.getVibrantColor(context
                .getResources().getColor(R.color.colorPrimary)));
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.movie_poster)
        ImageView poster;
        @BindView(R.id.movieItemProgress)
        ProgressBar movieItemProgress;
        @BindView(R.id.title_background)
        View titleBackground;
        @BindView(R.id.movie_name)
        TextView name;
        @BindView((R.id.movie_container))
        FrameLayout movieContainer;

        public MovieModel movie;

        public ViewHolder(View root) {
            super(root);
            ButterKnife.bind(this, root);
        }

        @Override
        public void onClick(View view) {
            moviesListView.movieClicked(movie);
        }
    }
}
