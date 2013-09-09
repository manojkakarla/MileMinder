package com.dailymiler.view;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.dailymiler.R;


public class NewPostView extends Activity {
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post);
		
		Spinner spinnerDist = (Spinner)findViewById(R.id.spinnerDistance);
        
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.unitsDistance, R.layout.spinner_dropdown);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerDist.setAdapter(adapter);
		
		Spinner spinnerWorkout = (Spinner)findViewById(R.id.spinnerWorkoutType);
		
		adapter = ArrayAdapter.createFromResource(this, R.array.workoutType, R.layout.spinner_dropdown);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerWorkout.setAdapter(adapter);
	}


}