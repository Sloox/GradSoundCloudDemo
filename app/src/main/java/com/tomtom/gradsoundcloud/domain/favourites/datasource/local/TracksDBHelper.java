package com.tomtom.gradsoundcloud.domain.favourites.datasource.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Tracks DB Helper that extends a SQLiteOpenHelper to store user tracks as a blob
 *
 * @see SQLiteOpenHelper
 */
public class TracksDBHelper extends SQLiteOpenHelper {

    /**
     * The constant DATABASE_VERSION.
     */
    public static final int DATABASE_VERSION = 1;

    /**
     * The constant DATABASE_NAME.
     */
    public static final String DATABASE_NAME = "Tracks.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String BOOLEAN_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TracksDBConstants.TABLE_NAME + " (" +
                    TracksDBConstants.COLUMN_NAME_ID + TEXT_TYPE + " PRIMARY KEY," +
                    TracksDBConstants.COLUMN_TRACKS_JSON_BLOB + TEXT_TYPE + COMMA_SEP +
                    TracksDBConstants.COLUMN_DATE_FETCHED_TIME + TEXT_TYPE +
                    " )";

    /**
     * Instantiates a new Tracks db helper.
     *
     * @param context the context
     */
    public TracksDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
