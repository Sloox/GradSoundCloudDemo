package com.tomtom.gradsoundcloud.domain.favourites.datasource.remote;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tomtom.gradsoundcloud.R;
import com.tomtom.gradsoundcloud.domain.favourites.model.Tracks;
import com.tomtom.gradsoundcloud.domain.favourites.datasource.TracksRemoteDataSource;
import com.tomtom.gradsoundcloud.view.favourites.FavouritesErrors;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Concrete Implementation to load the User Favourite tracks remotely (Network)
 * All implementations will consider retriving the user from the Network
 *
 * @see TracksRemoteDataSource
 */
public class TracksRemoteDataSourceImpl implements TracksRemoteDataSource {
    private static final String TAG = TracksRemoteDataSourceImpl.class.getSimpleName();

    private static TracksRemoteDataSourceImpl INSTANCE;
    private final OkHttpClient client;
    private final Gson gson;
    private String clientID;

    /**
     * Gets instance of TracksRemoteDataSourceImpl.
     *
     * @param context the context
     * @return the instance
     */
    public static TracksRemoteDataSourceImpl getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new TracksRemoteDataSourceImpl(context);
        }
        return INSTANCE;
    }

    /**
     * Prevents direct construction, except through getinstance
     */
    private TracksRemoteDataSourceImpl(@NonNull Context context) {
        clientID = context.getResources().getString(R.string.clientid);
        client = new OkHttpClient();
        gson = new Gson();
    }

    @Override
    public List<Tracks> getUserFavouriteTracks(@NonNull String userId) {
        Request request = new Request.Builder().url(buildtracksUrl(userId)).build();
        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                List<Tracks> error = (new ArrayList<>());
                error.add(new Tracks(FavouritesErrors.FAILED_RETRIEVE_TRACKS));
                return error;
            }
            Type listOfTracks = new TypeToken<List<Tracks>>() {}.getType();
            return gson.fromJson(response.body().charStream(), listOfTracks);
        } catch (IOException e) {
            Log.e(TAG, "Failed to get user from server:" + e);
            List<Tracks> error = (new ArrayList<>());
            error.add(new Tracks(FavouritesErrors.NETWORK_ERROR));
            return error;

        }
    }

    private String buildtracksUrl(String userId) {
        return TracksSoundCloudService.SERVICE_ENDPOINT + TracksSoundCloudService.SERVICE_REST_PARAM.replace("{user}", userId) + "?client_id=" + clientID;
    }
}
