package org.zhdan.vlc_remote;

/**
 * Class definition for a callback to be invoked when the response data for the
 * GET call is available.
 */
public abstract class GetResponseCallback<T> {

    /**
     * Called when the response data for the REST call is ready. <br/>
     * This method is guaranteed to execute on the UI thread.
     *
     * @param data The {@code data} that was received from the server.
     */
    abstract void onDataReceived(T data);

    /*
     * Additional methods like onPreGet() or onFailure() can be added with default implementations.
     * This is why this has been made and abstract class rather than Interface.
     */

    /**
     * Called when a GET success response is received. <br/>
     * This method is guaranteed to execute on the UI thread.
     */
    abstract void onGetSuccess();
}
