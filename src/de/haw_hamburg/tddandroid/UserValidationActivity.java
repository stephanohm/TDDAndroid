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
import android.widget.TextView;

public class UserValidationActivity extends Activity {

	TextView emailTextView = null;
	TextView userNameTextView = null;
	TextView passwordTextView = null;
	TextView mobileTextView = null;
	Button returnButton = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_validation);
		
		emailTextView = (TextView) findViewById(R.id.emailTextView);
		userNameTextView = (TextView) findViewById(R.id.userNameTextView);
		passwordTextView = (TextView) findViewById(R.id.passwordTextView);
		mobileTextView = (TextView) findViewById(R.id.mobileTextView);
		returnButton = (Button) findViewById(R.id.returnButton);
		Bundle extras = getIntent().getExtras();
	    if (extras != null) {
	      User user = (User) extras.getSerializable("user");
	      
	      emailTextView.setText(user.getEmail());
	      userNameTextView.setText(user.getUserName());
	      passwordTextView.setText(user.getPassword());
	      mobileTextView.setText(user.getMobileOS());
	    }
	    
	    returnButton.setOnClickListener(new OnClickListener() {
	    	
			@Override
			public void onClick(View v) {
				Intent nextScreen = new Intent(getApplicationContext() ,MainActivity.class);
			    startActivity(nextScreen);
				
			}
		});
		
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
		getMenuInflater().inflate(R.menu.user_validation, menu);
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