package com.aeon.myoncall;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TabAvailability extends BaseActivity implements OnClickListener {

	Button btn_quickavailb, btn_advvavilbs, btn_infqui, btn_infadv;
	RelativeLayout lay_bggg;

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		lay_bggg.getBackground().setAlpha(80);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		setScreenName("Availability Screen");
		//disableTrackReport();
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_availibility);
		
		btn_quickavailb = (Button) findViewById(R.id.btn_quickavailb);
		btn_advvavilbs = (Button) findViewById(R.id.btn_advvavilbs);
		btn_infqui = (Button) findViewById(R.id.btn_infqui);
		btn_infadv = (Button) findViewById(R.id.btn_infadv);
		lay_bggg = (RelativeLayout) findViewById(R.id.lay_bggg);
		btn_quickavailb.setOnClickListener(this);
		btn_advvavilbs.setOnClickListener(this);
		btn_infqui.setOnClickListener(this);
		btn_infadv.setOnClickListener(this);
		lay_bggg.getBackground().setAlpha(80);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();

		if (id == R.id.btn_quickavailb) {
			TabPage.tabHost.setCurrentTab(7);
		} else if (id == R.id.btn_advvavilbs) {

			TabPage.tabHost.setCurrentTab(8);
		} else if (id == R.id.btn_infqui) {
			CallDialogue("1");
		} else if (id == R.id.btn_infadv) {
			CallDialogue("2");
		}
	}

	private void CallDialogue(String here) {
		final Dialog Alert_dialogg;
		Alert_dialogg = new Dialog(TabAvailability.this);
		Alert_dialogg.requestWindowFeature(Window.FEATURE_NO_TITLE);
		Alert_dialogg.setContentView(R.layout.dialog_error);
		Alert_dialogg.setCancelable(false);

		TextView txt_msg = (TextView) Alert_dialogg.findViewById(R.id.shshs);
		Button btn_okk = (Button) Alert_dialogg.findViewById(R.id.btn_ok);
		TextView txt_errr = (TextView) Alert_dialogg.findViewById(R.id.txt_errr);
		if (here.equalsIgnoreCase("1")) {
			txt_msg.setText("My Quick Availibility gives you the \r\n ability to send in a 2 week period of \r\n "
					+ "your shift types. If you have regular \r\n availibility you can 'save favurites' \r\n"
					+ "and 'apply favourites' for your next \r\n submission. This will be submitted to \r\n "
					+ " availibility@oncall.com.au");
			txt_errr.setText("My Quick Availibility");
		} else if (here.equalsIgnoreCase("2")) {
			txt_msg.setText("Advanced Availibility allows you to \r\n submit up to 6 weeks of availibility in \r\n "
					+ "greater detail. You can specify the  \r\n times you are available each day as \r\n well as shift types. If you have a regular  \r\n"
					+ "availibility you can 'save favourites'  \r\n and 'apply favourites' for your next  \r\n submission. This will be submitted to \r\n availibility@myoncall.co.au");
			txt_errr.setText("Advanced Availibility");
		}

		Alert_dialogg.show();
		btn_okk.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Alert_dialogg.dismiss();

			}
		});
	}
}
