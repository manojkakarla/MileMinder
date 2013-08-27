package com.mileminder;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONObject;

import java.util.List;

public class FriendsActivity extends ListActivity {

	private ImageTextListAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		registerForContextMenu(getListView());


		FriendsView fv = new FriendsView();
		Bundle b = getIntent().getExtras();
		String user = b.getString(MileMinder.USER_NAME);
		String urlString = String.format(
				"https://api.dailymile.com/people/%s/friends.json",user != null && !user.isEmpty() ? user : "manojk");
		List<JSONObject> friends = fv.retrieveJSONArray(urlString);
        if(friends.size() > 20) friends = friends.subList(0, 20);
		 adapter = new ImageTextListAdapter(this,friends);
		setListAdapter(adapter);
	}

	 @Override
	    protected void onListItemClick(ListView l, View v, int position, long id) {
		 Toast.makeText(getBaseContext(), "Sample", Toast.LENGTH_SHORT);
		 
		 List<Friend> friends = adapter.getFriends();
	        Friend friend = friends.get(position);
	        Toast.makeText(getBaseContext(), friend.getDisplayName() + " ID #" + friend.getUserName(), 1).show();
	    }
}