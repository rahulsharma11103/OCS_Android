package com.aeon.myoncall;

import java.util.ArrayList;

import com.aeon.adapters.AvailibQAdapter;
import com.aeon.asynctask.GetNextDates;
import com.aeon.constants.AvailabilityDateQ;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SelectAvailibilty extends BaseActivity implements OnClickListener {

	Button btn_subbmt, btn_backavail;
	TextView txt_avvial;
	public static ListView listscd;
	public static int lay_width = 0;
	public static String openation = "";
	RelativeLayout lay_tt1, lay_tt2, lay_tt3, lay_tt4, lay_tt5, lay_tt6;
	public static ArrayList<String> dates = new ArrayList<String>();
	public static ArrayList<String> check_onee = new ArrayList<String>();
	public static ArrayList<String> chhck_twoo = new ArrayList<String>();
	public static ArrayList<String> check_three = new ArrayList<String>();
	public static ArrayList<String> check_four = new ArrayList<String>();
	public static ArrayList<String> check_five = new ArrayList<String>();

	public static ArrayList<String> fav_check_onee = new ArrayList<String>();
	public static ArrayList<String> fav_chhck_twoo = new ArrayList<String>();
	public static ArrayList<String> fav_check_three = new ArrayList<String>();
	public static ArrayList<String> fav_check_four = new ArrayList<String>();
	public static ArrayList<String> fav_check_five = new ArrayList<String>();
	ArrayList<AvailabilityDateQ> _data = new ArrayList<AvailabilityDateQ>();
	Button btnclearall, btnapplyfav, btnsavefav;
	SharedPreferences sh_Pref;
	Editor toEdit;
	String user_clid, user_fname, user_lname, user_emailss;

	boolean actStopped;

	static AvailibQAdapter adapter;

	// adapter.notifyDataSetChanged();
	// ls.setAdapter(adapter);

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		setScreenName("QuickAvailibility Screen");

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quick_avail);

		init();

		txt_avvial.setText("Quick Availibility");

		adapter = new AvailibQAdapter(GetNextDates.arrayy, this);
		listscd.setAdapter(adapter);
		
		openation = "";
		check_onee.clear();
		chhck_twoo.clear();
		check_three.clear();

		check_four.clear();
		check_five.clear();
		GetNextDates getDatess = new GetNextDates(getApplicationContext());
		getDatess.execute("");

		// ------------------------->>

		// int width=listscd.getLayoutParams().width;

		Resources resources = getResources();
		Configuration config = resources.getConfiguration();
		DisplayMetrics dm = resources.getDisplayMetrics();

		double screenWidthInPixels = (double) config.screenWidthDp * dm.density;
		double screenHeightInPixels = screenWidthInPixels * dm.heightPixels / dm.widthPixels;

		lay_width = (int) (screenWidthInPixels + .5);

		int remain_one = 0;
		int remm2 = 0;
		if (lay_width > 500) {
			lay_tt1.getLayoutParams().width = 175;
			remain_one = SelectAvailibilty.lay_width - 175;
			remm2 = remain_one / 5;
			lay_tt2.getLayoutParams().width = remm2;
			lay_tt3.getLayoutParams().width = remm2;
			lay_tt4.getLayoutParams().width = remm2;
			lay_tt5.getLayoutParams().width = remm2;
			lay_tt6.getLayoutParams().width = remm2;
		} else if (lay_width <= 500) {
			lay_tt1.getLayoutParams().width = 145;
			remain_one = SelectAvailibilty.lay_width - 145;
			remm2 = remain_one / 5;
			lay_tt2.getLayoutParams().width = remm2;
			lay_tt3.getLayoutParams().width = remm2;
			lay_tt4.getLayoutParams().width = remm2;
			lay_tt5.getLayoutParams().width = remm2;
			lay_tt6.getLayoutParams().width = remm2;
		}
		int xxx = (lay_width / 3) - 6;
		btnclearall.getLayoutParams().width = xxx;
		btnapplyfav.getLayoutParams().width = xxx;
		btnsavefav.getLayoutParams().width = xxx;

		// listscd.setOnItemClickListener(new
		// ListView(this).getOnItemClickListener());

		sh_Pref = getSharedPreferences("register_details", MODE_PRIVATE);
		toEdit = sh_Pref.edit();

		user_clid = sh_Pref.getString("on_call_id", null);
		user_fname = sh_Pref.getString("first_name", null);
		user_lname = sh_Pref.getString("last_name", null);
		user_emailss = sh_Pref.getString("email_id", null);

	}

	private void init() {
		// TODO Auto-generated method stub
		btn_subbmt = (Button) findViewById(R.id.btn_subbmt);
		btn_backavail = (Button) findViewById(R.id.btn_backavail);
		txt_avvial = (TextView) findViewById(R.id.txt_avvial);
		listscd = (ListView) findViewById(R.id.listscd);
		btnclearall = (Button) findViewById(R.id.btnclearall);
		btnapplyfav = (Button) findViewById(R.id.btnapplyfav);
		btnsavefav = (Button) findViewById(R.id.btnsavefav);
		btnclearall.setOnClickListener(this);
		btn_subbmt.setOnClickListener(this);
		btn_backavail.setOnClickListener(this);
		btnapplyfav.setOnClickListener(this);
		btnsavefav.setOnClickListener(this);
		lay_tt1 = (RelativeLayout) findViewById(R.id.lay_tt1);
		lay_tt2 = (RelativeLayout) findViewById(R.id.lay_tt2);
		lay_tt3 = (RelativeLayout) findViewById(R.id.lay_tt3);
		lay_tt4 = (RelativeLayout) findViewById(R.id.lay_tt4);
		lay_tt5 = (RelativeLayout) findViewById(R.id.lay_tt5);
		lay_tt6 = (RelativeLayout) findViewById(R.id.lay_tt6);

		actStopped = false;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId(), flagcheck = 0;

		if (id == R.id.btn_subbmt) {

			Log.e("SelectAvailabiity", "SUBMIT Called : dates size : " + dates.size());
			try {
				StringBuilder all_data = new StringBuilder();

				for (int i = 0; i < dates.size(); i++) {
					int x = 0;

					if (check_onee.get(i).equalsIgnoreCase("AM")) {
						all_data.append("  ").append(dates.get(i))
								.append(" - " + check_onee.get(i)).append(" \r\n");
					} else {
						x++;
					}
					if (chhck_twoo.get(i).equalsIgnoreCase("PM")) {
						all_data.append("  ").append(dates.get(i))
								.append(" - " + chhck_twoo.get(i)).append(" \r\n");
					} else {
						x++;
					}
					if (check_three.get(i).equalsIgnoreCase("A/Night")) {
						all_data.append("  ").append(dates.get(i))
								.append(" - " + check_three.get(i)).append(" \r\n");
					} else {
						x++;
					}
					if (check_four.get(i).equalsIgnoreCase("S/O")) {
						all_data.append("  ").append(dates.get(i))
								.append(" - " + check_four.get(i)).append(" \r\n");
					} else {
						x++;
					}
					if (check_five.get(i).equalsIgnoreCase("N/A")) {
						all_data.append("  ").append(dates.get(i))
								.append(" - " + check_five.get(i)).append(" \r\n");
					} else {
						x++;
					}
					if (x == 5) {

						CallDialogue(dates.get(i));
						flagcheck = 0;
						break;
					}
					flagcheck++;
				}

				Log.e("SelectAvailability", "Flag Checks : " + flagcheck);

				if (flagcheck == 14) {

					Intent ei = new Intent(Intent.ACTION_SEND_MULTIPLE);
					ei.setType("plain/text");
					ei.putExtra(Intent.EXTRA_EMAIL, new String[] { "availability@oncall.com.au" });
					ei.putExtra(Intent.EXTRA_SUBJECT, "Quick Submission - " + user_clid);
					// ei.putExtra(Intent.EXTRA_STREAM, email_images.get(0));
					ei.putExtra(
							Intent.EXTRA_TEXT,
							"Dear Availibility Team, \r\n  \t\t Please find attached the submission of my current availibility  \r\n "
									+ user_fname
									+ " "
									+ user_lname
									+ " - "
									+ user_clid
									+ "  \r\n"
									+ all_data.toString());

					actStopped = false;
					startActivityForResult(Intent.createChooser(ei, "Sending multiple attachment"),
							12345);

					System.out.println("" + all_data.toString());
				}

			} catch (Exception e) {
				// TODO: handle exception
				Log.e("SelectActivity", "CAtch Errror : " + e.toString());
				e.printStackTrace();
			}

		} else if (id == R.id.btn_backavail) {
			TabPage.tabHost.setCurrentTab(1);
		} else if (id == R.id.btnclearall) {

			check_onee.clear();
			chhck_twoo.clear();
			check_three.clear();
			check_four.clear();
			check_five.clear();
			openation = "";
			GetNextDates getDatess = new GetNextDates(getApplicationContext());
			getDatess.execute("");

			// openation="clearr";
			System.out.println("again call");
		} else if (id == R.id.btnapplyfav) {

			openation = "apply_fav";
			GetNextDates getDatess = new GetNextDates(getApplicationContext());
			getDatess.execute("");

		} else if (id == R.id.btnsavefav) {
			fav_check_onee.clear();
			fav_chhck_twoo.clear();
			fav_check_three.clear();
			fav_check_four.clear();
			fav_check_five.clear();

			fav_check_onee.addAll(check_onee);
			fav_chhck_twoo.addAll(chhck_twoo);
			fav_check_three.addAll(check_three);
			fav_check_four.addAll(check_four);
			fav_check_five.addAll(check_five);

			/*
			 * check_onee=null; chhck_twoo=null; check_three=null;
			 * check_four=null; check_five=null;
			 */
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// preventing default implementation previous to
			// android.os.Build.VERSION_CODES.ECLAIR
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	public static void RefreshUi(int poss, int top) {
		adapter.notifyDataSetChanged();
		listscd.setSelectionFromTop(poss, top);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();

		actStopped = true;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		actStopped = false;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == 12345 && actStopped) {

			// Toast.makeText(this, "Mail sent.", Toast.LENGTH_SHORT).show();
			// alert();

			check_onee.clear();
			chhck_twoo.clear();
			check_three.clear();
			check_four.clear();
			check_five.clear();
			openation = "";
			GetNextDates getDatess = new GetNextDates(getApplicationContext());
			getDatess.execute("");

		}
	}

	public void alert() {

		// TODO Auto-generated method stub
		AlertDialog.Builder alertadd = new AlertDialog.Builder(this);
		// alertadd.setTitle("Android";

		LayoutInflater factory = LayoutInflater.from(this);
		final View view = factory.inflate(R.layout.alert, null, false);
		alertadd.setView(view);
		// ImageView image= (ImageView) view.findViewById(R.id.img_dialog_logo);
		TextView text = (TextView) view.findViewById(R.id.txt_dialog_1);
		text.setText("Submitted");
		TextView text_msg = (TextView) view.findViewById(R.id.text_dialog_msg);
		text_msg.setText("Thanks you for submitting your availability.You will receive a confirmation email shortly");

		// TextView text2= (TextView) view.findViewById(R.id.txt_dialog_2);
		// text.setText("Unlock all features of the App and remove Ads forever";

		alertadd.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		alertadd.show();

	}

	/*
	 * @Override public void onCheckedChanged(CompoundButton buttonView, boolean
	 * isChecked) { // TODO Auto-generated method stub
	 * 
	 * int pos = listscd.getPositionForView(buttonView);
	 * System.out.println("Pos ["+pos+"]"); if (pos !=
	 * ListView.INVALID_POSITION) {
	 * 
	 * System.out.println("=================="); // Planet p =
	 * planetsList.get(pos); //p.setChecked(isChecked); }
	 * 
	 * }
	 */

	private void CallDialogue(String here) {
		final Dialog Alert_dialogg;
		Alert_dialogg = new Dialog(SelectAvailibilty.this);
		Alert_dialogg.requestWindowFeature(Window.FEATURE_NO_TITLE);
		Alert_dialogg.setContentView(R.layout.dialog_error);
		Alert_dialogg.setCancelable(false);

		TextView txt_msg = (TextView) Alert_dialogg.findViewById(R.id.shshs);
		Button btn_okk = (Button) Alert_dialogg.findViewById(R.id.btn_ok);
		TextView txt_errr = (TextView) Alert_dialogg.findViewById(R.id.txt_errr);

		txt_msg.setText("No all fields are filled in");
		txt_errr.setText("" + here);

		Alert_dialogg.show();
		btn_okk.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Alert_dialogg.dismiss();

			}
		});
	}

	public static AvailibQAdapter getAdapter() {
		return adapter;
	}
}
