package de.haw_hamburg.tddandroid;

import de.haw_hamburg.tddandroid.utils.User;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class RegisterActivity extends Activity {

	EditText emailEditText = null;
	EditText usernameEditText = null;
	EditText passwordEditText = null;
	CheckBox tosCheckbox = null;
	Spinner mobileSpinner = null;
	Button registerButton = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		emailEditText = (EditText) findViewById(R.id.emailEditText);
		usernameEditText = (EditText) findViewById(R.id.usernameEditText);
		passwordEditText = (EditText) findViewById(R.id.passwordEditText);
		mobileSpinner = (Spinner) findViewById(R.id.mobileSpinner);
		registerButton = (Button) findViewById(R.id.registerButton);
		
		// Show the Up button in the action bar.
		setupActionBar();
		
		registerButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//TODO Fehlerbehandlung TOS Checkbox
				registerUser();		
			}
		});
		
	}

	public void registerUser(){
		Intent nextScreen = new Intent(this, UserValidationActivity.class);
	    
		String userName = usernameEditText.getText().toString();
		String email = emailEditText.getText().toString();
		String password = passwordEditText.getText().toString();
		String prefferedOS = mobileSpinner.getSelectedItem().toString();
		
		User user = new User(email, userName);
		user.setPassword(password);
		user.setMobileOS(prefferedOS);
		
		nextScreen.putExtra("user", user);
		startActivity(nextScreen);
	}
	
	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
