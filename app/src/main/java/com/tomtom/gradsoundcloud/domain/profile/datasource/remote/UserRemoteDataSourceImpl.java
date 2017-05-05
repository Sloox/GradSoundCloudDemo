package com.tomtom.gradsoundcloud.domain.profile.datasource.remote;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.tomtom.gradsoundcloud.R;
import com.tomtom.gradsoundcloud.domain.profile.model.User;
import com.tomtom.gradsoundcloud.domain.profile.datasource.UserRemoteDataSource;
import com.tomtom.gradsoundcloud.view.profile.ProfileErrors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Concrete Implementation to load the User data remotely (Network)
 * All implementations will consider retriving the user from the Network
 * @see UserRemoteDataSource
 */
public class UserRemoteDataSourceImpl implements UserRemoteDataSource {
    private static String TAG = UserRemoteDataSourceImpl.class.getSimpleName();

    private static UserRemoteDataSourceImpl INSTANCE;
    private final OkHttpClient client;
    private final Gson gson;
    private String clientID;
    
    /**
     * Prevents direct construction, except through getinstance
     */
    private UserRemoteDataSourceImpl(@NonNull Context context) {
        checkNotNull(context);
        clientID = context.getResources().getString(R.string.clientid);
        client = new OkHttpClient();
        gson = new Gson();

    }

    /**
     * Gets instance of UserRemoteDataSourceImpl.
     *
     * @param context the context
     * @return the instance
     */
    public static UserRemoteDataSourceImpl getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new UserRemoteDataSourceImpl(context);
        }
        return INSTANCE;
    }

    @Override
    public User getUser(@NonNull String userId) {
        Request request = new Request.Builder().url(buildUserUrl(userId)).build();
        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) return null;
            User newUser = gson.fromJson(response.body().charStream(), User.class);
            if (newUser != null) {
                return newUser;
            }

        } catch (IOException e) {
            Log.e(TAG, "Failed to get user from server:"+e);
            return new User(ProfileErrors.NETWORK_ERROR);
        }

        return null;
    }

    private String buildUserUrl(String userId) {
        return UserSoundCloudService.SERVICE_ENDPOINT + UserSoundCloudService.SERVICE_REST_PARAM + userId + "?client_id=" + clientID;
    }

}
