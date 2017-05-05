package com.tomtom.gradsoundcloud.domain.profile.datasource;

import android.support.annotation.NonNull;

import com.tomtom.gradsoundcloud.domain.profile.model.User;
import com.tomtom.gradsoundcloud.view.profile.ProfileErrors;
import com.tomtom.gradsoundcloud.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Repository layer that decides whether to get data from a cache or from network
 * Implements UserDataSource which allows it to delegate the operations to the actual implementations
 *
 * @see UserDataSource
 * @see com.tomtom.gradsoundcloud.domain.profile.datasource.local.UserLocalDataSource
 * @see UserRemoteDataSource
 */
public class UserRepo implements UserDataSource {

    private static final String TAG = UserRepo.class.getSimpleName();
    private static UserRepo INSTANCE = null;
    private final UserDataSource mUserLocalDataSource;
    private final UserRemoteDataSource mUserRemoteDataSource;
    private String selectedUserId;

    private List<UserRepoObserver> mObservers = new ArrayList<UserRepoObserver>();

    /**
     * Gets single instance of class.
     *
     * @param localDataSource  the tasks local data source
     * @param remoteDataSource the remote data source
     * @param selectedUserId   the selected user id
     * @return the instance
     */
    public static UserRepo getInstance(UserDataSource localDataSource, UserRemoteDataSource remoteDataSource, String selectedUserId) {
        if (INSTANCE == null) {
            INSTANCE = new UserRepo(localDataSource, remoteDataSource, selectedUserId);
        }
        return INSTANCE;
    }


    private UserRepo(@NonNull UserDataSource userDataSource, UserRemoteDataSource mUserRemoteDataSource, String selectedUserId) {
        this.mUserRemoteDataSource = mUserRemoteDataSource;
        this.selectedUserId = selectedUserId;
        mUserLocalDataSource = checkNotNull(userDataSource);
    }


    /**
     * Add observers to the repo, used for ui updates
     *
     * @param observer the observer
     */
    public void addContentObserver(UserRepoObserver observer) {
        if (!mObservers.contains(observer)) {
            mObservers.add(observer);
        }
    }

    /**
     * Removes Content observers from repo
     *
     * @param observer the observer
     */
    public void removeContentObserver(UserRepoObserver observer) {
        if (mObservers.contains(observer)) {
            mObservers.remove(observer);
        }
    }

    /**
     * Notifies a observers a change has occured
     * Allows UI to be updated
     */
    private void notifyContentObserver() {
        for (UserRepoObserver observer : mObservers) {
            observer.onUserInfoChange();
        }
    }

    @Override
    public User getUser(@NonNull String userId) {
        checkNotNull(userId);
        User user = mUserLocalDataSource.getUser(userId);
        if (user == null) {
            user = mUserRemoteDataSource.getUser(userId);
            if (user != null && user.getError() != null) {
                return user;
            } else if (user != null) {
                //user isnt null, lets make a local copy
                mUserLocalDataSource.deleteUser(userId);//delete if exists
                mUserLocalDataSource.saveUser(user);//save new
            }
        }
        if (user == null) {
            return new User(ProfileErrors.NULL_USER);
        }
        //change the user to selected user
        if (StringUtil.isNullorEmpty(selectedUserId)) {
            selectedUserId = userId;
        }
        return user;
    }

    @Override
    public User getSelectedUser() {
        if (StringUtil.isNullorEmpty(selectedUserId)) {
            return new User(ProfileErrors.NO_USER_SELECTED);
        }
        return getUser(selectedUserId);
    }

    @Override
    public void saveUser(@NonNull User user) {
        checkNotNull(user);
        mUserLocalDataSource.saveUser(user);
        notifyContentObserver();
    }

    @Override
    public void deleteUser(@NonNull String userId) {
        checkNotNull(userId);
        mUserLocalDataSource.deleteUser(userId);
        notifyContentObserver();
    }

    @Override
    public void deleteSelectedUser() {
        if (StringUtil.isNullorEmpty(selectedUserId)) {
            return;
        }
        deleteUser(selectedUserId);
        selectedUserId = null;
    }

    @Override
    public void refreshUser() {
        notifyContentObserver();
    }

    @Override
    public void changeSelectedUser(String id) {
        if (StringUtil.isNotNullAndNotEmpty(id)) {
            selectedUserId = id;
        }
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    /**
     * The interface User repo observer.
     */
    public interface UserRepoObserver {

        /**
         * On tasks changed.
         */
        void onUserInfoChange();

    }
}
