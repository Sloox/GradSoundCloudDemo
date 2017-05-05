package com.tomtom.gradsoundcloud.domain.profile.datasource;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.tomtom.gradsoundcloud.domain.profile.model.User;

/**
 * Custom Loader to load the User data from a cache (DB) & Remotely if neccessary
 * Does this Asynchronously so the implementation can run "sequentially"
 */
public class ProfileLoader extends AsyncTaskLoader<User> implements UserRepo.UserRepoObserver {

    private static final String TAG = ProfileLoader.class.getSimpleName();
    private UserRepo userRepo;

    /**
     * Instantiates a new Profile loader.
     *
     * @param context  the context
     * @param userRepo the user repo
     */
    public ProfileLoader(Context context, UserRepo userRepo) {
        super(context);
        this.userRepo = userRepo;
    }

    @Override
    public User loadInBackground() {
        return userRepo.getSelectedUser();
    }

    @Override
    public void deliverResult(User data) {
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
        userRepo.addContentObserver(this);
        //force a load if it has changed
        if (takeContentChanged()) {
            forceLoad();
        }

    }

    @Override
    protected void onReset() {
        onStopLoading();
        userRepo.removeContentObserver(this);
    }

    /**
     * A way to trigger the change of data
     */
    @Override
    public void onUserInfoChange() {
        if (isStarted()) {
            forceLoad();
        }
    }
}
