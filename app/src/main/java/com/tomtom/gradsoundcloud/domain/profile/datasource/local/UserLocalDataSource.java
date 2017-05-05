package com.tomtom.gradsoundcloud.domain.profile.datasource.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import com.tomtom.gradsoundcloud.domain.profile.model.User;
import com.tomtom.gradsoundcloud.domain.profile.datasource.UserDataSource;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Concrete Implementation to load the User data from local storage (SQLite)
 * All implementations will consider local storage only where applicable
 * @see UserDataSource
 */
public class UserLocalDataSource implements UserDataSource {
    private static String TAG = UserLocalDataSource.class.getSimpleName();

    private static UserLocalDataSource INSTANCE;
    private UserDbHelper mDbHelper;
    private SQLiteDatabase mDb;

    /**
     * Prevent direct construction, except through getinstance
     */
    private UserLocalDataSource(@NonNull Context context) {
        checkNotNull(context);
        mDbHelper = new UserDbHelper(context);
        mDb = mDbHelper.getWritableDatabase();
    }

    /**
     * Gets instance.
     *
     * @param context the context
     * @return the instance
     */
    public static UserLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new UserLocalDataSource(context);
        }
        return INSTANCE;
    }


    @Override
    public User getUser(@NonNull String userId) {
        try {
            String userSelection = UserDBConstants.COLUMN_NAME_ID + " LIKE ?";
            Cursor cursor = mDb.query(UserDBConstants.TABLE_NAME, UserDBConstants.UserSelect, userSelection, new String[]{userId}, null, null, null);
            User dbUser = null;
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                String ttl = cursor.getString(cursor.getColumnIndexOrThrow(UserDBConstants.COLUMN_DATE_FETCHED_TIME));
                if (checkTimeStamp(ttl)) {
                    dbUser = getUserFromDB(cursor);
                }
            }

            if (cursor != null) {
                cursor.close();
            }
            return dbUser;

        } catch (IllegalStateException e) {
            Log.e(TAG, "Failed to get user:", e);
        }
        return null;
    }

    @Override
    public User getSelectedUser() {
        throw new UnsupportedOperationException("Getting a selected user is not supported at this level");
    }

    private boolean checkTimeStamp(String timestamp) {
        try {
            long diff = System.currentTimeMillis() - Long.valueOf(timestamp);
            long diffMinutes = diff / (60 * 1000) % 60;
            if (diffMinutes <= 2) {
                return true;
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
        Log.i(TAG, "Profile data is out of date & will be refreshed");
        return false;
    }

    /**
     * Retrieve a User from the db cursor
     */
    private User getUserFromDB(Cursor cursor) {
        String id = cursor.getString(cursor.getColumnIndexOrThrow(UserDBConstants.COLUMN_NAME_ID));
        String permalink = cursor.getString(cursor.getColumnIndexOrThrow(UserDBConstants.COLUMN_NAME_PERMALINK));
        String username = cursor.getString(cursor.getColumnIndexOrThrow(UserDBConstants.COLUMN_NAME_USERNAME));
        String uri = cursor.getString(cursor.getColumnIndexOrThrow(UserDBConstants.COLUMN_NAME_URI));
        String permalinkUrl = cursor.getString(cursor.getColumnIndexOrThrow(UserDBConstants.COLUMN_NAME_PERMALINK_URI));
        String avatarUrl = cursor.getString(cursor.getColumnIndexOrThrow(UserDBConstants.COLUMN_NAME_AVATAR_URL));
        String country = cursor.getString(cursor.getColumnIndexOrThrow(UserDBConstants.COLUMN_NAME_COUNTRY));
        String fullName = cursor.getString(cursor.getColumnIndexOrThrow(UserDBConstants.COLUMN_NAME_FULL_NAME));
        String city = cursor.getString(cursor.getColumnIndexOrThrow(UserDBConstants.COLUMN_NAME_CITY));
        String description = cursor.getString(cursor.getColumnIndexOrThrow(UserDBConstants.COLUMN_NAME_DESCRIPTION));
        String discogsName = cursor.getString(cursor.getColumnIndexOrThrow(UserDBConstants.COLUMN_NAME_DISCOGS_NAME));
        String myspaceName = cursor.getString(cursor.getColumnIndexOrThrow(UserDBConstants.COLUMN_NAME_MYSPACE_NAME));
        String website = cursor.getString(cursor.getColumnIndexOrThrow(UserDBConstants.COLUMN_NAME_WEBSITE));
        String websiteTitle = cursor.getString(cursor.getColumnIndexOrThrow(UserDBConstants.COLUMN_NAME_WEBSITE_TITLE));
        Boolean online = cursor.getInt(cursor.getColumnIndexOrThrow(UserDBConstants.COLUMN_NAME_ONLINE)) == 1;
        String trackCount = cursor.getString(cursor.getColumnIndexOrThrow(UserDBConstants.COLUMN_NAME_TRACK_COUNT));
        String playlistCount = cursor.getString(cursor.getColumnIndexOrThrow(UserDBConstants.COLUMN_NAME_PLAYLIST_COUNT));
        String followersCount = cursor.getString(cursor.getColumnIndexOrThrow(UserDBConstants.COLUMN_NAME_FOLLOWERS_COUNT));
        String followingsCount = cursor.getString(cursor.getColumnIndexOrThrow(UserDBConstants.COLUMN_NAME_FOLLOWINGS_COUNT));
        String publicFavoritesCount = cursor.getString(cursor.getColumnIndexOrThrow(UserDBConstants.COLUMN_NAME_PUBLIC_FAVOURITES_COUNT));
        return new User(id, permalink, username, uri, permalinkUrl, avatarUrl, country, fullName, city, description, discogsName, myspaceName, website, websiteTitle, online, trackCount, playlistCount, followersCount, followingsCount, publicFavoritesCount);
    }

    @Override
    public void saveUser(@NonNull User user) {
        try {
            checkNotNull(user);
            ContentValues values = new ContentValues();
            values.put(UserDBConstants.COLUMN_NAME_ID, user.getId());
            values.put(UserDBConstants.COLUMN_NAME_PERMALINK, user.getPermalink());
            values.put(UserDBConstants.COLUMN_NAME_USERNAME, user.getUsername());
            values.put(UserDBConstants.COLUMN_NAME_PERMALINK_URI, user.getPermalinkUrl());
            values.put(UserDBConstants.COLUMN_NAME_AVATAR_URL, user.getAvatarUrl());
            values.put(UserDBConstants.COLUMN_NAME_COUNTRY, user.getCountry());
            values.put(UserDBConstants.COLUMN_NAME_FULL_NAME, user.getFullName());
            values.put(UserDBConstants.COLUMN_NAME_CITY, user.getCity());
            values.put(UserDBConstants.COLUMN_NAME_DESCRIPTION, user.getDescription());
            values.put(UserDBConstants.COLUMN_NAME_DISCOGS_NAME, user.getDiscogsName());
            values.put(UserDBConstants.COLUMN_NAME_MYSPACE_NAME, user.getMyspaceName());
            values.put(UserDBConstants.COLUMN_NAME_WEBSITE, user.getWebsite());
            values.put(UserDBConstants.COLUMN_NAME_WEBSITE_TITLE, user.getWebsiteTitle());
            values.put(UserDBConstants.COLUMN_NAME_ONLINE, user.getOnline());
            values.put(UserDBConstants.COLUMN_NAME_TRACK_COUNT, user.getTrackCount());
            values.put(UserDBConstants.COLUMN_NAME_PLAYLIST_COUNT, user.getPlaylistCount());
            values.put(UserDBConstants.COLUMN_NAME_FOLLOWERS_COUNT, user.getFollowersCount());
            values.put(UserDBConstants.COLUMN_NAME_FOLLOWINGS_COUNT, user.getFollowingsCount());
            values.put(UserDBConstants.COLUMN_NAME_PUBLIC_FAVOURITES_COUNT, user.getPublicFavoritesCount());
            values.put(UserDBConstants.COLUMN_DATE_FETCHED_TIME, String.valueOf(System.currentTimeMillis()));
            mDb.insert(UserDBConstants.TABLE_NAME, null, values);
        } catch (IllegalStateException e) {
            Log.e(TAG, "Failed to save to DB", e);
        }
    }

    @Override
    public void deleteUser(@NonNull String userId) {
        try {
            String selection = UserDBConstants.COLUMN_NAME_ID + " LIKE ?";
            String[] selectionArgs = {userId};

            mDb.delete(UserDBConstants.TABLE_NAME, selection, selectionArgs);
        } catch (IllegalStateException e) {
            Log.e(TAG, "Failed to delete from DB:", e);
        }
    }

    @Override
    public void deleteSelectedUser() {
        throw new UnsupportedOperationException("Deleting a selected user is not supported at this level");
    }

    @Override
    public void refreshUser() {
        //no applicable
    }

    @Override
    public void changeSelectedUser(String id) {
        throw new UnsupportedOperationException("Changing a selected user is not supported at this level");
    }
}
