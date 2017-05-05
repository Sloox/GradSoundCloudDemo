package com.tomtom.gradsoundcloud.presenter.favourites;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.tomtom.gradsoundcloud.view.BaseView;
import com.tomtom.gradsoundcloud.domain.favourites.model.Tracks;
import com.tomtom.gradsoundcloud.domain.favourites.datasource.FavouritesLoader;
import com.tomtom.gradsoundcloud.domain.favourites.datasource.TracksRepo;
import com.tomtom.gradsoundcloud.util.BaseError;
import com.tomtom.gradsoundcloud.view.favourites.FavouritesErrors;

import java.util.List;

/**
 * The Concrete Favourites presenter implementation that implmenets the FavouritesPresenter interface & Loadermanager callbacks
 *
 * @see android.support.v4.app.LoaderManager.LoaderCallbacks
 * @see FavourtiesPresenter
 */
public class FavouritesPresenterImpl implements FavourtiesPresenter, LoaderManager.LoaderCallbacks<List<Tracks>> {
    private final static String TAG = FavouritesPresenterImpl.class.getSimpleName();

    private final static int TRACKS_QUERY = 2;
    private final LoaderManager mLoaderManager;
    private final FavouritesView fView;
    private final FavouritesLoader loader;
    private final TracksRepo tracksRepo;
    private List<Tracks> tracksList;

    /**
     * Instantiates a new Favourites presenter.
     *
     * @param mLoaderManager the m loader manager
     * @param fView          the f view
     * @param loader         the loader
     * @param tracksRepo     the tracks repo
     */
    public FavouritesPresenterImpl(LoaderManager mLoaderManager, FavouritesView fView, FavouritesLoader loader, TracksRepo tracksRepo) {
        this.mLoaderManager = mLoaderManager;
        this.fView = fView;
        this.loader = loader;
        this.tracksRepo = tracksRepo;
        fView.setPresenter(this);
    }

    @Override
    public void start() {
        if (mLoaderManager.getLoader(TRACKS_QUERY) == null) {
            mLoaderManager.initLoader(TRACKS_QUERY, null, this).forceLoad();
        } else {
            tracksRepo.refreshTracks();
            mLoaderManager.destroyLoader(TRACKS_QUERY);
            mLoaderManager.initLoader(TRACKS_QUERY, null, this).forceLoad();
        }
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        fView.setLoadingIndicator(true);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<List<Tracks>> loader, List<Tracks> data) {
        fView.setLoadingIndicator(false);
        tracksList = data;
        showFavouriteTracks();
    }

    @Override
    public void onLoaderReset(Loader loader) {
    }


    @Override
    public void showFavouriteTracks() {
        if (tracksList == null || tracksList.isEmpty()) {
            fView.showFailedtoGetTracks(FavouritesErrors.NULL_TRACKS);
        } else if (tracksList.size() == 1 && tracksList.get(0).getFavouritesErrors() != null) {
            fView.showFailedtoGetTracks(tracksList.get(0).getFavouritesErrors());
        } else {
            fView.showTracks(tracksList);
            fView.hideSnackBar();
        }
    }

    @Override
    public void refreshtracks() {
        start();
    }


    /**
     * The interface Favourites view.
     */
    public interface FavouritesView extends BaseView<FavouritesPresenterImpl> {
        /**
         * Sets loading indicator for the ui.
         *
         * @param active the active
         */
        void setLoadingIndicator(boolean active);

        /**
         * Hide snack bar.
         */
        void hideSnackBar();

        /**
         * Show error states on the UI.
         *
         * @param error the error
         */
        void showFailedtoGetTracks(BaseError error);

        /**
         * Show tracks for a selected user.
         *
         * @param trackses the trackses
         */
        void showTracks(List<Tracks> trackses);

        /**
         * Refresh tracks.
         */
        void refreshTracks();
    }
}
