package com.aeon.myoncall;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;

public class BaseActivity extends Activity {

	protected boolean bTrackScreen = true;
	protected boolean bTrackDuration = true;

	protected String screenName = "MyOnCall Screen";
	Tracker tracker;
	protected long startTime;
	protected long endTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		tracker = ((MyApplication) getApplication()).getDefaultTracker();
		if (bTrackScreen) {
			tracker.setScreenName(screenName);
			tracker.send(new HitBuilders.AppViewBuilder().build());
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		startTime = System.currentTimeMillis();
		if (bTrackScreen) {
			GoogleAnalytics.getInstance(this).reportActivityStart(this);
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		endTime = System.currentTimeMillis();

		if (bTrackScreen) {
			GoogleAnalytics.getInstance(this).reportActivityStop(this);
		}

		if (bTrackDuration) {
			sendTimingInfo("Screen Duration", endTime - startTime, screenName, null);
		}
	}

	// Send Event
	protected void setMyEvent(String categoryName, String action, String label) {
		if (TextUtils.isEmpty(categoryName)) {
			categoryName = "Category";
		}

		if (TextUtils.isEmpty(action)) {
			action = "Action";
		}

		if (TextUtils.isEmpty(label)) {
			label = "Label";
		}

		tracker.send(new HitBuilders.EventBuilder().setCategory(categoryName).setAction(action)
				.setLabel(label).build());
	}

	// Send Timining Info
	protected void sendTimingInfo(String category, long interval, String timingName,
			String timingLabel) {
		// Build and send timing.
		tracker.send(new HitBuilders.TimingBuilder().setCategory(category).setValue(interval)
				.setVariable(timingName).setLabel(timingLabel).build());
	}

	// Set ScreenView Name
	protected void setScreenName(String viewName) {
		if (!TextUtils.isEmpty(viewName)) {
			screenName = viewName;
		}
	}

	// Disable Track Report on current screen
	protected void disableScreenTrack() {
		bTrackScreen = false;
	}

	// Enable Track Report on current screen
	protected void enableScreenTrack() {
		bTrackScreen = true;
	}

	// Disable Track Report Duration
	protected void disableDurationTrack() {
		bTrackDuration = false;
	}

	// Enable Track Report Duration
	protected void enableDurationTrack() {
		bTrackDuration = true;
	}
}
