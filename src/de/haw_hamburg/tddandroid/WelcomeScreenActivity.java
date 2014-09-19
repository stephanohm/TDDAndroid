package de.haw_hamburg.tddandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class WelcomeScreenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome_screen);
		
		Button btnLaunchMain = (Button) findViewById(R.id.btnLaunchMain);
		
		btnLaunchMain.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startMainAvtivity();
			}
		});
	}
	
	
	public void startMainAvtivity(){
		Intent nextScreen = new Intent(this, MainActivity.class);
	    startActivity(nextScreen);
	}
}
