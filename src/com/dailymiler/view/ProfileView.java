package com.dailymiler.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.dailymiler.R;
import com.dailymiler.domain.Entry;
import com.dailymiler.domain.EntryList;
import com.dailymiler.domain.User;
import com.dailymiler.infra.DataLoader;
import com.dailymiler.infra.EntryListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProfileView extends Activity {
    public static final String USER_NAME = "userName";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        Bundle b = getIntent().getExtras();
        String userName = b.getString(USER_NAME);
        String pageUrl = String.format("https://api.dailymile.com/people/%s.json", userName);
        User user = new DataLoader<User>().fetchData(pageUrl, User.class);
        ImageView imageView = (ImageView) findViewById(R.id.profile_pic);

        // Set the text on the TextView
        setText(R.id.name, user.getDisplayName());
        setText(R.id.location, user.getLocation());
        setText(R.id.goal, user.getGoal());
        setText(R.id.url, user.getUrl());

        UrlImageViewHelper.setUrlDrawable(imageView, user.getPhotoUrl());

        EntryList entries = new DataLoader<EntryList>().fetchData(String.format("http://api.dailymile.com/people/%s/entries.json", userName), EntryList.class);
        List<Entry> entryList = entries.getEntryList();
        EntryListAdapter adapter = new EntryListAdapter(this, limit(entryList, 10));
        ListView listView = (ListView) findViewById(R.id.entry_list);
        listView.setAdapter(adapter);

    }

    private List<Entry> limit(List<Entry> entryList, int limit) {
        List<Entry> entries = new ArrayList<Entry>();
        for (Entry entry : entryList) {
            if(entry.getWorkout() != null) entries.add(entry);
            if(entries.size() == limit) break;
        }
        return entries;
    }

    private void setText(int id, String value) {
        TextView textView = (TextView) findViewById(id);
        textView.setText(value);
    }
}
