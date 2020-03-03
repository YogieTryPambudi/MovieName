package com.assosiatedicoding.movieyogiefix.Item;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class MovieItem implements Parcelable {
    private int id;
    private String movieName;
    private String movieDescription;
    private String moviePoster;



    public MovieItem(JSONObject object) {
        try {
            int id = object.getInt("id");
            String movieName = object.getString("title");
            String movieDescription = object.getString("overview");
            String moviePoster =("https://image.tmdb.org/t/p/w185" + object.getString("poster_path"));


            this.id = id;
            this.moviePoster = moviePoster;
            this.movieName = movieName;
            this.movieDescription = movieDescription;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected MovieItem(Parcel in) {
        id = in.readInt();
        movieName = in.readString();
        movieDescription = in.readString();
        moviePoster = in.readString();
    }

    public static final Creator<MovieItem> CREATOR = new Creator<MovieItem>() {
        @Override
        public MovieItem createFromParcel(Parcel in) {
            return new MovieItem(in);
        }

        @Override
        public MovieItem[] newArray(int size) {
            return new MovieItem[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }
    public void setMovieName(String movieName){
        this.movieName = movieName;
    }


    public String getMovieDescription() {
        return movieDescription;
    }
    public void setMovieDescription(String movieDescription){
        this.movieDescription = movieDescription;
    }


    public String getmoviePoster() {
        return moviePoster;
    }
    public void setMoviePoster(String moviePoster){
        this.moviePoster = moviePoster;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(movieName);
        dest.writeString(movieDescription);
        dest.writeString(moviePoster);
    }
}


