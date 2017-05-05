package com.tomtom.gradsoundcloud.domain.profile.datasource;

import android.support.annotation.NonNull;

import com.tomtom.gradsoundcloud.domain.profile.model.User;

/**
 * Entry point on accessing User data for remote calls only
 * This different abstraction ensures that the Liskov Substitution Principle is held within SOLID
 */
public interface UserRemoteDataSource {
    /**
     * Gets a user with a userId.
     *
     * @param userId the user id
     * @return the user
     */
    User getUser(@NonNull String userId);

}
