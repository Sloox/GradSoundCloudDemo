package com.tomtom.gradsoundcloud.domain.favourites.datasource;

import com.tomtom.gradsoundcloud.domain.favourites.model.Tracks;

import java.util.List;

/**
 * Entry point on accessing User favourite tracks data for remote calls only
 * This different abstraction ensures that the Liskov Substitution Principle is held within SOLID
 */
public interface TracksRemoteDataSource {

    /**
     * Gets user favourite tracks.
     *
     * @param userId the user id
     * @return the user favourite tracks
     */
    List<Tracks> getUserFavouriteTracks(String userId);

}
