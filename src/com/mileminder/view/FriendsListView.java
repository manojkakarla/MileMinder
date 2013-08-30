package com.mileminder.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.mileminder.R;
import com.mileminder.domain.FriendsList;
import com.mileminder.domain.User;
import com.mileminder.infra.DataLoader;
import com.mileminder.infra.FriendListAdapter;

import java.util.List;


public class FriendsListView extends Activity implements OnItemClickListener {

    private FriendListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        FriendsList friends = new DataLoader<FriendsList>().fetchData(buildUrl(), FriendsList.class);
        List<User> userList = friends.getFriendsList();
        adapter = new FriendListAdapter(this, userList.size() > 20 ? userList.subList(0, 20) : userList);
        ListView listView = (ListView) findViewById(R.id.friends_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    private String buildUrl() {
        Bundle b = getIntent().getExtras();
        String user = b.getString(MileMinder.USER_NAME);
        return String.format(
                "https://api.dailymile.com/people/%s/friends.json", user != null && !user.isEmpty() ? user : "manojk");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
        List<User> users = adapter.getUsers();
        User user = users.get(position);
        Intent profileIntent = new Intent(getApplicationContext(), ProfileView.class);
        profileIntent.putExtra(ProfileView.USER_NAME, user.getUserName());

        startActivity(profileIntent);
    }
}