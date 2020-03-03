package com.assosiatedicoding.movieyogiefix.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.assosiatedicoding.movieyogiefix.Item.MovieItem;
import com.assosiatedicoding.movieyogiefix.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private static Context context;
    private ArrayList<MovieItem> mData = new ArrayList<>();

    public MovieAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<MovieItem> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    public void addItem(final MovieItem item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void clearData() {
        mData.clear();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item
                , viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieViewHolder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        static TextView movieName;
        static TextView movieDescription;
        static ImageView moviePoster;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            movieName = itemView.findViewById(R.id.movie_name);
            movieDescription = itemView.findViewById(R.id.movie_description);
            moviePoster = itemView.findViewById(R.id.movie_poster);
        }

        static void bind(final MovieItem movie) {
            movieName.setText(movie.getMovieName());
            movieDescription.setText(movie.getMovieDescription());
            Glide.with(context).load(movie.getmoviePoster()).into(moviePoster);
        }
    }
}


