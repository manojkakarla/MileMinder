package com.mileminder.infra;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;

    public class FriendFetcher extends AsyncTask<String, Integer, String> {


        private static final String TAG = "MileMinder";

        // Do the long-running work in here
        public String doInBackground(String... urls) {
            return queryRESTurl(urls[0]);
        }

        private String queryRESTurl(String url) {
        		HttpClient httpclient = new DefaultHttpClient();
        		HttpGet httpget = new HttpGet(url);
        		HttpResponse response;
        		try {
        			response = httpclient.execute(httpget);
        			Log.i(TAG, "Status:[" + response.getStatusLine().toString() + "]");
        			HttpEntity entity = response.getEntity();
        			if (entity != null) {
        				InputStream stream = entity.getContent();
        				String result = convertStreamToString(stream);
        				Log.i(TAG, "Result of conversation: [" + result + "]");
        				stream.close();
        				return result;
        			}

        		}  catch (Exception e) {
                    Toast.makeText(null, e.getMessage(), Toast.LENGTH_LONG).show();
        			Log.e("REST", "There was an IO Stream related error", e);
        		}
        		return null;
        	}

        	private String convertStreamToString(InputStream is) {

        		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        		StringBuilder sb = new StringBuilder();
        		String line;
        		try {
        			while ((line = reader.readLine()) != null) {
        				sb.append(line + "\n");
        			}
        		} catch (IOException e) {
        			e.printStackTrace();
        		} finally {
        			try {
        				is.close();
        			} catch (IOException e) {
        				e.printStackTrace();
        			}
        		}
        		return sb.toString();
        	}
    }
