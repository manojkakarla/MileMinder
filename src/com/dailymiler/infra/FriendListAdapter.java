package com.dailymiler.infra;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.dailymiler.R;
import com.dailymiler.domain.User;

import java.util.ArrayList;
import java.util.List;


public class FriendListAdapter extends ArrayAdapter<User> {

    private List<User> users = new ArrayList<User>();

    public FriendListAdapter(Activity activity, List<User> users) {
        super(activity, 0, users);
        this.users = users;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Activity activity = (Activity) getContext();
        LayoutInflater inflater = activity.getLayoutInflater();

        // Inflate the views from XML
        View rowView = inflater.inflate(R.layout.friends, null);
        User user = getItem(position);
        // Load the image and set it on the ImageView
        ImageView imageView = (ImageView) rowView.findViewById(R.id.friend_photo);

        // Set the text on the TextView
        TextView name = (TextView) rowView.findViewById(R.id.name);
        TextView location = (TextView) rowView.findViewById(R.id.location);
        name.setText(user.getDisplayName());
        location.setText(user.getLocation());
        UrlImageViewHelper.setUrlDrawable(imageView, user.getPhotoUrl());

        return rowView;
    }

    public List<User> getUsers() {
        return users;
    }

}
