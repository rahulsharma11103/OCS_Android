package com.aeon.myoncall;

import android.os.Bundle;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LoginActivity extends BaseActivity implements OnClickListener {

	private Dialog Alert_dialogg;
	TextView txt_login;
	EditText edt_oncallid, edt_pass;
	RelativeLayout lay_setAlp;
	TextView txt_msg, txt_fogtpass;
	Button btn_okk;

	SharedPreferences sh_Pref;
	Editor toEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		setScreenName("Login Screen");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		txt_login = (TextView) findViewById(R.id.txt_login);
		txt_fogtpass = (TextView) findViewById(R.id.txt_fogtpass);
		edt_oncallid = (EditText) findViewById(R.id.edt_oncallid);
		edt_pass = (EditText) findViewById(R.id.edt_pass);
		txt_login.setOnClickListener(this);
		txt_fogtpass.setOnClickListener(this);
		lay_setAlp = (RelativeLayout) findViewById(R.id.lay_setAlp);

		// alert dialog to show invalid credentials
		lay_setAlp.getBackground().setAlpha(80);
		sh_Pref = getSharedPreferences("register_details", MODE_PRIVATE);
		toEdit = sh_Pref.edit();
		String hello = sh_Pref.getString("on_call_id", null);
		if (hello != null) {
			// ntent to open tab activity already registered and login
			String loginflass = sh_Pref.getString("login_flag", null);
			if (loginflass.equalsIgnoreCase("true")) {
				// move to tab activity already login
				Intent i_mainpage = new Intent(getApplicationContext(), TabPage.class);
				startActivity(i_mainpage);
				finish();

			} else if (loginflass.equalsIgnoreCase("false")) {
				// keep user here to login
			}
		} else {
			Intent i_mainpage = new Intent(getApplicationContext(), RegisterPage.class);
			startActivity(i_mainpage);
			finish();
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		int id = v.getId();
		if (id == R.id.txt_login) {
			String ent_id = edt_oncallid.getText().toString();
			String ent_pas = edt_pass.getText().toString();

			if (!ent_id.equalsIgnoreCase("") && !ent_pas.equalsIgnoreCase("")) {

				String usercallid = sh_Pref.getString("on_call_id", null);
				String user_pass = sh_Pref.getString("password", null);
				if (ent_id.equalsIgnoreCase(usercallid) && ent_pas.equalsIgnoreCase(user_pass)) {

					// login to tab class
					toEdit = sh_Pref.edit();
					toEdit.putString("login_flag", "true");
					toEdit.commit();
					Intent inwe = new Intent(getApplicationContext(), TabPage.class);
					startActivity(inwe);
					finish();
				} else {
					CallDialogue("2");
				}
			} else {
				CallDialogue("2");
			}

		} else if (id == R.id.txt_fogtpass) {
			CallDialogueFgtPass("");
		}
	}

	private void CallDialogue(String here) {
		final Dialog Alert_dialogg;
		Alert_dialogg = new Dialog(LoginActivity.this);
		Alert_dialogg.requestWindowFeature(Window.FEATURE_NO_TITLE);
		Alert_dialogg.setContentView(R.layout.dialog_error);
		Alert_dialogg.setCancelable(false);

		TextView txt_msg = (TextView) Alert_dialogg.findViewById(R.id.shshs);
		Button btn_okk = (Button) Alert_dialogg.findViewById(R.id.btn_ok);
		TextView txt_errr = (TextView) Alert_dialogg.findViewById(R.id.txt_errr);

		if (here.equalsIgnoreCase("1")) {

			String usercallid = sh_Pref.getString("password", null);
			txt_errr.setText("Your Password: ");
			txt_msg.setText(usercallid);

		} else if (here.equalsIgnoreCase("2")) {

			txt_msg.setText("Your username and password does not match");
			txt_errr.setText("Error");
		} else if (here.equalsIgnoreCase("3")) {

			txt_msg.setText("Please try again or contact Oncall to \r\n retrieve your Oncall ID number");
			txt_errr.setText("Incorrect Oncall ID");
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

	private void CallDialogueFgtPass(String here) {
		final Dialog Alert_dialogg2;
		Alert_dialogg2 = new Dialog(LoginActivity.this);
		Alert_dialogg2.requestWindowFeature(Window.FEATURE_NO_TITLE);
		Alert_dialogg2.setContentView(R.layout.forget_passlay);
		Alert_dialogg2.setCancelable(false);

		final EditText edtt_msg = (EditText) Alert_dialogg2.findViewById(R.id.edt_pass);
		Button btn_okkss = (Button) Alert_dialogg2.findViewById(R.id.btn_submit);
		Button btn_cancel = (Button) Alert_dialogg2.findViewById(R.id.btn_cancel);

		Alert_dialogg2.show();
		btn_okkss.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Alert_dialogg2.dismiss();
				String usercallid = sh_Pref.getString("on_call_id", null);
				String eddtid = edtt_msg.getText().toString();
				if (eddtid.equalsIgnoreCase(usercallid)) {
					CallDialogue("1");
				} else {
					CallDialogue("3");
				}
			}
		});
		btn_cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Alert_dialogg2.dismiss();
			}
		});
	}
}
