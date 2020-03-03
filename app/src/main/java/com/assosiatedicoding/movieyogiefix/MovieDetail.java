package com.assosiatedicoding.movieyogiefix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.assosiatedicoding.movieyogiefix.Item.MovieItem;
import com.bumptech.glide.Glide;

public class MovieDetail  extends AppCompatActivity  {
    MovieItem movieItems;
    ImageView moviePoster;
    TextView movieName;
    TextView movieDescription;
    TextView nameMovie;
    TextView descriptionMovie;
    Button btnSubmit;
    private ProgressBar progressBar;
    public static final String EXTRA_MOVIE = "extra_movie";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        moviePoster = (ImageView) findViewById(R.id.movie_poster);
        movieName = (TextView) findViewById(R.id.movie_name);
        movieDescription = (TextView) findViewById(R.id.movie_description);
        nameMovie = (TextView) findViewById(R.id.name_movie);
        descriptionMovie = (TextView) findViewById(R.id.description_movie);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        progressBar.setVisibility(View.VISIBLE);
        showLoading(true);
        movieItems = getIntent().getParcelableExtra(EXTRA_MOVIE);
        if (getIntent() != null) {
            showLoading(false);

        }
        movieName.setText(movieItems.getMovieName());


        movieDescription.setText(movieItems.getMovieDescription());
        Glide.with(MovieDetail.this)
                .load(movieItems.getmoviePoster())
                .into(moviePoster);

        if (movieItems != null) {

        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }

    }

}