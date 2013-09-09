package com.dailymiler.infra;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.dailymiler.R;
import com.dailymiler.domain.Entry;
import com.dailymiler.domain.Workout;

import java.util.*;


public class EntryListAdapter extends ArrayAdapter<Entry> {

    private List<Entry> entries = new ArrayList<Entry>();
    private static final Map<String, Integer> idMap = new HashMap<String, Integer>();
    static {
           idMap.put("running", R.drawable.running);
           idMap.put("walking", R.drawable.walking);
           idMap.put("cycling", R.drawable.cycling);
           idMap.put("crossfit", R.drawable.crossfit);
           idMap.put("swimming", R.drawable.swimming);

           idMap.put("great", R.drawable.great);
           idMap.put("good", R.drawable.good);
           idMap.put("alright", R.drawable.alright);
           idMap.put("blah", R.drawable.blah);
           idMap.put("tired", R.drawable.tired);
           idMap.put("injured", R.drawable.injured);
    }

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
        activityView.setImageResource(idMap.get(workout.getActivityType().toLowerCase()));

        if (workout.getFelt() != null) {
            ImageView imageView = (ImageView) rowView.findViewById(R.id.felt_icon);
            imageView.setImageResource(idMap.get(workout.getFelt()));
        }
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
