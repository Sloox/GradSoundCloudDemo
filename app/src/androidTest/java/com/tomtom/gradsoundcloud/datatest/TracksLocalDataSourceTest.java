package com.tomtom.gradsoundcloud.datatest;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.tomtom.gradsoundcloud.domain.favourites.model.Tracks;
import com.tomtom.gradsoundcloud.domain.favourites.datasource.local.TracksLocalDataSource;
import com.tomtom.gradsoundcloud.view.favourites.FavouritesErrors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * Integration tests for the TracksDataSource
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TracksLocalDataSourceTest {
    private static String USERID = "3702";
    private static String USERID2 = "3703";
    private static String USERID3 = "3705";


    private TracksLocalDataSource tracksLocalDataSource;

    /**
     * Setup that runs before the tests start.
     */
    @Before
    public void setup() {
        tracksLocalDataSource = TracksLocalDataSource.getInstance(InstrumentationRegistry.getTargetContext());
    }

    /**
     * After testclear data.
     */
    @After
    public void afterTestclearData() {
        tracksLocalDataSource.deleteUserTracks(USERID);
        tracksLocalDataSource.deleteUserTracks(USERID2);
        tracksLocalDataSource.deleteUserTracks(USERID3);
    }

    /**
     * Test data source conditions.
     */
    @Test
    public void testDataSourceConditions() {
        assertNotNull(tracksLocalDataSource);
    }

    /**
     * Test save tracks.
     */
    @Test
    public void test_saveTracks() {
        List<Tracks> tracksList = new ArrayList<>();
        tracksList.add(new Tracks());
        tracksLocalDataSource.saveUserTracks(tracksList, USERID);
        assertThat(tracksLocalDataSource.getUserFavouriteTracks(USERID), is(tracksList));
    }

    /**
     * Test get saved tracks.
     */
    @Test
    public void test_getSavedTracks() {
        List<Tracks> tracksList = new ArrayList<>();
        List<Tracks> track2List = new ArrayList<>();

        tracksList.add(new Tracks(FavouritesErrors.NULL_TRACKS));
        tracksList.add(new Tracks());

        tracksLocalDataSource.saveUserTracks(tracksList, USERID);
        tracksLocalDataSource.saveUserTracks(track2List, USERID2);

        List<Tracks> retrieveTracks = tracksLocalDataSource.getUserFavouriteTracks(USERID);
        assertNotNull(retrieveTracks);
        assertTrue(retrieveTracks.equals(tracksList));

        List<Tracks> retrieveTracks2 = tracksLocalDataSource.getUserFavouriteTracks(USERID2);
        assertNotNull(retrieveTracks2);
        assertTrue(retrieveTracks2.equals(track2List));
    }

    /**
     * Test delete tracks.
     */
    @Test
    public void test_deleteTracks(){
        List<Tracks> tracksList = new ArrayList<>();
        tracksList.add(new Tracks(FavouritesErrors.NULL_TRACKS));
        tracksLocalDataSource.saveUserTracks(tracksList, USERID);
        tracksLocalDataSource.deleteUserTracks(USERID);
        List<Tracks> retrieveTracks = tracksLocalDataSource.getUserFavouriteTracks(USERID);
        assertNull(retrieveTracks);
    }

    /**
     * Test get empty tracks.
     */
    @Test
    public void test_getEmptyTracks() {
        List<Tracks> retrieveTracks = tracksLocalDataSource.getUserFavouriteTracks(USERID3);
        assertNull(retrieveTracks);
    }

}
