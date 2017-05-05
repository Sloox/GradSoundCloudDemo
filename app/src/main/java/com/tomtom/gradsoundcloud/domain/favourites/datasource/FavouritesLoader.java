package com.tomtom.gradsoundcloud.domain.favourites.datasource;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.tomtom.gradsoundcloud.domain.favourites.model.Tracks;

import java.util.List;

/**
 * Custom Loader to load the Favourite tracks of a user from a cache (DB) or from the Network
 * Does this Asynchronously so the implementation can run "sequentially"
 */
public class FavouritesLoader extends AsyncTaskLoader<List<Tracks>> implements TracksRepo.TracksRepoObserver {
    private static final String TAG = FavouritesLoader.class.getSimpleName();

    private TracksRepo tracksRepo;
    private String userID;

    /**
     * Instantiates a new Favourites loader.
     *
     * @param context    the context
     * @param userID     the user id
     * @param tracksRepo the tracks repo
     */
    public FavouritesLoader(Context context, String userID, TracksRepo tracksRepo) {
        super(context);
        this.userID = userID;
        this.tracksRepo = tracksRepo;
    }

    @Override
    public List<Tracks> loadInBackground() {
        return tracksRepo.getUserFavouriteTracks(userID);
    }

    @Override
    public void deliverResult(List<Tracks> data) {
        if (isReset()) {
            return;
        }
        if (isStarted()) {
            super.deliverResult(data);
        }
    }

    @Override
    protected void onStartLoading() {
        //begin monotoring the data source
        tracksRepo.addContentObserver(this);
        //force a load if it has changed
        if (takeContentChanged()) {
            forceLoad();
        }

    }

    @Override
    protected void onReset() {
        onStopLoading();
        tracksRepo.removeContentObserver(this);
    }

    @Override
    public void onTracksChanged() {
        if (isStarted()) {
            forceLoad();
        }
    }
}
