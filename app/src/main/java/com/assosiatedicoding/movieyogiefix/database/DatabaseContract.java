package com.assosiatedicoding.movieyogiefix.database;

import android.provider.BaseColumns;

public class DatabaseContract {
    static String TABLE_NAME = "film";

    static final class FilmColumns implements BaseColumns {

        static String NAME = "name";
        static String OVERVIEW = "overview";

    }
}

