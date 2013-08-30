package com.mileminder.infra;


import android.os.AsyncTask;
import android.util.Log;
import com.owlike.genson.Genson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.concurrent.TimeUnit;

public class DataLoader<T> {

    private static final String TAG = "MileMinder";

    public T fetchData(String urlString, Class<T> aClass) {
        AsyncTask<String, Integer, T> result = new FriendFetcher<T>(aClass).execute(urlString);
        try {
            return result.get(20, TimeUnit.SECONDS);
        } catch (Exception e1) {
            Log.e(TAG, "There was an error loading data", e1);
        }
        return null;
    }

    private static class FriendFetcher<T> extends AsyncTask<String, Integer, T> {
        private Class<T> aClass;

        public FriendFetcher(Class<T> aClass) {
            this.aClass = aClass;
        }

        public T doInBackground(String... urls) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(urls[0]);
            HttpResponse response;
            try {
                response = httpclient.execute(httpget);
                Log.i(TAG, "Status:[" + response.getStatusLine().toString() + "]");
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return new Genson().deserialize(entity.getContent(), aClass);
                }
            } catch (Exception e) {
                Log.e(TAG, "There was an IO Stream related error", e);
            }
            return null;
        }


    }
}
