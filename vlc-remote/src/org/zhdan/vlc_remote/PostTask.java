package org.zhdan.vlc_remote;

import android.os.AsyncTask;
import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * An AsyncTask implementation for performing POSTs on the VLC REST APIs.
 */
public class PostTask extends AsyncTask<String, String, String> {
    private String restURL;
    private RestTaskCallback callback;
    private FailCallback failCallback;
    private String requestBody;
    private Throwable error;

    /**
     * Creates a new instance of PostTask with the specified params
     *
     * @param restUrl      The URL for the REST API
     * @param callback     The callback to be invoked when the HTTP request completes
     * @param requestBody  The body of the POST request
     * @param failCallback The callback to be invoked when the HTTP request fails
     */
    public PostTask(String restUrl, String requestBody, RestTaskCallback callback, FailCallback failCallback) {
        this.restURL = restUrl;
        this.requestBody = requestBody;
        this.callback = callback;
        this.failCallback = failCallback;
    }

    @Override
    protected String doInBackground(String... arg0) {
        final HttpClient httpclient = new DefaultHttpClient();
        final HttpPost httppost = new HttpPost(restURL);
        // final String result;
        try {
            if (requestBody != null) {
                httppost.setEntity(new StringEntity(requestBody));
            }
            Log.d("vlcremote", "Pending VLC REST POST request: " + restURL);
            final HttpResponse response;
            try {
                response = httpclient.execute(httppost);
                Log.d("vlcremote", "Response " + response.getEntity());
                // result = EntityUtils.toString(response.getEntity());
            } catch (ClientProtocolException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (IOException e) {
                error = e;
            }
        } catch (UnsupportedEncodingException e) {
            error = e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        if (error == null) {
            callback.onTaskComplete(result);
        } else {
            failCallback.onTaskFail(error);
        }
        super.onPostExecute(result);
    }
}
