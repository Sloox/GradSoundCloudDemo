package com.tomtom.gradsoundcloud.repositorytest;

import android.content.Context;

import com.tomtom.gradsoundcloud.domain.profile.model.User;
import com.tomtom.gradsoundcloud.domain.profile.datasource.UserDataSource;
import com.tomtom.gradsoundcloud.domain.profile.datasource.UserRemoteDataSource;
import com.tomtom.gradsoundcloud.domain.profile.datasource.UserRepo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Unit tests for the implementation of UserRepository
 */
public class UserRepositoryTest {
    /**
     * The constant USER_ID.
     */
    public static final String USER_ID = "3507";

    private UserRepo userRepo;

    @Mock
    private UserDataSource localDataSource;

    @Mock
    private UserRemoteDataSource remoteDataSource;

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
        userRepo = UserRepo.getInstance(localDataSource, remoteDataSource, USER_ID);
    }

    /**
     * Cleanup per test.
     */
    @After
    public void cleanup() {
        UserRepo.destroyInstance();
    }

    /**
     * Test get user remote calleddue to null db.
     */
    @Test
    public void test_getUser_remoteCalleddueToNullDB() {
        User user = new User();
        when(localDataSource.getUser(USER_ID)).thenReturn(null);
        when(remoteDataSource.getUser(USER_ID)).thenReturn(user);
        userRepo.getUser(USER_ID);

        verify(localDataSource).getUser(USER_ID);
        verify(remoteDataSource).getUser(USER_ID);
        verify(localDataSource).deleteUser(USER_ID);
        verify(localDataSource).saveUser(user);
    }

    /**
     * Test save user.
     */
    @Test
    public void test_saveUser() {
        User user = new User();
        userRepo.saveUser(user);
        verify(localDataSource).saveUser(user);
        User user2 = userRepo.getUser(USER_ID);
        assertTrue(user2.equals(user));
    }

    /**
     * Test get user favourite tracks.
     */
    @Test
    public void test_getUserFavouriteTracks() {
        User user = new User();
        when(localDataSource.getUser(USER_ID)).thenReturn(user);
        User user2 = userRepo.getUser(USER_ID);
        verify(localDataSource).getUser(USER_ID);
        assertTrue(user2.equals(user));
    }

    /**
     * Test delete user tracks.
     */
    @Test
    public void test_deleteUserTracks() {
        userRepo.deleteUser(USER_ID);
        verify(localDataSource).deleteUser(USER_ID);
    }



}
