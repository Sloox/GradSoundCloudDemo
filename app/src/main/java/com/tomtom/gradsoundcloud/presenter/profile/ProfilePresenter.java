package com.tomtom.gradsoundcloud.presenter.profile;

import com.tomtom.gradsoundcloud.presenter.BasePresenter;

/**
 * Presenter interface that defines the contract between the UI & model for the Profile/User
 * @see BasePresenter
 */
public interface ProfilePresenter extends BasePresenter {
    /**
     * Show add new user.
     * Shows a dialog that will add a new user
     */
    void showAddNewUser();

    /**
     * Try add new user.
     * attempts to add a user & update the ui
     *
     * @param id the id
     */
    void tryAddNewUser(int id);

    /**
     * Remove selected user.
     */
    void removeSelectedUser();

    /**
     * Show user.
     * allows the user to be shown on the ui
     */
    void showUser();

    /**
     * Refresh user.
     * forces a refresh of the data in the model
     */
    void refreshUser();
}
