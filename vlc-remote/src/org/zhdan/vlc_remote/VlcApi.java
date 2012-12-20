package org.zhdan.vlc_remote;

/**
 * Entry point into the API.
 */
public class VlcApi {
    public final String baseUrl;
    public final FailCallback failCallback;

    public VlcApi(final String url, final FailCallback failCallback) {
        baseUrl = url + "requests/status.xml?";
        this.failCallback = failCallback;
    }

    /**
     * Push remote 'pause button'
     *
     * @param callback Callback to execute when the profile is available.
     */
    public void pause(final PostCallback callback) {
        new PostTask(pause(), null, new RestTaskCallback() {
            public void onTaskComplete(String response) {
                callback.onPostSuccess();
            }
        }, failCallback).execute();
    }

    /**
     * Push remote 'stop button'
     *
     * @param callback Callback to execute when the profile is available.
     */
    public void stop(final PostCallback callback) {
        new PostTask(stop(), null, new RestTaskCallback() {
            public void onTaskComplete(String response) {
                callback.onPostSuccess();
            }
        }, failCallback).execute();
    }

    /**
     * Push remote 'next button'
     *
     * @param callback Callback to execute when the profile is available.
     */
    public void next(final PostCallback callback) {
        new PostTask(next(), null, new RestTaskCallback() {
            public void onTaskComplete(String response) {
                callback.onPostSuccess();
            }
        }, failCallback).execute();
    }

    private String url(String suffix) {
        if (baseUrl == null) {
            throw new IllegalStateException("Base url not set!");
        }
        return baseUrl + suffix;
    }
    private String pause() {
        return url("command=pl_pause");
    }

    private String stop() {
        return url("command=pl_stop");
    }

    private String next() {
        return url("command=pl_next");
    }

}
