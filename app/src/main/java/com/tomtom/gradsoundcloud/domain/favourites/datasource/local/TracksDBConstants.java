package com.tomtom.gradsoundcloud.domain.favourites.datasource.local;


/**
 * Tracks database table & Row names
 */
public final class TracksDBConstants {

    public static final String TABLE_NAME = "tracks";
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_TRACKS_JSON_BLOB = "tracks";
    public static final String COLUMN_DATE_FETCHED_TIME = "timesync";

    public static final String[] TRACKS_SELECT = {
            COLUMN_NAME_ID, COLUMN_TRACKS_JSON_BLOB, COLUMN_DATE_FETCHED_TIME,
    };

}
