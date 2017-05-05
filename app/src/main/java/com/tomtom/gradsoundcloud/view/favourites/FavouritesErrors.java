package com.tomtom.gradsoundcloud.view.favourites;

import com.tomtom.gradsoundcloud.util.BaseError;


/**
 * Concrete Error states relating to Favourites/Tracks
 * These error states that allow the UI to update accordingly
 * @see com.tomtom.gradsoundcloud.util.BaseError
 */
public enum FavouritesErrors implements BaseError {
    FAILED_RETRIEVE_TRACKS, NETWORK_ERROR, NULL_TRACKS
}
