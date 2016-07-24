package com.daryl.flickster.adapters;

import android.content.Context;
import android.content.res.Configuration;
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

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
        }

        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            ImageView ivPosterImage = (ImageView) convertView.findViewById(R.id.ivPosterMovieImage);
            ivPosterImage.setImageResource(0);
            Picasso.with(getContext()).load(movie.getPosterPath()).fit().
                    placeholder(R.drawable.loading_img).
                    error(R.drawable.error_img).
                    into(ivPosterImage);
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ImageView ivBackdropImage = (ImageView) convertView.findViewById(R.id.ivBackdropMovieImage);
            ivBackdropImage.setImageResource(0);
            Picasso.with(getContext()).load(movie.getBackdropPath()).fit().
                    placeholder(R.drawable.loading_img).
                    error(R.drawable.error_img).
                    into(ivBackdropImage);
        }

        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvMovieTitle);
        TextView tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);

        tvTitle.setText(movie.getOriginalTitle());
        tvOverview.setText(movie.getOverview());


        return convertView;
    }
}
