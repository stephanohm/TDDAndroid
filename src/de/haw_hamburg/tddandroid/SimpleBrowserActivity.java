package de.haw_hamburg.tddandroid;


import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SimpleBrowserActivity extends Activity {

	private WebView webView = null;
	private Button browserButton = null;
	private EditText urlEditText = null;
	private Spinner urlSpinner = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_browser);
		// Show the Up button in the action bar.
		setupActionBar();
		browserButton = (Button) findViewById(R.id.browseButton);
		urlEditText = (EditText) findViewById(R.id.URLeditText);
		urlSpinner = (Spinner) findViewById(R.id.urlSpinner);
		webView = (WebView) findViewById(R.id.browserWebView);
		
		WebViewClient webClient = new WebViewClient()
	    {
	        @Override
	        public boolean shouldOverrideUrlLoading(WebView  view, String  url)
	        {
	            return false;
	        }
	    };
	    webView.setWebViewClient(webClient);
		
		browserButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (urlSpinner.getSelectedItem().toString()
						.equals("URL as entered")) {
					String url = urlEditText.getText().toString();
					refreshWebview(url);
				} else if (urlSpinner.getSelectedItem().toString()
						.equals("long page to scroll")) {
					refreshWebview("file:///android_asset/html/pageToScroll.html");
				} else if (urlSpinner.getSelectedItem().toString()
						.equals("standard html elements page")) {
					refreshWebview("file:///android_asset/html/myTestPage.html");
				}
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
	
	public void refreshWebview(String url){
		webView.loadUrl(url);
	}

}