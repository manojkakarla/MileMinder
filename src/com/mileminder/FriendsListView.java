package com.mileminder;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import com.mileminder.domain.Friend;
import com.mileminder.infra.FriendsLoader;
import com.mileminder.infra.ImageTextListAdapter;
import org.json.JSONObject;

import java.util.List;


public class FriendsListView extends Activity implements OnItemClickListener {

	private ImageTextListAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);

		FriendsLoader fv = new FriendsLoader();
        String urlString = buildUrl();
		List<JSONObject> friends = fv.retrieveJSONArray(urlString);
        if(friends.size() > 20) friends = friends.subList(0, 20);
		 adapter = new ImageTextListAdapter(this,friends);
        ListView listView = (ListView) findViewById(R.id.list);
		listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
	}

    private String buildUrl() {
        Bundle b = getIntent().getExtras();
        String user = b.getString(MileMinder.USER_NAME);
        return String.format(
                "https://api.dailymile.com/people/%s/friends.json",user != null && !user.isEmpty() ? user : "manojk");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
		 Toast.makeText(getBaseContext(), "Sample", Toast.LENGTH_SHORT).show();

		 List<Friend> friends = adapter.getFriends();
	        Friend friend = friends.get(position);
	        Toast.makeText(getBaseContext(), friend.getDisplayName() + " ID #" + friend.getUserName(), 1).show();
	    }
    }