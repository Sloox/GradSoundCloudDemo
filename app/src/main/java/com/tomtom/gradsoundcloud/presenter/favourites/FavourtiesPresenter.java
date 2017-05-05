package com.tomtom.gradsoundcloud.presenter.favourites;

import com.tomtom.gradsoundcloud.presenter.BasePresenter;

/**
 * Presenter interface that defines the contract between the UI & model for the Favourites/Tracks
 * @see BasePresenter
 */
public interface FavourtiesPresenter extends BasePresenter {

    /**
     * Show favourite tracks.
     * updates the ui in the view
     */
    void showFavouriteTracks();

    /**
     * Refreshtracks.
     * used to refresh the UI components
     */
    void refreshtracks();

}
