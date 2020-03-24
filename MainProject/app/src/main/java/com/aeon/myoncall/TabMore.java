package com.aeon.myoncall;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TabMore extends BaseActivity implements OnClickListener{

	Button btn_unregs;
	RelativeLayout lay_onclhome,lay_hotjobs,lay_stafplicy,lay_trainginf,lay_eventinff,lay_facebook,
				   lay_linkedin,lay_udtpersdt,lay_trmcond;
	SharedPreferences sh_Pref;
	Editor toEdit;
	public static String webvw_flag="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setScreenName("More Screen");
		//disableTrackReport();
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_more);
		
		sh_Pref = getSharedPreferences("register_details", MODE_PRIVATE);
		toEdit = sh_Pref.edit();
		init();
		
	}

	private void init() {
		// TODO Auto-generated method stub
		btn_unregs=(Button)findViewById(R.id.btn_unregs);
		btn_unregs.setOnClickListener(this);
		
		lay_onclhome=(RelativeLayout)findViewById(R.id.lay_onclhome);
		lay_hotjobs=(RelativeLayout)findViewById(R.id.lay_hotjobs);
		lay_stafplicy=(RelativeLayout)findViewById(R.id.lay_stafplicy);
		lay_trainginf=(RelativeLayout)findViewById(R.id.lay_trainginf);
		lay_eventinff=(RelativeLayout)findViewById(R.id.lay_eventinff);
		lay_facebook=(RelativeLayout)findViewById(R.id.lay_facebook);
		lay_linkedin=(RelativeLayout)findViewById(R.id.lay_linkedin);
		lay_udtpersdt=(RelativeLayout)findViewById(R.id.lay_udtpersdt);
		lay_trmcond=(RelativeLayout)findViewById(R.id.lay_trmcond);
		lay_onclhome.setOnClickListener(this);
		lay_hotjobs.setOnClickListener(this);
		lay_stafplicy.setOnClickListener(this);
		lay_trainginf.setOnClickListener(this);
		lay_eventinff.setOnClickListener(this);
		lay_facebook.setOnClickListener(this);
		lay_linkedin.setOnClickListener(this);
		lay_udtpersdt.setOnClickListener(this);
		lay_trmcond.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id=v.getId();
		if(id==R.id.btn_unregs){
			
		/*	toEdit.clear();
			toEdit.commit();
			Intent ineew = new Intent(getApplicationContext(),RegisterPage.class);
			startActivity(ineew);
			finish();*/
			CallDialogue("");
			
		}else if(id==R.id.lay_onclhome){
			
			webvw_flag="homepage";
			CallIntetWeb();
			
		}else if(id==R.id.lay_hotjobs){
			webvw_flag="hotjobs";
			CallIntetWeb();
		}else if(id==R.id.lay_stafplicy){
			webvw_flag="staffpolicy";
			CallIntetWeb();
		}else if(id==R.id.lay_trainginf){
			webvw_flag="trtaining";
			CallIntetWeb();
		}else if(id==R.id.lay_eventinff){
			webvw_flag="event";
			CallIntetWeb();
		}else if(id==R.id.lay_facebook){
			webvw_flag="facebook";
			CallIntetWeb();
		}else if(id==R.id.lay_linkedin){
			webvw_flag="linkedin";
			CallIntetWeb();
		}else if(id==R.id.lay_udtpersdt){
			TabPage.update_selection="more";
			TabPage.tabHost.setCurrentTab(6);
		}else if(id==R.id.lay_trmcond){
			Intent i_web33=new Intent(getApplicationContext(),TermsConditions.class);
			startActivity(i_web33);
		}
		
	}
	
	private void CallIntetWeb(){
		Intent i_web=new Intent(getApplicationContext(),WebViewAct.class);
		startActivity(i_web);
	}
	
	private void CallDialogue(String here){
		 final Dialog Alert_dialogg;
		Alert_dialogg = new Dialog(TabMore.this);
		Alert_dialogg.requestWindowFeature(Window.FEATURE_NO_TITLE);
		Alert_dialogg.setContentView(R.layout.doalog_unregste);
		Alert_dialogg.setCancelable(false);
		  
		Button btn_yess=(Button)Alert_dialogg.findViewById(R.id.btn_yess);
		Button btn_cancell=(Button)Alert_dialogg.findViewById(R.id.btn_cancell);



		Alert_dialogg.show();
		btn_yess.setOnClickListener(new View.OnClickListener() {
			
			  	@Override
				public void onClick(View v) {
			  		// TODO Auto-generated method stub
			  		Alert_dialogg.dismiss();
			  		toEdit.clear();
					toEdit.commit();
					//TabPage.bitmap_user_profreal.recycle();
					UserUpdate.saveImage(getApplicationContext(), null, "user_prof", null);
					TabPage.bitmap_user_profreal=null;
					TabPage.bitmap_prof=null;
					Intent ineew = new Intent(getApplicationContext(),RegisterPage.class);
					startActivity(ineew);
					finish();
				}
		 });
		btn_cancell.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Alert_dialogg.dismiss();
			}
		});
	}
}
