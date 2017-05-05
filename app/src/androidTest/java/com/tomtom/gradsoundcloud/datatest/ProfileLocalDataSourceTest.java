package com.tomtom.gradsoundcloud.datatest;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.tomtom.gradsoundcloud.domain.profile.model.User;
import com.tomtom.gradsoundcloud.domain.profile.datasource.local.UserLocalDataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * Integration tests for the TracksDataSource
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ProfileLocalDataSourceTest {
    private static String USERID = "3702";
    private static String USERID2 = "3703";
    private static String USERID3 = "3705";


    private UserLocalDataSource userLocalDataSource;

    /**
     * Setup that runs before the tests start.
     */
    @Before
    public void setup() {
        userLocalDataSource = UserLocalDataSource.getInstance(InstrumentationRegistry.getTargetContext());
    }

    /**
     * After testclear data.
     */
    @After
    public void afterTestclearData() {
        userLocalDataSource.deleteUser(USERID);
        userLocalDataSource.deleteUser(USERID2);
        userLocalDataSource.deleteUser(USERID3);

    }

    /**
     * Test data source conditions.
     */
    @Test
    public void testDataSourceConditions() {
        assertNotNull(userLocalDataSource);
    }

    /**
     * Test save profile.
     */
    @Test
    public void test_saveProfile() {
        User user = new User();
        user.setId(3702);
        user.setOnline(false);
        userLocalDataSource.saveUser(user);
        User user1 = userLocalDataSource.getUser(USERID);
        assertThat(user1, is(user));
    }

    /**
     * Test delete profile.
     */
    @Test
    public void test_deleteProfile() {
        User user = new User();
        user.setId(3702);
        userLocalDataSource.saveUser(user);
        userLocalDataSource.deleteUser(USERID);
        User nullUser = userLocalDataSource.getUser(USERID);
        assertNull(nullUser);
    }

    /**
     * Test get null user.
     */
    @Test
    public void test_getNullUser() {
        User nullUser = userLocalDataSource.getUser(USERID);
        assertNull(nullUser);
    }

}
