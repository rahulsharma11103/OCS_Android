package com.aeon.myoncall;

import com.aeon.utils.Utility;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

public class TabPage extends BaseTabActivity implements OnTabChangeListener {

	public static TabHost tabHost;
	public static String img_selection = "";
	public static String update_selection = "";
	public static Bitmap bitmap_prof, bitmap_user_profreal;

	public static String img_selec = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setScreenName("Main Screen");
		disableScreenTrack();
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_activity);		
		
		// Report Device And App Info
		setMyEvent("Device Info", "Device Type", "Android");
		setMyEvent("Device Info", "Device Model", Utility.getDeviceName());
		setMyEvent("Device Info", "OS Version", Utility.getVersionInfo());
		setMyEvent("App Info", "App Version", Utility.getAppVersionInfo(this));
		
		tabHost = getTabHost();
		tabHost.setOnTabChangedListener(this);

		TabHost.TabSpec spec;
		Intent intent;

		/************* TAB1 ************/
		// Create Intents to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, TabUser.class);
		spec = tabHost
				.newTabSpec("User")
				.setIndicator(
						getTabIndicator(tabHost.getContext(), "User",
								getResources().getDrawable(R.drawable.ic_drawable_user)))
				.setContent(intent);

		// Add intent to tab
		tabHost.addTab(spec);

		/************* TAB2 ************/
		intent = new Intent().setClass(this, TabAvailability.class);
		spec = tabHost
				.newTabSpec("Availibility")
				.setIndicator(
						getTabIndicator(tabHost.getContext(), "Availibility", getResources()
								.getDrawable(R.drawable.ic_drawable_avail))).setContent(intent);
		tabHost.addTab(spec);

		/************* TAB3 ************/
		intent = new Intent().setClass(this, TabTimesheet.class);
		spec = tabHost
				.newTabSpec("Timesheet")
				.setIndicator(
						getTabIndicator(tabHost.getContext(), "Timesheet", getResources()
								.getDrawable(R.drawable.ic_drwable_timesheet))).setContent(intent);
		tabHost.addTab(spec);

		/************* TAB4 ************/
		intent = new Intent().setClass(this, TabMyShifts.class);
		spec = tabHost
				.newTabSpec("MyShifts")
				.setIndicator(
						getTabIndicator(tabHost.getContext(), "MyShifts", getResources()
								.getDrawable(R.drawable.ic_drable_myshift))).setContent(intent);
		tabHost.addTab(spec);

		/************* TAB5 ************/
		intent = new Intent().setClass(this, TabMore.class);
		spec = tabHost
				.newTabSpec("More")
				.setIndicator(
						getTabIndicator(tabHost.getContext(), "More",
								getResources().getDrawable(R.drawable.ic_drawable_more)))
				.setContent(intent);
		tabHost.addTab(spec);

		/************* TAB6 Hidden ************/
		intent = new Intent().setClass(this, AddPhotoAct.class);
		spec = tabHost
				.newTabSpec("AddPhotoAct")
				.setIndicator(
						getTabIndicator(tabHost.getContext(), "More",
								getResources().getDrawable(R.drawable.ic_launcher)))
				.setContent(intent);
		tabHost.addTab(spec);

		/************* TAB7 Hidden ************/
		intent = new Intent().setClass(this, UserUpdate.class);
		spec = tabHost
				.newTabSpec("UserUpdate")
				.setIndicator(
						getTabIndicator(tabHost.getContext(), "More",
								getResources().getDrawable(R.drawable.ic_launcher)))
				.setContent(intent);
		tabHost.addTab(spec);

		/************* TAB8 Hidden ************/
		intent = new Intent().setClass(this, SelectAvailibilty.class);
		spec = tabHost
				.newTabSpec("UserUpdate")
				.setIndicator(
						getTabIndicator(tabHost.getContext(), "More",
								getResources().getDrawable(R.drawable.ic_launcher)))
				.setContent(intent);
		tabHost.addTab(spec);

		/************* TAB9 Hidden ************/
		intent = new Intent().setClass(this, SelectAdvAvailb.class);
		spec = tabHost
				.newTabSpec("UserUpdate")
				.setIndicator(
						getTabIndicator(tabHost.getContext(), "More",
								getResources().getDrawable(R.drawable.ic_launcher)))
				.setContent(intent);
		tabHost.addTab(spec);

		// Set Tab1 as Default tab and change image
		tabHost.getTabWidget().setCurrentTab(0);
		tabHost.getTabWidget().getChildAt(5).setVisibility(View.GONE);
		tabHost.getTabWidget().getChildAt(6).setVisibility(View.GONE);
		tabHost.getTabWidget().getChildAt(7).setVisibility(View.GONE);
		tabHost.getTabWidget().getChildAt(8).setVisibility(View.GONE);

	}

	@Override
	public void onTabChanged(String tabId) {
		// TODO Auto-generated method stub

		System.out.println("" + tabId);
		if (tabId.equalsIgnoreCase("User")) {
			// TabUser.img_userrr.setImageBitmap(UserUpdate.getImageBitmap(getApplicationContext(),
			// "user_prof", null));
			// tabHost.getTabWidget().getChildAt(5).setIndicator(getTabIndicator(tabHost.getContext(),
			// "More", getResources().getDrawable(R.drawable.ic_launcher)));

		} else if (tabId.equalsIgnoreCase("Availibility")) {

		} else if (tabId.equalsIgnoreCase("Timesheet")) {

		} else if (tabId.equalsIgnoreCase("User")) {

		} else if (tabId.equalsIgnoreCase("User")) {

		}
	}

	private View getTabIndicator(Context context, String string, Drawable icLauncher) {
		View view = LayoutInflater.from(context).inflate(R.layout.tab_item, null);
		ImageView iv = (ImageView) view.findViewById(R.id.imageView);
		iv.setBackgroundDrawable(icLauncher);
		TextView tv = (TextView) view.findViewById(R.id.textView);
		tv.setText(string);
		return view;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (TabPage.bitmap_user_profreal != null) {
			TabUser.img_userrr.setImageBitmap(TabPage.bitmap_user_profreal);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		
	}
}
