package com.tomtom.gradsoundcloud.view.activity;

import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;

/**
 * A marker interface that helps with handling the android toolbar
 */
public interface ToolbarActivity {

    /**
     * Gets toolbar.
     *
     * @return the toolbar
     */
    Toolbar getToolbar();

    /**
     * Gets progress indicator.
     *
     * @return the progress indicator
     */
    ProgressBar getProgressIndicator();
}
