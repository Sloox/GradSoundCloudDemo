package com.tomtom.gradsoundcloud.view.profile;

import android.content.Intent;
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
import com.tomtom.gradsoundcloud.domain.profile.model.User;
import com.tomtom.gradsoundcloud.presenter.profile.ProfilePresenterImpl;
import com.tomtom.gradsoundcloud.view.activity.FavouritesActivity;
import com.tomtom.gradsoundcloud.view.activity.FragmentContainerActivity;
import com.tomtom.gradsoundcloud.view.activity.ToolbarActivity;
import com.tomtom.gradsoundcloud.view.dialog.AddUserDialog;
import com.tomtom.gradsoundcloud.util.BaseError;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Fragment that displays the selected user from the SoundCloud service, implements the ProfileView
 * @see ProfilePresenterImpl.ProfileView
 * @see android.support.v4.app.Fragment
 */
public class ProfileFragment extends Fragment implements ProfilePresenterImpl.ProfileView {

    private static final int ADD_NEW_USER = 1001;
    private static final int RESULT_OK = -1;
    private static final int TIME_DELAY = 2;
    private static final String TAG = ProfileFragment.class.getSimpleName();
    private ProfilePresenterImpl presenter;
    private ProgressBar progressIndicator;
    private int fragmentContainerId;
    private ProfileAdapter adapter;
    private Snackbar snackbar;

    private ScheduledExecutorService pollingServiceScheduler = null;
    private ScheduledFuture pollingFuture = null;

    private RecyclerView recyclerViewContent;
    private LinearLayout errorStatus;

    private boolean activityResultResume;

    /**
     * default constructor must be public
     */
    public ProfileFragment() {
    }

    /**
     * New instance profile fragment.
     *
     * @return the profile fragment
     */
    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }


    private Runnable getPollingAction() {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                if (isVisible() && !isRemoving()) {
                    Log.i(TAG, "Poll triggered, attempting a refresh");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            refreshProfile();
                        }
                    });

                }
            }
        });
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
        adapter = new ProfileAdapter(ProfileFragment.this, new ArrayList<User>(0));
        recyclerViewContent.setAdapter(adapter);

        setHasOptionsMenu(true);

        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progressIndicator = ((ToolbarActivity) getActivity()).getProgressIndicator();
        fragmentContainerId = ((FragmentContainerActivity) getActivity()).getFragmentContainerId();
        ((ToolbarActivity) getActivity()).getToolbar().setTitle(getString(R.string.soundcloud_profile));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!activityResultResume) {
            presenter.start();
        } else {
            activityResultResume = !activityResultResume;
        }

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
    public void showFailedtoUpdate(BaseError errors) {
        ProfileErrors pError = (ProfileErrors) errors;
        setErrorIconVisibility(true);
        hideSnackBar();
        switch (pError) {
            case NO_USER_IN_DB:
                snackbar = Snackbar.with(getActivity())
                        .type(SnackbarType.MULTI_LINE)
                        .duration(Snackbar.SnackbarDuration.LENGTH_INDEFINITE)
                        .text(getString(R.string.error_no_user_in_db));

                snackbar.actionColor(getResources().getColor(R.color.dialog_focused_blue))
                        .actionLabel(getString(R.string.profile_add_user))
                        .dismissOnActionClicked(true)
                        .actionListener(new ActionClickListener() {
                            @Override
                            public void onActionClicked(Snackbar snackbar) {
                                presenter.showAddNewUser();
                            }
                        });
                break;
            case NULL_USER:
                snackbar = Snackbar.with(getActivity())
                        .type(SnackbarType.MULTI_LINE)
                        .duration(Snackbar.SnackbarDuration.LENGTH_INDEFINITE)
                        .text(getString(R.string.error_failed_get_user));

                snackbar.actionColor(getResources().getColor(R.color.dialog_focused_blue))
                        .actionLabel(getString(R.string.profile_add_user))
                        .dismissOnActionClicked(true)
                        .actionListener(new ActionClickListener() {
                            @Override
                            public void onActionClicked(Snackbar snackbar) {
                                presenter.showAddNewUser();
                            }
                        });
                break;
            case NO_USER_SELECTED:
                snackbar = Snackbar.with(getActivity())
                        .type(SnackbarType.MULTI_LINE)
                        .duration(Snackbar.SnackbarDuration.LENGTH_INDEFINITE)
                        .text(getString(R.string.error_no_user_selected));

                snackbar.actionColor(getResources().getColor(R.color.dialog_focused_blue))
                        .actionLabel(getString(R.string.profile_add_user))
                        .dismissOnActionClicked(true)
                        .actionListener(new ActionClickListener() {
                            @Override
                            public void onActionClicked(Snackbar snackbar) {
                                presenter.showAddNewUser();
                            }
                        });
                break;
            case NETWORK_ERROR:
                snackbar = Snackbar.with(getActivity())
                        .type(SnackbarType.MULTI_LINE)
                        .duration(Snackbar.SnackbarDuration.LENGTH_INDEFINITE)
                        .text(getString(R.string.error_user_network_failure));

                snackbar.actionColor(getResources().getColor(R.color.dialog_focused_blue))
                        .actionLabel(getString(R.string.action_retry))
                        .dismissOnActionClicked(true)
                        .actionListener(new ActionClickListener() {
                            @Override
                            public void onActionClicked(Snackbar snackbar) {
                                presenter.refreshUser();
                            }
                        });
                break;

            default:
                snackbar = Snackbar.with(getActivity())
                        .type(SnackbarType.MULTI_LINE)
                        .duration(Snackbar.SnackbarDuration.LENGTH_LONG)
                        .text(getString(R.string.error_unknown));
                break;

        }
        SnackbarManager.show(snackbar);
    }

    @Override
    public void showAddNewUser() {
        startActivityForResult(AddUserDialog.getStartIntent(getContext()), ADD_NEW_USER);
    }

    @Override
    public void removeSelectedUser() {
        presenter.removeSelectedUser();
    }

    @Override
    public void refreshProfile() {
        presenter.refreshUser();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NEW_USER) {
            if (resultCode == RESULT_OK) {
                presenter.tryAddNewUser(data.getIntExtra(AddUserDialog.EXTRA_USER_ADD_ID, -1));
                activityResultResume = true;
            }
        }
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

    @Override
    public void gotoProfileTracks(String uId) {
        startActivity(FavouritesActivity.getStartIntent(getContext(),uId));
    }

    @Override
    public void showUser(User user) {
        adapter.updateData(user);
        setLoadingIndicator(false);
        setErrorIconVisibility(false);
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
    public void setPresenter(ProfilePresenterImpl presenter) {
        this.presenter = presenter;
    }
}
