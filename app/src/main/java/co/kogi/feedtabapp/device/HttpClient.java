package co.kogi.feedtabapp.device;

import android.util.Log;

import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpGet;
import com.koushikdutta.async.http.AsyncHttpResponse;
import com.koushikdutta.async.http.cache.ResponseCacheMiddleware;
import com.koushikdutta.async.http.callback.HttpConnectCallback;

import java.io.IOException;

import co.kogi.feedtabapp.util.AppContext;

/**
 * Created by user on 5/07/2016.
 */
public class HttpClient {

    static ResponseCacheMiddleware cacher;

    public static void startCache(){
        if (cacher == null) {
            try {
                cacher = ResponseCacheMiddleware.addCache(AsyncHttpClient.getDefaultInstance(),
                        AppContext.getContext().getFileStreamPath("asynccache"),
                        1024 * 1024 * 10);
            }
            catch (IOException e) {
                Log.d("HttpClient","Cache init error");
            }
        }
    }

    public static void get(String url, AsyncHttpClient.StringCallback stringCallback) {
        AsyncHttpClient.getDefaultInstance().executeString(new AsyncHttpGet(url), stringCallback);

    }

    public static void get(String url, HttpConnectCallback callback) {
        AsyncHttpClient.getDefaultInstance().execute(new AsyncHttpGet(url),callback);

    }
}
