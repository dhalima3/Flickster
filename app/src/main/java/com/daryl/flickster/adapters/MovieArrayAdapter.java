package com.daryl.flickster.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.daryl.flickster.R;
import com.daryl.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    public static final int POPULAR_MOVIE_LAYOUT = 1;
    public static final int NORMAL_MOVIE_LAYOUT = 0;
    public static final double BACKDROP_IMAGE_WIDTH_MULTIPLIER = .6;
    public static final double POSTER_IMAGE_WIDTH_MULTIPLIER = .4;

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position).isPopular()) {
            return POPULAR_MOVIE_LAYOUT;
        } else {
            return NORMAL_MOVIE_LAYOUT;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);

        if (convertView == null) {
            int type = getItemViewType(position);
            convertView = getInflatedLayoutForType(type, parent);
        }

        ImageView ivMovieImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvMovieTitle);
        TextView tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);

        String imageUri = movie.isPopular() ? movie.getBackdropPath() : movie.getPosterPath();
        int imageWidth = getContext().getResources().getDisplayMetrics().widthPixels;

        if (movie.isPopular()) {
            if (tvTitle != null) {
                imageWidth *= BACKDROP_IMAGE_WIDTH_MULTIPLIER;
            }
        } else {
            imageWidth *= POSTER_IMAGE_WIDTH_MULTIPLIER;
        }

        Picasso.with(getContext()).load(imageUri).
                placeholder(R.drawable.loading_img).
                error(R.drawable.error_img).
                resize(imageWidth, 0).
                into(ivMovieImage);

        if (tvTitle != null) {
            tvTitle.setText(movie.getOriginalTitle());
        }
        if (tvOverview != null) {
            tvOverview.setText(movie.getOverview());
        }

        return convertView;
    }

    private View getInflatedLayoutForType(int type, ViewGroup parent) {
        if (type == POPULAR_MOVIE_LAYOUT) {
            return LayoutInflater.from(getContext()).inflate(R.layout.item_popular_movie, parent, false);
        } else {
            return LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent, false);
        }
    }
}
