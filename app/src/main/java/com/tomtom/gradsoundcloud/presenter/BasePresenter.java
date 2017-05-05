package com.tomtom.gradsoundcloud.presenter;

/**
 * Base presenter that defines the common functionality a presenter must have
 */
public interface BasePresenter {
    /**
     * Start, will trigger data loading & initiate the presnter (usually onResume() of a fragment or activity)
     */
    void start();
}
