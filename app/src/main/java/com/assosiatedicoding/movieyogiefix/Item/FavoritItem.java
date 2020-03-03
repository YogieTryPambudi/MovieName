package com.assosiatedicoding.movieyogiefix.Item;

import android.os.Parcel;
import android.os.Parcelable;

public class FavoritItem implements Parcelable {

    private String name;
    private String overview;


    private int id;

    public FavoritItem() {

    }

    public FavoritItem(String name, String overview,int id) {
        this.name = name;
        this.overview = overview;
        this.id = id;
    }

    protected FavoritItem(Parcel in) {
        name = in.readString();
        overview = in.readString();
        id = in.readInt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static Creator<FavoritItem> getCREATOR() {
        return CREATOR;
    }

    public static final Creator<FavoritItem> CREATOR = new Creator<FavoritItem>() {
        @Override
        public FavoritItem createFromParcel(Parcel in) {
            return new FavoritItem(in);
        }

        @Override
        public FavoritItem[] newArray(int size) {
            return new FavoritItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(overview);
        dest.writeInt(id);
    }
}

