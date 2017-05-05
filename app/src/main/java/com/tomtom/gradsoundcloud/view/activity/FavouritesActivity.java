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
import com.tomtom.gradsoundcloud.domain.favourites.datasource.FavouritesLoader;
import com.tomtom.gradsoundcloud.domain.favourites.datasource.TracksRepo;
import com.tomtom.gradsoundcloud.domain.favourites.datasource.local.TracksLocalDataSource;
import com.tomtom.gradsoundcloud.domain.favourites.datasource.remote.TracksRemoteDataSourceImpl;
import com.tomtom.gradsoundcloud.view.favourites.FavouritesFragment;
import com.tomtom.gradsoundcloud.presenter.favourites.FavouritesPresenterImpl;
import com.tomtom.gradsoundcloud.util.FragmentActivityUtils;


/**
 * Favourites Activity that contains the UI & view for Selected users favourites tracks
 */
public class FavouritesActivity extends AppCompatActivity implements ToolbarActivity, FragmentContainerActivity {
    private static final String TAG = FavouritesActivity.class.getSimpleName();

    private android.support.v4.app.FragmentManager fragmentManager;
    private static final String EXTRA_UID = "EXTRA_UID";
    private String uId = "3207";//make selectable

    private FavouritesFragment favouritesFragment;

    private ProgressBar progressBar;
    private Toolbar toolbar;


    /**
     * Helper class to get a starting  intent to launch this activity
     *
     * @param context the context
     * @param uId     the u id
     * @return the intent
     */
    public static Intent getStartIntent(@NonNull Context context, @NonNull String uId) {
        Intent intent = new Intent(context, FavouritesActivity.class);
        intent.putExtra(EXTRA_UID, uId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grad_sound_cloud_favourites);

        uId = getIntent().getStringExtra(EXTRA_UID);

        fragmentManager = getSupportFragmentManager();

        setupToolbar();
        setupProgressIndicator();

        setupFragments();
        setupPresenters();

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
        TracksRepo repo = TracksRepo.getInstance(TracksLocalDataSource.getInstance(getApplicationContext()), TracksRemoteDataSourceImpl.getInstance(getApplicationContext()));
        FavouritesLoader loader = new FavouritesLoader(getApplicationContext(), uId, repo);
        new FavouritesPresenterImpl(getSupportLoaderManager(), favouritesFragment, loader, repo);
    }

    /**
     * A helper method to initiliaze the fragment displaying
     * the selected users favourite tracks
     */
    private void setupFragments() {
        favouritesFragment = (FavouritesFragment) fragmentManager.findFragmentById(R.id.contentFrame);
        if (favouritesFragment == null) {
            favouritesFragment = FavouritesFragment.newInstance();
            FragmentActivityUtils.addFragmentToActivity(fragmentManager, favouritesFragment, getFragmentContainerId());
        }

    }


    /**
     * A helper method to initiliaze the toolbar
     */
    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_back);
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
        getMenuInflater().inflate(R.menu.menu_favourites, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_refresh:
                favouritesFragment.refreshTracks();
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
