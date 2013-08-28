package com.mileminder.infra;


import android.os.AsyncTask;
import android.util.Log;
import com.mileminder.infra.FriendFetcher;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class FriendsLoader {

	private static final String TAG = "MileMeter";

	public ArrayList<JSONObject> retrieveJSONArray(String urlString) {
		AsyncTask<String, Integer, String> resultTask = new FriendFetcher().execute(urlString);
        ArrayList<JSONObject> friends = new ArrayList<JSONObject>();
        try {
            String result = resultTask.get(20, TimeUnit.SECONDS);
            if (result != null) {
                JSONObject json = new JSONObject(result);
                JSONArray jobsArray = json.getJSONArray("friends");

                for (int a = 0; a < jobsArray.length(); a++) {
                    JSONObject friend = jobsArray.getJSONObject(a);

                    friends.add(friend);
                }
                return friends;

            } else {
                JSONObject myObject = new JSONObject();
                myObject.put("photo_url", "grey");
                myObject.put("display_name", "None");
                myObject.put("location", "None");
                friends.add(myObject);
            }
        } catch (Exception e1) {
            Log.e(TAG, "There was an error loading data", e1);
        }
            return friends;
    }
}
