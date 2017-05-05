package com.tomtom.gradsoundcloud.repositorytest;

import android.content.Context;

import com.tomtom.gradsoundcloud.domain.favourites.model.Tracks;
import com.tomtom.gradsoundcloud.domain.favourites.datasource.TracksDataSource;
import com.tomtom.gradsoundcloud.domain.favourites.datasource.TracksRemoteDataSource;
import com.tomtom.gradsoundcloud.domain.favourites.datasource.TracksRepo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the implementation of TracksRepository
 */
public class TracksRepositoryTest {
    /**
     * The constant USER_ID.
     */
    public static final String USER_ID = "3507";
    private TracksRepo tracksRepo;

    @Mock
    private TracksDataSource localDataSource;

    @Mock
    private TracksRemoteDataSource remoteDataSource;

    @Mock
    private Context mContext;

    /**
     * Setup that runs before the tests start.
     */
    @Before
    public void setup() {
        try {
            MockitoAnnotations.initMocks(this);
        } catch (NullPointerException e) {
            //waiting for fix
        }
        tracksRepo = TracksRepo.getInstance(localDataSource, remoteDataSource);
    }

    /**
     * Cleanup per test.
     */
    @After
    public void cleanUp() {
        TracksRepo.destroyInstance();
    }

    /**
     * Test get tracks remote calleddue to null db.
     */
    @Test
    public void test_getTracks_remoteCalleddueToNullDB() {
        List<Tracks> newTrackses = new ArrayList<>();
        newTrackses.add(new Tracks());
        when(localDataSource.getUserFavouriteTracks(any(String.class))).thenReturn(null);
        when(remoteDataSource.getUserFavouriteTracks(any(String.class))).thenReturn(newTrackses);
        tracksRepo.getUserFavouriteTracks(USER_ID);

        verify(localDataSource).getUserFavouriteTracks(any(String.class));
        verify(remoteDataSource).getUserFavouriteTracks(any(String.class));
        verify(localDataSource).deleteUserTracks(any(String.class));
        verify(localDataSource).saveUserTracks(anyListOf(Tracks.class), any(String.class));
    }

    /**
     * Test save user tracks.
     */
    @Test
    public void test_saveUserTracks() {
        List<Tracks> newTrackses = new ArrayList<>();
        newTrackses.add(new Tracks());
        tracksRepo.saveUserTracks(newTrackses, USER_ID);
        verify(localDataSource).saveUserTracks(newTrackses, USER_ID);
    }

    /**
     * Test get user favourite tracks.
     */
    @Test
    public void test_getUserFavouriteTracks() {
        List<Tracks> newTrackses = new ArrayList<>();
        newTrackses.add(new Tracks());
        when(localDataSource.getUserFavouriteTracks(any(String.class))).thenReturn(newTrackses);
        List<Tracks> getTracks = tracksRepo.getUserFavouriteTracks(USER_ID);
        verify(localDataSource).getUserFavouriteTracks(USER_ID);
        assertTrue(getTracks.equals(newTrackses));
    }

    /**
     * Test delete user tracks.
     */
    @Test
    public void test_deleteUserTracks() {
        tracksRepo.deleteUserTracks(USER_ID);
        verify(localDataSource).deleteUserTracks(USER_ID);
    }

}
