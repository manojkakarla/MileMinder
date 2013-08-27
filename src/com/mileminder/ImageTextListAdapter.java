package com.mileminder;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class ImageTextListAdapter extends ArrayAdapter<JSONObject> {

	private List<Friend> friends =  new ArrayList<Friend>();
	
	public ImageTextListAdapter(Activity activity, List<JSONObject> friends) {
		super(activity, 0, friends);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Activity activity = (Activity) getContext();
		LayoutInflater inflater = activity.getLayoutInflater();

		// Inflate the views from XML
		View rowView = inflater.inflate(R.layout.friends, null);
		JSONObject imageAndText = getItem(position);
		// Load the image and set it on the ImageView
		ImageView imageView = (ImageView) rowView.findViewById(R.id.friend_photo);
		String photoUrl = "";
		try {
			photoUrl = (String) imageAndText.get("photo_url");
			imageView.setImageBitmap(loadImageFromWeb(photoUrl));
		} catch (JSONException e) {
			photoUrl = "";
		}

		// Set the text on the TextView
		TextView name = (TextView) rowView.findViewById(R.id.name);
		TextView location = (TextView) rowView.findViewById(R.id.location);
		try {
			name.setText((String)imageAndText.get("display_name"));
			location.setText((String)imageAndText.get("location"));
			Friend friend = new Friend(name.getText().toString(), 
					(String)imageAndText.get("username"),
					location.getText().toString());
			friend.setPhotoUrl(photoUrl);
			friends.add(friend);
		} catch (JSONException e) {
			location.setText("JSON Exception");
		}

		return rowView;
	}

	private Bitmap loadImageFromWeb(String url){
		try{
            AsyncTask<String, Integer, InputStream> imageTask = new ImageLoader().execute(url);
            return BitmapFactory.decodeStream(imageTask.get());
		}catch (Exception e) {
			return null;
		}
	}
	
	public void clickHandler(){
		
	}

	public List<Friend> getFriends() {
		return friends;
	}

    private class ImageLoader extends AsyncTask<String,Integer, InputStream> {
        @Override
        protected InputStream doInBackground(String... urls) {
            try {
                return new URL(urls[0]).openConnection().getInputStream();
            } catch (IOException e) {
                return null;
            }
        }
    }
}
