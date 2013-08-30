package com.mileminder.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;
import com.mileminder.R;

public class MileMinder extends Activity {
	protected static final String USER_NAME = "username";
    private static final String YOUR_API_KEY = "Y4i6ErZNa9kuyIsJr2htG6G71QEHszQ4zjIT9dg0";
    private static final String YOUR_API_SECRET = "1phJOHA1VqNxFHzvge3MARiJMvJCP4iodIYgfXCR";

    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

        findViewById(R.id.buttonLogin).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(getApplicationContext(), FriendsListView.class);
				Editable user = ((EditText)findViewById(R.id.username)).getText();
				Toast.makeText(getBaseContext(), user.toString(), Toast.LENGTH_LONG).show();
				myIntent.putExtra(USER_NAME, user.toString());
				startActivity(myIntent);    
			}
		});
	}
	
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu) {
	     MenuInflater inflater = getMenuInflater();
	     inflater.inflate(R.menu.game_menu, menu);
	     return true;
	 }
	 
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	     switch (item.getItemId()) {
	     case R.id.new_post:
	         newPost();
	         return true;
	     case R.id.help:
	         showHelp();
	         return true;
	     default:
	         return super.onOptionsItemSelected(item);
	     }
	 }

	private void showHelp() {
		// TODO Auto-generated method stub
		
	}

	private void newPost() {
		Intent myIntent = new Intent(getApplicationContext(), NewPostView.class);
		startActivity(myIntent);    
	}

}