package com.tomtom.gradsoundcloud.domain.profile.datasource;

import android.support.annotation.NonNull;

import com.tomtom.gradsoundcloud.domain.profile.model.User;

/**
 * Entry point on accessing User data from a generic standpoint
 *
 */
public interface UserDataSource {

    /**
     * Gets a user with a userId.
     *
     * @param userId the user id
     * @return the user
     */
    User getUser(@NonNull String userId);

    /**
     * Gets the selected user.
     *
     * @return the user
     */
    User getSelectedUser();

    /**
     * Save user.
     *
     * @param user the user
     */
    void saveUser(@NonNull User user);

    /**
     * Delete user with a userId.
     *
     * @param userId the user id
     */
    void deleteUser(@NonNull String userId);

    /**
     * Delete selected user.
     */
    void deleteSelectedUser();

    /**
     * Refresh user.
     */
    void refreshUser();


    /**
     * Change selected user.
     */
    void changeSelectedUser(String id);

}
