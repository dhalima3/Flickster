package com.daryl.flickster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView tvMovieDetailTitle;
    private RatingBar rbMovieDetail;
    private TextView tvMovieDetailOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        tvMovieDetailTitle = (TextView) findViewById(R.id.tvMovieDetailTitle);
        tvMovieDetailOverview = (TextView) findViewById(R.id.tvMovieDetailOverview);
        rbMovieDetail = (RatingBar) findViewById(R.id.rbMovieDetail);

        String originalTitle = getIntent().getStringExtra("originalTitle");
        String overview = getIntent().getStringExtra("overview");
        float voteAverage = getIntent().getFloatExtra("voteAverage", 0);

        tvMovieDetailTitle.setText(originalTitle);
        tvMovieDetailOverview.setText(overview);
        rbMovieDetail.setRating(voteAverage);
    }
}
