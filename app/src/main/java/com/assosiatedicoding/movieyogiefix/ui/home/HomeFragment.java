package com.assosiatedicoding.movieyogiefix.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.assosiatedicoding.movieyogiefix.Item.MovieItem;
import com.assosiatedicoding.movieyogiefix.MovieDetail;
import com.assosiatedicoding.movieyogiefix.R;
import com.assosiatedicoding.movieyogiefix.adapter.MovieAdapter;
import com.assosiatedicoding.movieyogiefix.clik.ItemClickSupport;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private MovieAdapter adapter;
    private RecyclerView recyclerView;
    private MovieViewModel homeViewModel;
    private ProgressBar progressBar;

    public HomeFragment() {

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homeViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        homeViewModel.getMovies().observe(this, getMovies);
        homeViewModel.setMovie();

        recyclerView = view.findViewById(R.id.rv_category);
        adapter = new MovieAdapter(getContext());
        progressBar = view.findViewById(R.id.progressBar);
        adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        showLoading(true);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent movieObject = new Intent(getActivity(), MovieDetail.class);
                movieObject.putExtra(MovieDetail.EXTRA_MOVIE,homeViewModel.getMovies().getValue()
                        .get(position));
                startActivity(movieObject);

            }


        });

    }

    private Observer<ArrayList<MovieItem>> getMovies = new Observer<ArrayList<MovieItem>>() {
        @Override
        public void onChanged(ArrayList<MovieItem> movieItem) {
            if (movieItem != null) {
                adapter.setData(movieItem);
                showLoading(false);

            }
        }
    };
    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }


}


