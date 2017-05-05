package com.tomtom.gradsoundcloud.domain.favourites.datasource.remote;


/**
 * The interface that helps setup the soundcloud network calls for tracks
 */
public interface TracksSoundCloudService {
    /**
     * The constant SERVICE_ENDPOINT.
     */
    String SERVICE_ENDPOINT = "http://api.soundcloud.com/";
    /**
     * The constant SERVICE_REST_PARAM.
     */
    String SERVICE_REST_PARAM = "users/{user}/favorites";

}
