package de.haw_hamburg.tddandroid;

import org.json.JSONException;
import org.json.JSONObject;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class WebViewNativeInteraction extends Activity {

	WebView webview;
	EditText fnameEditText;
	EditText lnameEditText;
	Button updateWebViewButton;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_view_native_interaction);

		fnameEditText = (EditText)this.findViewById(R.id.fnameEditText);
		lnameEditText = (EditText)this.findViewById(R.id.lnameEditText);
		updateWebViewButton = (Button)this.findViewById(R.id.updateWebViewButton);
		webview = (WebView)this.findViewById(R.id.webView);

		updateWebViewButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				sendNamesToWebView();
			}
		});
		setupActionBar();
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
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onStart() {
    	super.onStart();
    	WebSettings webSettings = webview.getSettings();
    	webSettings.setJavaScriptEnabled(true);
    	webview.loadUrl("file:///android_asset/html/webViewContent.html");
    	
    	webview.addJavascriptInterface(new WebViewInterface(this), "Android");
    	
	}

	public void sendNamesToWebView() {
		JSONObject namesJson = new JSONObject();
		try {
			namesJson.put("fname", fnameEditText.getText().toString());
			namesJson.put("lname", lnameEditText.getText().toString());
			webview.loadUrl( "javascript:setNames(" + namesJson.toString() + ")" );
		} catch (JSONException e) {			
			Log.e(getPackageName(), "Failed to create JSON object for web view");
		}
	}

	public class WebViewInterface {
	    Context mContext;

	    /** Instantiate the interface and set the context */
	    WebViewInterface(Context c) {
	        mContext = c;
	    }
	    
	    @JavascriptInterface 
	    public void updateNames(String namesJsonString) {
	    	Log.d(getPackageName(), "Sent from webview: " + namesJsonString);
	    	try {

	    		JSONObject namesJson = new JSONObject(namesJsonString);
				final String firstName = namesJson.getString("fname");  
				final String lastName = namesJson.getString("lname");  

				// When invoked from Javascript this is executed on a thread other than the UI thread
				// Since we want to update the native UI controls we must create a runnable for the main UI thread.
		    	runOnUiThread(new Runnable() {
		            public void run() {
		            	fnameEditText.setText(firstName);
		            	lnameEditText.setText(lastName);
		            }
		        });

	    	} catch (JSONException e) {
				Log.e(getPackageName(), "Failed to create JSON object from web view data");
			}

	    }

	}


}