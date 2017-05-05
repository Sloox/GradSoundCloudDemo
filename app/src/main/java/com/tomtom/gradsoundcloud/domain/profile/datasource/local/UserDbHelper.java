package com.tomtom.gradsoundcloud.domain.profile.datasource.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * User DB Helper that extends a SQLiteOpenHelper to store user in a relational form
 * @see SQLiteOpenHelper
 */
public class UserDbHelper extends SQLiteOpenHelper {

    /**
     * The constant DATABASE_VERSION.
     */
    public static final int DATABASE_VERSION = 1;

    /**
     * The constant DATABASE_NAME.
     */
    public static final String DATABASE_NAME = "User.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String BOOLEAN_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UserDBConstants.TABLE_NAME + " (" +
                    UserDBConstants.COLUMN_NAME_ID + TEXT_TYPE + " PRIMARY KEY," +
                    UserDBConstants.COLUMN_NAME_PERMALINK + TEXT_TYPE + COMMA_SEP +
                    UserDBConstants.COLUMN_NAME_USERNAME + TEXT_TYPE + COMMA_SEP +
                    UserDBConstants.COLUMN_NAME_URI + TEXT_TYPE + COMMA_SEP +
                    UserDBConstants.COLUMN_NAME_PERMALINK_URI + TEXT_TYPE + COMMA_SEP +
                    UserDBConstants.COLUMN_NAME_AVATAR_URL + TEXT_TYPE + COMMA_SEP +
                    UserDBConstants.COLUMN_NAME_COUNTRY + TEXT_TYPE + COMMA_SEP +
                    UserDBConstants.COLUMN_NAME_FULL_NAME + TEXT_TYPE + COMMA_SEP +
                    UserDBConstants.COLUMN_NAME_CITY + TEXT_TYPE + COMMA_SEP +
                    UserDBConstants.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    UserDBConstants.COLUMN_NAME_DISCOGS_NAME + TEXT_TYPE + COMMA_SEP +
                    UserDBConstants.COLUMN_NAME_MYSPACE_NAME + TEXT_TYPE + COMMA_SEP +
                    UserDBConstants.COLUMN_NAME_WEBSITE + TEXT_TYPE + COMMA_SEP +
                    UserDBConstants.COLUMN_NAME_WEBSITE_TITLE + TEXT_TYPE + COMMA_SEP +
                    UserDBConstants.COLUMN_NAME_ONLINE + BOOLEAN_TYPE + COMMA_SEP +
                    UserDBConstants.COLUMN_NAME_TRACK_COUNT + TEXT_TYPE + COMMA_SEP +
                    UserDBConstants.COLUMN_NAME_PLAYLIST_COUNT + TEXT_TYPE + COMMA_SEP +
                    UserDBConstants.COLUMN_NAME_FOLLOWERS_COUNT + TEXT_TYPE + COMMA_SEP +
                    UserDBConstants.COLUMN_NAME_FOLLOWINGS_COUNT + TEXT_TYPE + COMMA_SEP +
                    UserDBConstants.COLUMN_NAME_PUBLIC_FAVOURITES_COUNT + TEXT_TYPE + COMMA_SEP +
                    UserDBConstants.COLUMN_DATE_FETCHED_TIME + TEXT_TYPE +
                    " )";

    /**
     * Instantiates a new User db helper.
     *
     * @param context the context
     */
    public UserDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //not required
    }
}
