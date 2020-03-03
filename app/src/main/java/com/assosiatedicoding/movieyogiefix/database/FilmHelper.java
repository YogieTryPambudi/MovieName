package com.assosiatedicoding.movieyogiefix.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.assosiatedicoding.movieyogiefix.Item.FavoritItem;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.assosiatedicoding.movieyogiefix.database.DatabaseContract.FilmColumns.NAME;
import static com.assosiatedicoding.movieyogiefix.database.DatabaseContract.FilmColumns.OVERVIEW;
import static com.assosiatedicoding.movieyogiefix.database.DatabaseContract.TABLE_NAME;

public class FilmHelper {
    private static final String DATABASE_TABLE = TABLE_NAME;
    private static DatabaseHelper databaseHelper;
    private static FilmHelper INSTANCE;

    private static SQLiteDatabase database;

    private FilmHelper(Context context){
        databaseHelper = new DatabaseHelper(context);
    }
    public static FilmHelper getInstance(Context context){
        if (INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                INSTANCE = new FilmHelper(context);
            }
        }
        return INSTANCE;

    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();

        if (database.isOpen())
            database.close();
    }

    public Cursor queryAll() {
        return database.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC"
        );
    }

    public Cursor queryById(String id) {
        return database.query(
                DATABASE_TABLE,
                null,
                _ID + " = ?",
                new String[]{id},
                null,
                null,
                null,
                null
        );
    }


    public ArrayList<FavoritItem> getAllFilm() {
        ArrayList<FavoritItem> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                _ID + "ASC",
                null
        );
        cursor.moveToFirst();
        FavoritItem filmFavorite;

        if (cursor.getCount() > 0) {
            do {
                filmFavorite = new FavoritItem();
                filmFavorite.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                filmFavorite.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)));
                filmFavorite.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
            } while (!cursor.isAfterLast());
        }

        cursor.close();
        return arrayList;
    }

    public long insertFilm(FavoritItem filmFavorite) {
        ContentValues args = new ContentValues();
        args.put(_ID, filmFavorite.getId());
        args.put(NAME, filmFavorite.getName());
        args.put(OVERVIEW, filmFavorite.getOverview());
        return database.insert(DATABASE_TABLE, null, args);
    }

    public int deleteFilm(int id) {
        return database.delete(TABLE_NAME, _ID + "='" + id + "'", null);
    }
}
