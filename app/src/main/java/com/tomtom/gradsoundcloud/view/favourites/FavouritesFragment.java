package com.tomtom.gradsoundcloud.view.favourites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.nispok.snackbar.enums.SnackbarType;
import com.nispok.snackbar.listeners.ActionClickListener;
import com.tomtom.gradsoundcloud.R;
import com.tomtom.gradsoundcloud.domain.favourites.model.Tracks;
import com.tomtom.gradsoundcloud.presenter.favourites.FavouritesPresenterImpl;
import com.tomtom.gradsoundcloud.view.activity.FragmentContainerActivity;
import com.tomtom.gradsoundcloud.view.activity.ToolbarActivity;
import com.tomtom.gradsoundcloud.presenter.profile.ProfilePresenterImpl;
import com.tomtom.gradsoundcloud.util.BaseError;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Fragment that displays the selected user favourite tracks from the SoundCloud service, implements FavouritesView
 * @see FavouritesPresenterImpl.FavouritesView
 * @see android.support.v4.app.Fragment
 */
public class FavouritesFragment extends Fragment implements FavouritesPresenterImpl.FavouritesView {
    private static final String TAG = FavouritesFragment.class.getSimpleName();
    private static final int TIME_DELAY = 2;

    private FavouritesPresenterImpl presenter;
    private ProgressBar progressIndicator;
    private int fragmentContainerId;
    private FavouritesAdapter adapter;
    private Snackbar snackbar;

    private ScheduledExecutorService pollingServiceScheduler = null;
    private ScheduledFuture pollingFuture = null;

    private RecyclerView recyclerViewContent;
    private LinearLayout errorStatus;

    /**
     * default constructor must be public
     */
    public FavouritesFragment() {
    }

    /**
     * New instance favourites fragment.
     *
     * @return the favourites fragment
     */
    public static FavouritesFragment newInstance() {
        return new FavouritesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        errorStatus = (LinearLayout) root.findViewById(R.id.error_status);

        recyclerViewContent = (RecyclerView) root.findViewById(R.id.recycler_content);
        LinearLayoutManager llm = new LinearLayoutManager(container.getContext());
        recyclerViewContent.setLayoutManager(llm);
        recyclerViewContent.setHasFixedSize(true);
        adapter = new FavouritesAdapter(FavouritesFragment.this, new ArrayList<Tracks>(0));
        recyclerViewContent.setAdapter(adapter);
        setHasOptionsMenu(true);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progressIndicator = ((ToolbarActivity) getActivity()).getProgressIndicator();
        fragmentContainerId = ((FragmentContainerActivity) getActivity()).getFragmentContainerId();
        ((ToolbarActivity) getActivity()).getToolbar().setTitle(getString(R.string.soundcloud_favourite_tracks));
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();

        if (pollingServiceScheduler == null || pollingFuture == null) {
            Log.i(TAG, "Starting ExecutorService for Profile");
            pollingServiceScheduler = Executors.newSingleThreadScheduledExecutor();
            pollingFuture = pollingServiceScheduler.scheduleAtFixedRate(getPollingAction(), TIME_DELAY, TIME_DELAY, TimeUnit.MINUTES);
        }
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        progressIndicator.setVisibility(View.INVISIBLE);
        if (active) {
            progressIndicator.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideSnackBar() {
        if (snackbar != null) {
            snackbar.dismiss();
        }
    }


    @Override
    public void showFailedtoGetTracks(BaseError error) {
        FavouritesErrors fError = (FavouritesErrors) error;
        setErrorIconVisibility(true);
        hideSnackBar();
        switch (fError) {
            case NULL_TRACKS:
                snackbar = Snackbar.with(getActivity())
                        .type(SnackbarType.MULTI_LINE)
                        .duration(Snackbar.SnackbarDuration.LENGTH_INDEFINITE)
                        .text(getString(R.string.error_tracks_null));

                snackbar.actionColor(getResources().getColor(R.color.dialog_focused_blue))
                        .actionLabel(getString(R.string.action_retry))
                        .dismissOnActionClicked(true)
                        .actionListener(new ActionClickListener() {
                            @Override
                            public void onActionClicked(Snackbar snackbar) {
                                presenter.refreshtracks();
                            }
                        });
                break;
            case NETWORK_ERROR:
                snackbar = Snackbar.with(getActivity())
                        .type(SnackbarType.MULTI_LINE)
                        .duration(Snackbar.SnackbarDuration.LENGTH_INDEFINITE)
                        .text(getString(R.string.error_tracks_network_error));

                snackbar.actionColor(getResources().getColor(R.color.dialog_focused_blue))
                        .actionLabel(getString(R.string.action_retry))
                        .dismissOnActionClicked(true)
                        .actionListener(new ActionClickListener() {
                            @Override
                            public void onActionClicked(Snackbar snackbar) {
                                presenter.refreshtracks();
                            }
                        });
                break;
            case FAILED_RETRIEVE_TRACKS:
                snackbar = Snackbar.with(getActivity())
                        .type(SnackbarType.MULTI_LINE)
                        .duration(Snackbar.SnackbarDuration.LENGTH_INDEFINITE)
                        .text(getString(R.string.error_tracks_network_unsuccessfull));

                snackbar.actionColor(getResources().getColor(R.color.dialog_focused_blue))
                        .actionLabel(getString(R.string.action_retry))
                        .dismissOnActionClicked(true)
                        .actionListener(new ActionClickListener() {
                            @Override
                            public void onActionClicked(Snackbar snackbar) {
                                presenter.refreshtracks();
                            }
                        });
                break;
            default:
                snackbar = Snackbar.with(getActivity())
                        .type(SnackbarType.MULTI_LINE)
                        .duration(Snackbar.SnackbarDuration.LENGTH_INDEFINITE)
                        .text(getString(R.string.error_unknown));
                break;
        }
        SnackbarManager.show(snackbar);

    }

    @Override
    public void showTracks(List<Tracks> trackses) {
        setLoadingIndicator(false);
        setErrorIconVisibility(false);
        adapter.updateData(trackses);
    }

    @Override
    public void refreshTracks() {
        presenter.refreshtracks();
    }

    /**
     * Helper method to display an error image
     * and hide the rest of the components and vice versa
     */
    private void setErrorIconVisibility(boolean active) {
        if (active) {
            if (errorStatus != null) {
                errorStatus.setVisibility(View.VISIBLE);
            }
            if (recyclerViewContent != null) {
                recyclerViewContent.setVisibility(View.GONE);
            }
        } else {
            if (errorStatus != null) {
                errorStatus.setVisibility(View.GONE);
            }
            if (recyclerViewContent != null) {
                recyclerViewContent.setVisibility(View.VISIBLE);
            }
        }
    }


    /**
     * Polling action that refreshes the tracks after a set time interval
     */
    private Runnable getPollingAction() {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                if (isVisible() && !isRemoving()) {
                    Log.i(TAG, "Poll triggered, attempting a refresh");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            refreshTracks();
                        }
                    });

                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        hideSnackBar();
    }

    @Override
    public void onStop() {
        super.onStop();
        destroyPollingService();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroyPollingService();

    }

    private void destroyPollingService() {
        Log.i(TAG, "Stopping polling service");
        if (pollingFuture != null) {
            pollingFuture.cancel(false);
            pollingFuture = null;
        }
    }

    /**
     * Fragment gets the presenter set after the actual construction
     * of the presenter has occured
     *
     * @see ProfilePresenterImpl
     */

    @Override
    public void setPresenter(FavouritesPresenterImpl presenter) {
        this.presenter = presenter;
    }
}
