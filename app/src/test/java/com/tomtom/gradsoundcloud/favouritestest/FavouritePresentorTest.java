package com.tomtom.gradsoundcloud.favouritestest;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.tomtom.gradsoundcloud.domain.favourites.model.Tracks;
import com.tomtom.gradsoundcloud.domain.favourites.datasource.FavouritesLoader;
import com.tomtom.gradsoundcloud.domain.favourites.datasource.TracksRepo;
import com.tomtom.gradsoundcloud.presenter.favourites.FavouritesPresenterImpl;
import com.tomtom.gradsoundcloud.util.BaseError;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Unit tests for the implementation of FavouritePresentor
 */
public class FavouritePresentorTest {
    @Mock
    private TracksRepo tracksRepo;

    @Mock
    private FavouritesPresenterImpl.FavouritesView favouritesView;

    @Mock
    private FavouritesLoader loader;

    @Mock
    private LoaderManager loaderManager;

    private FavouritesPresenterImpl favouritesPresenter;

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
        // System.setProperty("dexmaker.dexcache", InstrumentationRegistry.getTargetContext().getCacheDir().getPath());
        favouritesPresenter = new FavouritesPresenterImpl(loaderManager, favouritesView, loader, tracksRepo);
    }

    /**
     * Test favourites presenter show favourite tracks.
     */
    @Test
    public void test_FavouritesPresenter_showFavouriteTracks() {
        favouritesPresenter.showFavouriteTracks();
        verify(favouritesView).showFailedtoGetTracks(any(BaseError.class));
    }

    /**
     * Test favourites presenter on load finished show tracks.
     */
    @Test
    public void test_FavouritesPresenter_onLoadFinished_ShowTracks() {
        List<Tracks> trackses = new ArrayList<>();
        trackses.add(new Tracks());
        when(tracksRepo.getUserFavouriteTracks("3507")).thenReturn(trackses);

        favouritesPresenter.onLoadFinished(mock(Loader.class), trackses);
        verify(favouritesView).setLoadingIndicator(false);
        verify(favouritesView).showTracks(trackses);
        verify(favouritesView).hideSnackBar();
    }

    /**
     * Test favourites presenter on load finished failed show tracks.
     */
    @Test
    public void test_FavouritesPresenter_onLoadFinished_FailedShowTracks() {
        List<Tracks> trackses = new ArrayList<>();
        trackses.add(new Tracks());
        when(tracksRepo.getUserFavouriteTracks("3507")).thenReturn(null);

        favouritesPresenter.onLoadFinished(mock(Loader.class), null);
        verify(favouritesView).showFailedtoGetTracks(any(BaseError.class));
    }


}
