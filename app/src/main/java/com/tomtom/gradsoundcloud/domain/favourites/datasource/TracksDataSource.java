package com.tomtom.gradsoundcloud.domain.favourites.datasource;

import com.tomtom.gradsoundcloud.domain.favourites.model.Tracks;

import java.util.List;


/**
 * The interface that defines Tracks data source and its corresponding actions
 */
public interface TracksDataSource {
    /**
     * Gets user favourite tracks.
     *
     * @param userId the user id
     * @return the user favourite tracks
     */
    List<Tracks> getUserFavouriteTracks(String userId);

    /**
     * Save user tracks.
     *
     * @param userTracks the user tracks
     * @param userId     the user id
     */
    void saveUserTracks(List<Tracks> userTracks, String userId);

    /**
     * Delete user tracks.
     *
     * @param userId the user id
     */
    void deleteUserTracks(String userId);

    /**
     * Refresh tracks.
     */
    void refreshTracks();
}
