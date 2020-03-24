package com.aeon.myoncall;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class WebViewAct extends BaseActivity {

	TextView text_web;
	Button btn_webback;
	WebView webView1;
	private ProgressDialog progDailog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		if (TabMore.webvw_flag.equalsIgnoreCase("event")) {
			setScreenName("Events Screen");
		} else if (TabMore.webvw_flag.equalsIgnoreCase("facebook")) {
			setScreenName("FaceBook Screen");
		} else if (TabMore.webvw_flag.equalsIgnoreCase("hotjobs")) {
			setScreenName("Hot Jobs Screen");
		} else if (TabMore.webvw_flag.equalsIgnoreCase("linkedin")) {
			setScreenName("LinkedIn Screen");
		} else if (TabMore.webvw_flag.equalsIgnoreCase("staffpolicy")) {
			setScreenName("Staff policies Screen");
		} else if (TabMore.webvw_flag.equalsIgnoreCase("trtaining")) {
			setScreenName("Traning Information Screen");
		} else if (TabMore.webvw_flag.equalsIgnoreCase("homepage")) {
			setScreenName("Homepage Screen");
		}

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_view_lay);

		btn_webback = (Button) findViewById(R.id.btn_webback);
		text_web = (TextView) findViewById(R.id.text_web);
		webView1 = (WebView) findViewById(R.id.webView1);
		progDailog = ProgressDialog.show(this, "Loading", "Please wait...", true);
		progDailog.setCancelable(false);
		webView1.getSettings().setJavaScriptEnabled(true);
		webView1.getSettings().setLoadWithOverviewMode(true);
		webView1.getSettings().setUseWideViewPort(true);
		webView1.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				progDailog.show();
				view.loadUrl(url);

				return true;
			}

			@Override
			public void onPageFinished(WebView view, final String url) {
				progDailog.dismiss();
			}
		});

		if (TabMore.webvw_flag.equalsIgnoreCase("event")) {
			// startWebView("http://oncallpersonnel.com.au/resources/news/events");
			webView1.loadUrl("http://oncallpersonnel.com.au/news/events");
		} else if (TabMore.webvw_flag.equalsIgnoreCase("facebook")) {
			webView1.loadUrl("https://www.facebook.com/OncallPersonnel");
			// startWebView("https://www.facebook.com/OncallPersonnel");
		} else if (TabMore.webvw_flag.equalsIgnoreCase("hotjobs")) {
			webView1.loadUrl("http://oncallpersonnel.com.au/careers/hot-jobs");
		} else if (TabMore.webvw_flag.equalsIgnoreCase("linkedin")) {
			webView1.loadUrl("https://www.linkedin.com/company/oncall-personnel");
		} else if (TabMore.webvw_flag.equalsIgnoreCase("staffpolicy")) {
			webView1.loadUrl("http://oncallpersonnel.com.au/staff-policies");
		} else if (TabMore.webvw_flag.equalsIgnoreCase("trtaining")) {
			webView1.loadUrl("http://oncallpersonnel.com.au/index.php/training/training-calendar");
		} else if (TabMore.webvw_flag.equalsIgnoreCase("homepage")) {
			webView1.loadUrl("http://oncallpersonnel.com.au/");
		}

		btn_webback.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
}
