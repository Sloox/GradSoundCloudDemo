package com.tomtom.gradsoundcloud.view;

/**
 * Base view that defines the common functionality a View must have
 *
 * @param <T> the type parameter
 */
public interface BaseView<T> {
    /**
     * Sets presenter. done withing the activity or fragment
     *
     * @param presenter the presenter
     */
    void setPresenter(T presenter);
}
