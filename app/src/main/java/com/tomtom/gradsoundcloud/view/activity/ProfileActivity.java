package com.tomtom.gradsoundcloud.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.tomtom.gradsoundcloud.R;
import com.tomtom.gradsoundcloud.domain.profile.datasource.ProfileLoader;
import com.tomtom.gradsoundcloud.domain.profile.datasource.UserRepo;
import com.tomtom.gradsoundcloud.domain.profile.datasource.local.UserLocalDataSource;
import com.tomtom.gradsoundcloud.domain.profile.datasource.remote.UserRemoteDataSourceImpl;
import com.tomtom.gradsoundcloud.view.dialog.AboutDialog;
import com.tomtom.gradsoundcloud.view.profile.ProfileFragment;
import com.tomtom.gradsoundcloud.presenter.profile.ProfilePresenterImpl;
import com.tomtom.gradsoundcloud.util.FragmentActivityUtils;


/**
 * ProfileActivity that contains the UI & view for Users profile
 */
public class ProfileActivity extends AppCompatActivity implements ToolbarActivity, FragmentContainerActivity {
    private static final String TAG = ProfileActivity.class.getSimpleName();

    private android.support.v4.app.FragmentManager fragmentManager;
    private String USERID = "3207";//make selectable

    private ProfileFragment profileFragment;

    private ProgressBar progressBar;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grad_sound_cloud_profile);
        fragmentManager = getSupportFragmentManager();

        setupToolbar();
        setupProgressIndicator();
        setupFragments();
        setupPresenters();
    }


    /**
     * Helper class to get a starting  intent to launch this activity
     *
     * @param context the context
     * @return the intent
     */
    public static Intent getStartIntent(@NonNull Context context) {
        return new Intent(context, ProfileActivity.class);
    }


    /**
     * Overridden to stop the app from closing if there are fragments that have been shown
     */
    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }


    private void setupPresenters() {
        UserRepo repo = UserRepo.getInstance(UserLocalDataSource.getInstance(getApplicationContext()), UserRemoteDataSourceImpl.getInstance(getApplicationContext()), USERID);
        ProfileLoader loader = new ProfileLoader(getApplicationContext(), repo);
        new ProfilePresenterImpl(loader, getSupportLoaderManager(), profileFragment, repo);
    }

    /**
     * A helper method to initiliaze the fragment displaying
     * the user selected
     */
    private void setupFragments() {
        profileFragment = (ProfileFragment) fragmentManager.findFragmentById(R.id.contentFrame);
        if (profileFragment == null) {
            profileFragment = ProfileFragment.newInstance();
        }
        FragmentActivityUtils.addFragmentToActivity(fragmentManager, profileFragment, getFragmentContainerId());
    }


    /**
     * A helper method to initiliaze the toolbar
     */
    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_info);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    /**
     * A helper method to initilaize the progress indicator
     */
    private void setupProgressIndicator() {
        progressBar = (ProgressBar) findViewById(R.id.progress_indicator);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(AboutDialog.getStartIntent(getApplicationContext()));
                return true;
            case R.id.action_refresh:
                profileFragment.refreshProfile();
                return true;
            case R.id.action_add_user:
                profileFragment.showAddNewUser();
                return true;
            case R.id.action_remove_user:
                profileFragment.removeSelectedUser();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getFragmentContainerId() {
        return R.id.contentFrame;
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public ProgressBar getProgressIndicator() {
        return progressBar;
    }

}
