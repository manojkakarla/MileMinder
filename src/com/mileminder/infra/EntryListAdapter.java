package com.mileminder.infra;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.mileminder.R;
import com.mileminder.domain.Entry;
import com.mileminder.domain.Workout;

import java.util.ArrayList;
import java.util.List;


public class EntryListAdapter extends ArrayAdapter<Entry> {

    private List<Entry> entries = new ArrayList<Entry>();

    public EntryListAdapter(Activity activity, List<Entry> entries) {
        super(activity, 0, entries);
        this.entries = entries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Activity activity = (Activity) getContext();
        LayoutInflater inflater = activity.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.entry, null);
        Entry entry = getItem(position);
        Workout workout = entry.getWorkout();

        setText(rowView, R.id.title, workout.getTitle());
        setText(rowView, R.id.distance, workout.formatDistance());
        setText(rowView, R.id.time, workout.formatDuration());
        setText(rowView, R.id.message, entry.getMessage());

        ImageView activityView = (ImageView) rowView.findViewById(R.id.activity_icon);
        int activityId = rowView.getResources().getIdentifier("com.mileminder.infra:drawable/" + workout.getActivityType().toLowerCase() + ".png", null, null);
        activityView.setImageResource(activityId);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.felt_icon);
        int id = rowView.getResources().getIdentifier("com.mileminder.infra:drawable/" + workout.getFelt() + ".png", null, null);
        imageView.setImageResource(id);

        return rowView;
    }

    private void setText(View view, int id, String value) {
        TextView textView = (TextView) view.findViewById(id);
        textView.setText(value);
    }

    public List<Entry> getEntries() {
        return entries;
    }
}
