package com.tomtom.gradsoundcloud.domain.profile.datasource.remote;

/**
 * The interface that helps setup the soundcloud network calls for User
 */
public interface UserSoundCloudService {
    /**
     * The constant SERVICE_ENDPOINT.
     */
    String SERVICE_ENDPOINT = "http://api.soundcloud.com/";
    /**
     * The constant SERVICE_REST_PARAM.
     */
    String SERVICE_REST_PARAM = "users/";

}
