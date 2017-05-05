package com.tomtom.gradsoundcloud.presenter.profile;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.tomtom.gradsoundcloud.view.BaseView;
import com.tomtom.gradsoundcloud.domain.profile.model.User;
import com.tomtom.gradsoundcloud.domain.profile.datasource.ProfileLoader;
import com.tomtom.gradsoundcloud.domain.profile.datasource.UserRepo;
import com.tomtom.gradsoundcloud.util.BaseError;

/**
 * The Concrete Profile presenter implementation that implements Presenter superclass &  a Loadermanager callbacks
 *
 * @see android.support.v4.app.LoaderManager.LoaderCallbacks
 * @see ProfilePresenter
 */
public final class ProfilePresenterImpl implements ProfilePresenter, LoaderManager.LoaderCallbacks<User> {

    private final static int PROFILE_QUERY = 1;
    private static final String TAG = ProfilePresenterImpl.class.getSimpleName();
    private final LoaderManager mLoaderManager;
    private final ProfileView pView;
    private final ProfileLoader loader;
    private User currentUser;
    private final UserRepo userRepo;

    /**
     * Instantiates a new Profile presenter.
     *
     * @param loader         the loader
     * @param mLoaderManager the m loader manager
     * @param pView          the p view
     * @param userRepo       the user repo
     */
    public ProfilePresenterImpl(@NonNull ProfileLoader loader, @NonNull LoaderManager mLoaderManager, @NonNull ProfileView pView, @NonNull UserRepo userRepo) {
        this.loader = loader;
        this.mLoaderManager = mLoaderManager;
        this.userRepo = userRepo;
        this.pView = pView;
        pView.setPresenter(this);
    }

    @Override
    public void start() {
        if (mLoaderManager.getLoader(PROFILE_QUERY) == null) {
            mLoaderManager.initLoader(PROFILE_QUERY, null, this).forceLoad();
        } else {
            userRepo.refreshUser();
            mLoaderManager.destroyLoader(PROFILE_QUERY);
            mLoaderManager.initLoader(PROFILE_QUERY, null, this).forceLoad();
        }

    }

    @Override
    public Loader<User> onCreateLoader(int id, Bundle args) {
        pView.setLoadingIndicator(true);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<User> loader, User data) {
        pView.setLoadingIndicator(false);
        currentUser = data;
        showUser();
    }

    @Override
    public void onLoaderReset(Loader loader) {
    }

    @Override
    public void showAddNewUser() {
        pView.showAddNewUser();//delegate this to the view
    }

    @Override
    public void tryAddNewUser(int id) {
        pView.setLoadingIndicator(true);
        loader.cancelLoad();
        loader.cancelLoadInBackground();
        userRepo.changeSelectedUser(String.valueOf(id));
        userRepo.refreshUser();
    }

    @Override
    public void removeSelectedUser() {
        pView.setLoadingIndicator(true);
        userRepo.deleteSelectedUser();
        userRepo.refreshUser();
    }

    @Override
    public void showUser() {
        if (currentUser != null && currentUser.getError() != null) {
            pView.showFailedtoUpdate(currentUser.getError());
        } else {
            pView.showUser(currentUser);
            pView.hideSnackBar();
        }
    }

    @Override
    public void refreshUser() {
        pView.setLoadingIndicator(true);
        start();
    }

    /**
     * The interface Profile view.
     */
    public interface ProfileView extends BaseView<ProfilePresenterImpl> {
        /**
         * Sets loading indicator.
         *
         * @param active the active
         */
        void setLoadingIndicator(boolean active);

        /**
         * Hide snack bar.
         */
        void hideSnackBar();

        /**
         * Goto profile tracks.
         *
         * @param uID the u id
         */
        void gotoProfileTracks(String uID);

        /**
         * Show user.
         *
         * @param user the user
         */
        void showUser(User user);

        /**
         * Show failedto update.
         *
         * @param error the error
         */
        void showFailedtoUpdate(BaseError error);

        /**
         * Show add new user.
         */
        void showAddNewUser();

        /**
         * Remove selected user.
         */
        void removeSelectedUser();

        /**
         * Refresh profile.
         */
        void refreshProfile();
    }


}
