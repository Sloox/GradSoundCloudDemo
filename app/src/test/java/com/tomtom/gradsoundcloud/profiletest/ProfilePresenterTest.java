package com.tomtom.gradsoundcloud.profiletest;


import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.tomtom.gradsoundcloud.domain.profile.model.User;
import com.tomtom.gradsoundcloud.domain.profile.datasource.ProfileLoader;
import com.tomtom.gradsoundcloud.domain.profile.datasource.UserRepo;
import com.tomtom.gradsoundcloud.presenter.profile.ProfilePresenterImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the implementation of ProfilePresenter
 */
public class ProfilePresenterTest {
    /**
     * The constant ID.
     */
    public static final String ID = "3207";
    @Mock
    private UserRepo userRepo;

    @Mock
    private ProfilePresenterImpl.ProfileView profileView;

    @Mock
    private ProfileLoader profileLoader;

    @Mock
    private LoaderManager loaderManager;

    private ProfilePresenterImpl profilePresenter;

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
        profilePresenter = new ProfilePresenterImpl(profileLoader, loaderManager, profileView, userRepo);
    }

    /**
     * Test profile presentor try add new user.
     */
    @Test
    public void test_ProfilePresentor_tryAddNewUser() {
        profilePresenter.tryAddNewUser(3207);
        verify(userRepo).changeSelectedUser(ID);
        verify(userRepo).refreshUser();
    }

    /**
     * Test profile presentor remove selected user.
     */
    @Test
    public void test_ProfilePresentor_RemoveSelectedUser() {
        profilePresenter.removeSelectedUser();
        verify(userRepo).deleteSelectedUser();
        verify(userRepo).refreshUser();
    }

    /**
     * Test profile presentor show user.
     */
    @Test
    public void test_ProfilePresentor_showUser() {
        profilePresenter.showUser();
        verify(profileView).showUser(any(User.class));
        verify(profileView).hideSnackBar();
    }

    /**
     * Test profile presentor show add new user.
     */
    @Test
    public void test_ProfilePresentor_showAddNewUser() {
        profilePresenter.showAddNewUser();
        verify(profileView).showAddNewUser();
    }

    /**
     * Test load finished show user.
     */
    @Test
    public void test_loadFinished_showUser() {
        User user = new User();
        when(userRepo.getUser(ID)).thenReturn(user);

        profilePresenter.onLoadFinished(mock(Loader.class), user);
        verify(profileView).setLoadingIndicator(false);
        verify(profileView).showUser(user);
    }

}
