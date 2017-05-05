package com.tomtom.gradsoundcloud.domain.profile.datasource.local;

/**
 * USER database table & Row names
 */
public final class UserDBConstants {
    public static final String TABLE_NAME = "user";


    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_PERMALINK = "permalink";
    public static final String COLUMN_NAME_USERNAME = "username";
    public static final String COLUMN_NAME_URI = "uri";
    public static final String COLUMN_NAME_PERMALINK_URI = "permalink_url";
    public static final String COLUMN_NAME_AVATAR_URL = "avatar_url";
    public static final String COLUMN_NAME_COUNTRY = "country";
    public static final String COLUMN_NAME_FULL_NAME = "full_name";
    public static final String COLUMN_NAME_CITY = "city";
    public static final String COLUMN_NAME_DESCRIPTION = "description";
    public static final String COLUMN_NAME_DISCOGS_NAME = "discogs_name";
    public static final String COLUMN_NAME_MYSPACE_NAME = "myspace_name";
    public static final String COLUMN_NAME_WEBSITE = "website";
    public static final String COLUMN_NAME_WEBSITE_TITLE = "website_title";
    public static final String COLUMN_NAME_ONLINE = "online";
    public static final String COLUMN_NAME_TRACK_COUNT = "track_count";
    public static final String COLUMN_NAME_PLAYLIST_COUNT = "playlist_count";
    public static final String COLUMN_NAME_FOLLOWERS_COUNT = "followers_count";
    public static final String COLUMN_NAME_FOLLOWINGS_COUNT = "followings_count";
    public static final String COLUMN_NAME_PUBLIC_FAVOURITES_COUNT = "public_favorites_count";
    public static final String COLUMN_DATE_FETCHED_TIME = "timesync";

    public static final String[] UserSelect = {
            COLUMN_NAME_ID,
            COLUMN_NAME_PERMALINK,
            COLUMN_NAME_USERNAME,
            COLUMN_NAME_URI,
            COLUMN_NAME_PERMALINK_URI,
            COLUMN_NAME_AVATAR_URL,
            COLUMN_NAME_COUNTRY,
            COLUMN_NAME_FULL_NAME,
            COLUMN_NAME_CITY,
            COLUMN_NAME_DESCRIPTION,
            COLUMN_NAME_DISCOGS_NAME,
            COLUMN_NAME_MYSPACE_NAME,
            COLUMN_NAME_WEBSITE,
            COLUMN_NAME_WEBSITE_TITLE,
            COLUMN_NAME_ONLINE,
            COLUMN_NAME_TRACK_COUNT,
            COLUMN_NAME_PLAYLIST_COUNT,
            COLUMN_NAME_FOLLOWERS_COUNT,
            COLUMN_NAME_FOLLOWINGS_COUNT,
            COLUMN_NAME_PUBLIC_FAVOURITES_COUNT,
            COLUMN_DATE_FETCHED_TIME,
    };


}
