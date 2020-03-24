package com.aeon.myoncall;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TabMyShifts extends BaseActivity implements OnClickListener {

	Button btn_infomyshr,btn_myshiftss;
	RelativeLayout layouu_bg;
	

	SharedPreferences sh_Pref;
	Editor toEdit;
	String user_clid,user_fname,user_lname,user_emailss;
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		layouu_bg.getBackground().setAlpha(80);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		setScreenName("MyShirts Screen");
		//disableTrackReport();
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_myshifts);
		
		btn_infomyshr=(Button)findViewById(R.id.btn_infomyshr);
		btn_myshiftss=(Button)findViewById(R.id.btn_myshifts);
		layouu_bg=(RelativeLayout)findViewById(R.id.layouu_bg);
		btn_infomyshr.setOnClickListener(this);
		btn_myshiftss.setOnClickListener(this);
		layouu_bg.getBackground().setAlpha(80);
		
		sh_Pref=getSharedPreferences("register_details", MODE_PRIVATE);
		toEdit = sh_Pref.edit();
		
		 user_clid= sh_Pref.getString("on_call_id", null);
		 user_fname = sh_Pref.getString("first_name", null);
		 user_lname = sh_Pref.getString("last_name", null);
		 user_emailss = sh_Pref.getString("email_id", null);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id=v.getId();
		if(id==R.id.btn_infomyshr){
			CallDialogue("");
		}else if(id==R.id.btn_myshifts){
			

			Intent ei = new Intent(Intent.ACTION_SEND_MULTIPLE);
            ei.setType("plain/text");
            ei.putExtra(Intent.EXTRA_EMAIL, new String[] {"myshifts@oncall.com.au"});
            ei.putExtra(Intent.EXTRA_SUBJECT, "Shift Request - "+user_clid);
            //ei.putExtra(Intent.EXTRA_STREAM, email_images.get(0));
            ei.putExtra(Intent.EXTRA_TEXT, "Dear Myshifts Team, \r\n  \t\t I would like to request for my current"
            		+" confirmation of booked shifts I have with ONCALL.  \r\n  \r\n "+user_fname+" "+user_lname+" - "+user_clid);
            	
            startActivityForResult(Intent.createChooser(ei, "Sending multiple attachment"), 12345);
			
		}
	} 
	
	
	private void CallDialogue(String here){
		 final Dialog Alert_dialogg;
		Alert_dialogg = new Dialog(TabMyShifts.this);
		Alert_dialogg.requestWindowFeature(Window.FEATURE_NO_TITLE);
		Alert_dialogg.setContentView(R.layout.dialog_error);
		Alert_dialogg.setCancelable(false);
		  
		TextView txt_msg=(TextView)Alert_dialogg.findViewById(R.id.shshs);
		Button btn_okk=(Button)Alert_dialogg.findViewById(R.id.btn_ok);
		TextView txt_errr=(TextView)Alert_dialogg.findViewById(R.id.txt_errr);

		txt_msg.setText("MyShifts allows you to send a request \r\n for all your shifts currently booked and \r\n confirmed. " +
		 		"The response will come \r\n from myshifts@oncall.com.au. This \r\n service is operational from 8am-10am. \r\n" +
		 		"Sending a MyShifts request outside \r\n these times will be actioned the \r\n following day.");
		txt_errr.setText("My Shifts");

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
