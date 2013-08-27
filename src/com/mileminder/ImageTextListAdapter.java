package com.mileminder;


import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class ImageTextListAdapter extends ArrayAdapter<JSONObject> {

	private List<Friend> friends =  new ArrayList<Friend>();
	
	public ImageTextListAdapter(Activity activity, ArrayList<JSONObject> friends) {
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
		ImageView imageView = (ImageView) rowView.findViewById(R.id.photo);
		String photoUrl = "";
		try {
			photoUrl = (String) imageAndText.get("photo_url");
			imageView.setImageDrawable(loadImageFromWeb(photoUrl));
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

	private Drawable loadImageFromWeb(String url){
		try{
			InputStream is = (InputStream) new URL(url).getContent();
			Drawable d = Drawable.createFromStream(is, "src name");
			return d;
		}catch (Exception e) {
			System.out.println("Exc="+e);
			return null;
		}
	}
	
	public void clickHandler(){
		
	}

	public List<Friend> getFriends() {
		return friends;
	}
}
