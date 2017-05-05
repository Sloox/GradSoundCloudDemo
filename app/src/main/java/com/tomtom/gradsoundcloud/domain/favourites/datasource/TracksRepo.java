package com.tomtom.gradsoundcloud.domain.favourites.datasource;

import com.tomtom.gradsoundcloud.domain.favourites.model.Tracks;

import java.util.ArrayList;
import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Repository layer that decides whether to get data from a cache or from network
 *
 * @see TracksDataSource
 */
public class TracksRepo implements TracksDataSource {
    private static final String TAG = TracksRepo.class.getSimpleName();

    private static TracksRepo INSTANCE = null;
    private final TracksRemoteDataSource fRemoteDataSource;
    private final TracksDataSource fLocalDataSource;

    private List<TracksRepoObserver> mObservers = new ArrayList<TracksRepoObserver>();


    /**
     * Gets single instance of class.
     *
     * @param localDataSource  the tasks local data source
     * @param remoteDataSource the remote data source
     * @return the instance
     */
    public static TracksRepo getInstance(TracksDataSource localDataSource, TracksRemoteDataSource remoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TracksRepo(localDataSource, remoteDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    /**
     * Prevent construction except through getInstance
     */
    private TracksRepo(TracksDataSource fLocalDataSource, TracksRemoteDataSource fRemoteDataSource) {
        this.fRemoteDataSource = fRemoteDataSource;
        this.fLocalDataSource = fLocalDataSource;
    }

    /**
     * Add observers to the repo, used for ui updates
     *
     * @param observer the observer
     */
    public void addContentObserver(TracksRepoObserver observer) {
        if (!mObservers.contains(observer)) {
            mObservers.add(observer);
        }
    }

    /**
     * Removes Content observers from repo
     *
     * @param observer the observer
     */
    public void removeContentObserver(TracksRepoObserver observer) {
        if (mObservers.contains(observer)) {
            mObservers.remove(observer);
        }
    }

    /**
     * Notifies observers a change has occured
     * Allows UI to be updated
     */
    private void notifyContentObserver() {
        for (TracksRepoObserver observer : mObservers) {
            observer.onTracksChanged();
        }
    }


    @Override
    public List<Tracks> getUserFavouriteTracks(String userId) {
        checkNotNull(userId);
        List<Tracks> dbTracks = fLocalDataSource.getUserFavouriteTracks(userId);
        if (dbTracks == null || dbTracks.isEmpty()) {
            List<Tracks> newTracks = fRemoteDataSource.getUserFavouriteTracks(userId);

            if(newTracks!=null && newTracks.size() == 1 && newTracks.get(0).getFavouritesErrors() != null) {
                return newTracks; //error occured
            }else  if (newTracks != null && !newTracks.isEmpty()) {
                deleteUserTracks(userId);
                saveUserTracks(newTracks, userId);//save a local copy
                return newTracks;
            }
        }
        return dbTracks;
    }

    @Override
    public void saveUserTracks(List<Tracks> userTracks, String userId) {
        checkNotNull(userTracks);
        checkNotNull(userId);
        fLocalDataSource.saveUserTracks(userTracks, userId);
    }

    @Override
    public void deleteUserTracks(String userId) {
        checkNotNull(userId);
        fLocalDataSource.deleteUserTracks(userId);
    }

    @Override
    public void refreshTracks() {
        notifyContentObserver();
    }

    /**
     * The interface Tracks repo observer.
     */
    public interface TracksRepoObserver {

        /**
         * On tracks changed.
         */
        void onTracksChanged();

    }
}
