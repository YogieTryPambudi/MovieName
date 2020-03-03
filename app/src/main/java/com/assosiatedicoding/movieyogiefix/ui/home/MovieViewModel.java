package com.assosiatedicoding.movieyogiefix.ui.home;

import android.util.Log;

import com.assosiatedicoding.movieyogiefix.Item.MovieItem;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import cz.msebera.android.httpclient.Header;

public class MovieViewModel extends ViewModel {
    public static final String API_KEY = "a5410b62d253b5a06fd82196cc013dca";

    private MutableLiveData<ArrayList<MovieItem>> listMovies = new MutableLiveData<>();

    public void setMovie(){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<MovieItem> listItem = new ArrayList<>();

        String url = "https://api.themoviedb.org/3/discover/movie?api_key=" + API_KEY;

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray results = responseObject.getJSONArray("results");

                    for (int i = 0; i<results.length(); i++){
                        JSONObject movie = results.getJSONObject(i);
                        MovieItem movieItems = new MovieItem(movie);
                        listItem.add(movieItems);
                    }
                    listMovies.postValue(listItem);
                }catch (Exception e){
                    Log.d("Exception",e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("onFailure",statusCode+""+error.getMessage());

            }
        });
    }
    public LiveData<ArrayList<MovieItem>> getMovies(){
        return listMovies;
    }

}