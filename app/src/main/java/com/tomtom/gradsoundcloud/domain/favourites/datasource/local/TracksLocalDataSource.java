package com.tomtom.gradsoundcloud.domain.favourites.datasource.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tomtom.gradsoundcloud.domain.favourites.model.Tracks;
import com.tomtom.gradsoundcloud.domain.favourites.datasource.TracksDataSource;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Concrete Implementation to load the Tracks data from local storage (SQLite)
 * All implementations will consider local storage only where applicable
 *
 * @see TracksDataSource
 */
public class TracksLocalDataSource implements TracksDataSource {
    private static String TAG = TracksLocalDataSource.class.getSimpleName();

    private static TracksLocalDataSource INSTANCE;
    private TracksDBHelper mDbHelper;
    private SQLiteDatabase mDb;

    /**
     * Prevent direct construction, except through getinstance
     */
    private TracksLocalDataSource(@NonNull Context context) {
        mDbHelper = new TracksDBHelper(context);
        mDb = mDbHelper.getWritableDatabase();
    }

    /**
     * Gets instance.
     *
     * @param context the context
     * @return the instance
     */
    public static TracksLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new TracksLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public List<Tracks> getUserFavouriteTracks(String userId) {
        try {
            List<Tracks> dbTracks = null;
            String tracksSelection = TracksDBConstants.COLUMN_NAME_ID + " LIKE ?";
            Cursor cursor =
                    mDb.query(TracksDBConstants.TABLE_NAME, TracksDBConstants.TRACKS_SELECT, tracksSelection, new String[]{userId},
                            null, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                String ttl = cursor.getString(cursor.getColumnIndexOrThrow(TracksDBConstants.COLUMN_DATE_FETCHED_TIME));
                if (checkTimeStamp(ttl)) {
                    String jsonBlob = cursor.getString(cursor.getColumnIndexOrThrow(TracksDBConstants.COLUMN_TRACKS_JSON_BLOB));
                    Gson gson = new Gson();
                    Type listOfTracks = new TypeToken<List<Tracks>>() {
                    }.getType();
                    dbTracks = gson.fromJson(jsonBlob, listOfTracks);
                }
            }

            if (cursor != null) {
                cursor.close();
            }
            return dbTracks;
        } catch (IllegalStateException e) {
            Log.e(TAG, "Failed to get user tracks:", e);
        } catch (RuntimeException e) {
            Log.e(TAG, "Failed to get user tracks:", e);
        }
        return null;
    }

    private boolean checkTimeStamp(String timestamp) {
        try {

            long diff = System.currentTimeMillis() - Long.valueOf(timestamp);
            long diffMinutes = diff / (60L * 1000L) % 60L;
            if (diffMinutes <= 2) {
                return true;
            }

        } catch (NumberFormatException nfe) {
            return false;
        }
        Log.i(TAG, "Tracks data is out of date & will be refreshed");
        return false;
    }

    @Override
    public void saveUserTracks(List<Tracks> userTracks, String userId) {
        Gson gson = new Gson();
        try {
            String jsonBlob = gson.toJson(userTracks);
            ContentValues values = new ContentValues();
            values.put(TracksDBConstants.COLUMN_NAME_ID, userId);
            values.put(TracksDBConstants.COLUMN_TRACKS_JSON_BLOB, jsonBlob);
            values.put(TracksDBConstants.COLUMN_DATE_FETCHED_TIME, String.valueOf(System.currentTimeMillis()));
            mDb.insert(TracksDBConstants.TABLE_NAME, null, values);
        } catch (IllegalStateException e) {
            Log.e(TAG, "Failed to save to DB", e);
        }
    }

    @Override
    public void deleteUserTracks(String userId) {
        try {
            String selection = TracksDBConstants.COLUMN_NAME_ID + " LIKE ?";
            String[] selectionArgs = {userId};

            mDb.delete(TracksDBConstants.TABLE_NAME, selection, selectionArgs);
        } catch (IllegalStateException e) {
            Log.e(TAG, "Failed to delete from DB:", e);
        }
    }

    @Override
    public void refreshTracks() {
        //not applicable at present
    }
}
