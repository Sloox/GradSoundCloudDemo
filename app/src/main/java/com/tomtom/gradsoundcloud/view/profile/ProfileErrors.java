package com.tomtom.gradsoundcloud.view.profile;

import com.tomtom.gradsoundcloud.util.BaseError;

/**
 * Concrete Error states relating to Profile/User
 * These error states that allow the UI to update accordingly
 * @see com.tomtom.gradsoundcloud.util.BaseError
 */
public enum ProfileErrors implements BaseError {
    NO_USER_IN_DB, NULL_USER, NETWORK_ERROR, NO_USER_SELECTED
}
