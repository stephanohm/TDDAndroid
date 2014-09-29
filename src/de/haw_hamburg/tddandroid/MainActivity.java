package de.haw_hamburg.tddandroid;

import android.app.Activity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	AlertDialog.Builder adb = null;
	AlertDialog ad = null;
	TextView response = null;
	Button displayToastButton = null;
	RadioGroup radioGroup = null;
	RadioButton radioButton = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button displayTextButton = (Button) findViewById(R.id.displayText);
		Button openWebviewButton = (Button) findViewById(R.id.openWebview);
		Button openSimpleBroserButton = (Button) findViewById(R.id.openSimpleBrowser);
		Button openRegisterForm = (Button) findViewById(R.id.openRegisterForm);
		Button displayAlertButton = (Button) findViewById(R.id.registerButton);
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		response = (TextView) findViewById(R.id.response);
		displayToastButton = (Button) findViewById(R.id.displayToast);
		
		
		displayAlertButton.setOnClickListener(new OnClickListener() {
			
			
			@Override
			public void onClick(View v) {
				showAlertDialog();
			}
			
		});
		
		displayTextButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText editText1 = (EditText) findViewById(R.id.enterText);
				TextView textView1 = (TextView) findViewById(R.id.textView1);
				String text = editText1.getText().toString();
				editText1.setText("");
				textView1.setText(text);
			}
		});
		
		displayToastButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				displayToast();	
			}
		});
		
		openRegisterForm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				openRegisterActivity();
			}
		});
		
		openWebviewButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				openWebviewActivity();
			}
		});
		
		openSimpleBroserButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				openSimpleBrowserActivity();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void openWebviewActivity() {
		Intent nextScreen = new Intent(this, WebViewNativeInteraction.class);
	    startActivity(nextScreen);
	}
	
	public void openRegisterActivity(){
		Intent nextScreen = new Intent(this, RegisterActivity.class);
	    startActivity(nextScreen);
	}
	
	public void openSimpleBrowserActivity() {
		Intent nextScreen = new Intent(this, SimpleBrowserActivity.class);
	    startActivity(nextScreen);
	}
	
	public void showAlertDialog(){
		adb = new AlertDialog.Builder(this);
		adb.setCancelable(false);
		adb.setMessage("respond to alert");
		adb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				response.setText("You answered YES");
			
		}
			});
		
		adb.setNegativeButton("No", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {
				response.setText("You answered NO");
			}
		});
		ad = adb.create();
		ad.setTitle("This is an Alert");
		ad.show();
	}
	
	public void displayToast(){
		Toast myToast = new Toast(this);
		myToast.makeText(this, R.string.toast, Toast.LENGTH_LONG).show();
	}
	
	public void onRadioButtonClicked(View view) {
	    // Is the button now checked?
	    boolean checked = ((RadioButton) view).isChecked();
	    
	    // Check which radio button was clicked
	    switch(view.getId()) {
	        case R.id.redRadioButton:
	            if (checked)
	            	Toast.makeText(this, "red selected", Toast.LENGTH_LONG).show();
	            break;
	        case R.id.yellowRadioButton:
	            if (checked)
	            	Toast.makeText(this, "yellow selected", Toast.LENGTH_LONG).show();
	            break;
	        case R.id.greenRadioButton:
	            if (checked)
	            	Toast.makeText(this, "green selected", Toast.LENGTH_LONG).show();
	            break;
	    }
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}


}
