package org.zhdan.vlc_remote;

import android.os.AsyncTask;

/**
 * An AsyncTask implementation for performing GETs on the VLC REST APIs.
 */
public class GetTask extends AsyncTask<String, String, String> {

    private String restUrl;
    private RestTaskCallback callback;

    /**
     * Creates a new instance of GetTask with the specified URL and callback.
     *
     * @param restUrl  The URL for the REST API.
     * @param callback The callback to be invoked when the HTTP request completes
     */
    public GetTask(String restUrl, RestTaskCallback callback) {
        this.restUrl = restUrl;
        this.callback = callback;
    }

    @Override
    protected String doInBackground(String... params) {
        String response = null;
        // TODO
        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        callback.onTaskComplete(result);
        super.onPostExecute(result);
    }
}
