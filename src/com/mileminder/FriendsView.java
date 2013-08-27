package com.mileminder;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class FriendsView {

	private static final String TAG = "MileMeter";

	public ArrayList<JSONObject> retrieveJSONArray(String urlString) {
		String result = queryRESTurl(urlString);
		ArrayList<JSONObject> friends = new ArrayList<JSONObject>();
		if (result != null) {
			try {
				JSONObject json = new JSONObject(result);
				JSONArray jobsArray = json.getJSONArray("friends");

				for (int a = 0; a < jobsArray.length(); a++) {
					JSONObject friend = jobsArray.getJSONObject(a);

					friends.add(friend);
				}
				return friends;
			} catch (JSONException e) {
				Log.e("JSON", "There was an error parsing the JSON", e);				
			}			
		}else{
			JSONObject myObject = new JSONObject();
			try {
				myObject.put("photo_url","grey");
				myObject.put("display_name", "None");
				myObject.put("location", "None");
				friends.add(myObject);
			} catch (JSONException e1) {
				Log.e("JSON", "There was an error creating the JSONObject", e1);
			}
		}
		return friends;
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
				InputStream instream = entity.getContent();
				String result = convertStreamToString(instream);
				Log.i(TAG, "Result of converstion: [" + result + "]");
				instream.close();
				return result;
			}

		} catch (ClientProtocolException e) {

			Log.e("REST", "There was a protocol based error", e);

		} catch (IOException e) {
			Log.e("REST", "There was an IO Stream related error", e);
		}
		return null;
	}
	
	private String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
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
