package com.aeon.myoncall;

import java.util.ArrayList;

import com.aeon.adapters.AvailAdvAdapter;
import com.aeon.adapters.AvailibQAdapter;
import com.aeon.asynctask.GetAdvancedDates;
import com.aeon.asynctask.GetNextDates;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SelectAdvAvailb extends BaseActivity implements OnClickListener{

	Button btnadvsavefav,btnadvapplyfav,btnadvclearall,btn_advbackavail,btn_advsubbmt;
	RelativeLayout adtt_one,adtt_two,adtt_three,adtt_four,adtt_five;
	public static int lay_width=0;
	static Context coontext;
	public static ArrayList<String> adv_dates=new ArrayList<String>();
	public static ArrayList<String> adv_time=new ArrayList<String>();
	public static ArrayList<String> adv_check_onee=new ArrayList<String>();
	public static ArrayList<String> adv_chhck_twoo=new ArrayList<String>();
	public static ArrayList<String> adv_check_three=new ArrayList<String>();

	public static ArrayList<String> fav_adv_time=new ArrayList<String>();
	public static ArrayList<String> fav_adv_check_onee=new ArrayList<String>();
	public static ArrayList<String> fav_adv_chhck_twoo=new ArrayList<String>();
	public static ArrayList<String> fav_adv_check_three=new ArrayList<String>();
	
	public static String openation2="";
	public static ListView listadv;
	
	
	static AvailAdvAdapter adapter;
	
	SharedPreferences sh_Pref;
	Editor toEdit;
	String user_clid,user_fname,user_lname,user_emailss;
	
	boolean actStopped ;
	
	
	//http://www.lalit3686.blogspot.in/2012/06/today-i-am-going-to-show-how-to-deal.html
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		setScreenName("AdvancedAvailibility Screen");
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_adv_avail);
		
		init();
		
		coontext=this;
		Resources resources = getResources();
		 Configuration config = resources.getConfiguration();
		 DisplayMetrics dm = resources.getDisplayMetrics();

		   double screenWidthInPixels = (double)config.screenWidthDp * dm.density;	
		   lay_width= (int)(screenWidthInPixels + .5);
		
		   int remain_one=0;
		   int remm2=0;
		   if(lay_width>500){
			   
			   adtt_one.getLayoutParams().width=175;
			   adtt_two.getLayoutParams().width=175;
				remain_one=lay_width - 350;
				remm2=remain_one/3;
				adtt_three.getLayoutParams().width=remm2;
				adtt_four.getLayoutParams().width=remm2;
				adtt_five.getLayoutParams().width=remm2;

			}else if(lay_width<=500){
				
				adtt_one.getLayoutParams().width=150;
				adtt_two.getLayoutParams().width=150;
				remain_one=lay_width - 300;
				remm2=remain_one/3;
				adtt_three.getLayoutParams().width=remm2;
				adtt_four.getLayoutParams().width=remm2;
				adtt_five.getLayoutParams().width=remm2;
			}
		   
		   int xxx=(lay_width/3)-6;
		   btnadvsavefav.getLayoutParams().width=xxx;
		   btnadvapplyfav.getLayoutParams().width=xxx;
		   btnadvclearall.getLayoutParams().width=xxx;
		   
		   adapter = new AvailAdvAdapter(GetAdvancedDates.arrayy2, this);
		   adv_dates.clear();
		   adv_time.clear();
		   adv_check_onee.clear();
		   adv_chhck_twoo.clear();
		   adv_check_three.clear();
		   
		   sh_Pref=getSharedPreferences("register_details", MODE_PRIVATE);
			toEdit = sh_Pref.edit();
			
			 user_clid= sh_Pref.getString("on_call_id", null);
			 user_fname = sh_Pref.getString("first_name", null);
			 user_lname = sh_Pref.getString("last_name", null);
			 user_emailss = sh_Pref.getString("email_id", null);
		   
		   
		   GetAdvancedDates getAdvdDates= new GetAdvancedDates(getApplicationContext());
		   getAdvdDates.execute("");
		   
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		actStopped = false;
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
		actStopped = true;
	}

	private void init() {
		// TODO Auto-generated method stub
		btnadvsavefav=(Button)findViewById(R.id.btnadvsavefav);
		btnadvapplyfav=(Button)findViewById(R.id.btnadvapplyfav);
		btnadvclearall=(Button)findViewById(R.id.btnadvclearall);
		btn_advbackavail=(Button)findViewById(R.id.btn_advbackavail);
		btn_advsubbmt=(Button)findViewById(R.id.btn_advsubbmt);
		listadv=(ListView)findViewById(R.id.listadv);
		btnadvsavefav.setOnClickListener(this);
		btnadvapplyfav.setOnClickListener(this);
		btnadvclearall.setOnClickListener(this);
		btn_advbackavail.setOnClickListener(this);
		btn_advsubbmt.setOnClickListener(this);
		adtt_one=(RelativeLayout)findViewById(R.id.adtt_one);
		adtt_two=(RelativeLayout)findViewById(R.id.adtt_two);
		adtt_three=(RelativeLayout)findViewById(R.id.adtt_three);
		adtt_four=(RelativeLayout)findViewById(R.id.adtt_four);
		adtt_five=(RelativeLayout)findViewById(R.id.adtt_five);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id=v.getId();
		if(id==R.id.btnadvsavefav){
			
			
			fav_adv_time.clear();
			fav_adv_check_onee.clear();
			fav_adv_chhck_twoo.clear();
			fav_adv_check_three.clear();
			
			
			fav_adv_time.addAll(adv_time);
			fav_adv_check_onee.addAll(adv_check_onee);
			fav_adv_chhck_twoo.addAll(adv_chhck_twoo);
			fav_adv_check_three.addAll(adv_check_three);
			
		}else if(id==R.id.btnadvapplyfav){
			
				openation2="apply_fav";

			   GetAdvancedDates getAdvdDates= new GetAdvancedDates(getApplicationContext());
			   getAdvdDates.execute("");
			
		}else if(id==R.id.btnadvclearall){
			 	adv_dates.clear();
			    adv_time.clear();
			    adv_check_onee.clear();
			    adv_chhck_twoo.clear();
			    adv_check_three.clear();
			    openation2="";
			   // listadv.setAdapter(null);
			    GetAdvancedDates getAdvdDates= new GetAdvancedDates(getApplicationContext());
				   getAdvdDates.execute("");
		}else if(id==R.id.btn_advbackavail){
			TabPage.tabHost.setCurrentTab(1);
		}else if(id==R.id.btn_advsubbmt){
			
			StringBuilder all_data = new StringBuilder();
			for (int i = 0; i < adv_dates.size(); i++) {
				int flagcheck=0,x=0;
				if(!adv_time.get(i).equalsIgnoreCase("SELECT")){
					all_data.append("  ").append(adv_dates.get(i)).append(" - "+adv_time.get(i)).append(" \r\n");
				}else{
					x++;
				}
				if(adv_check_onee.get(i).equalsIgnoreCase("A/Night")){
					all_data.append("  ").append(adv_dates.get(i)).append(" - "+adv_check_onee.get(i)).append(" \r\n");
				}else{
					x++;
				}
				if(adv_chhck_twoo.get(i).equalsIgnoreCase("S/O")){
					all_data.append("  ").append(adv_dates.get(i)).append(" - "+adv_chhck_twoo.get(i)).append(" \r\n");
				}else{
					x++;
				}
				if(adv_check_three.get(i).equalsIgnoreCase("N/A")){
					all_data.append("  ").append(adv_dates.get(i)).append(" - "+adv_check_three.get(i)).append(" \r\n");
				}else{
					x++;
				}	
			}
			
			Intent ei = new Intent(Intent.ACTION_SEND_MULTIPLE);
            ei.setType("plain/text");
            ei.putExtra(Intent.EXTRA_EMAIL, new String[] {"availability@oncall.com.au"});
            ei.putExtra(Intent.EXTRA_SUBJECT, "Advanced Submission - "+user_clid);
            //ei.putExtra(Intent.EXTRA_STREAM, email_images.get(0));
            ei.putExtra(Intent.EXTRA_TEXT, "Dear Availibility Team, \r\n  \t\t Please find attached the submission of my current availibility  \r\n "+user_fname+" "+user_lname+" - "+user_clid+"  \r\n"+all_data.toString());

            actStopped = false;
            startActivityForResult(Intent.createChooser(ei, "Sending multiple attachment"), 12345);

		}
	}
	
	
	
	
	private void CallDialogue(String here){
		 final Dialog Alert_dialogg;
		Alert_dialogg = new Dialog(SelectAdvAvailb.this);
		Alert_dialogg.requestWindowFeature(Window.FEATURE_NO_TITLE);
		Alert_dialogg.setContentView(R.layout.dialog_error);
		Alert_dialogg.setCancelable(false);
		  
		TextView txt_msg=(TextView)Alert_dialogg.findViewById(R.id.shshs);
		Button btn_okk=(Button)Alert_dialogg.findViewById(R.id.btn_ok);
		TextView txt_errr=(TextView)Alert_dialogg.findViewById(R.id.txt_errr);

		txt_msg.setText("No all fields are filled in");
		txt_errr.setText(""+here);

		Alert_dialogg.show();
		btn_okk.setOnClickListener(new View.OnClickListener() {
			
			  	@Override
				public void onClick(View v) {
			  		// TODO Auto-generated method stub
			  		Alert_dialogg.dismiss();
				
				}
		 });
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
			if(requestCode==12345 && actStopped)    
	        {
	          
				 //Toast.makeText(this, "Mail sent.", Toast.LENGTH_SHORT).show();
				//alert();
			
			    
				adv_dates.clear();
			    adv_time.clear();
			    adv_check_onee.clear();
			    adv_chhck_twoo.clear();
			    adv_check_three.clear();
			    openation2="";
			   // listadv.setAdapter(null);
			    GetAdvancedDates getAdvdDates= new GetAdvancedDates(getApplicationContext());
				   getAdvdDates.execute("");
				
				/*if(resultCode==Activity.RESULT_CANCELED)
			        {
			            Toast.makeText(this, "Mail canceled.", Toast.LENGTH_SHORT).show();
	        }*/
	        }
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	     if (keyCode == KeyEvent.KEYCODE_BACK) {
	     //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
	     return true;
	     }
	     return super.onKeyDown(keyCode, event);    
	}

	public static void RefreshUi(int index, int top) {
		// TODO Auto-generated method stub

		adapter.notifyDataSetChanged();
		listadv.setAdapter(adapter);
		listadv.setSelectionFromTop(index, top);
	}

	public void alert(){

        // TODO Auto-generated method stub
        AlertDialog.Builder alertadd = new AlertDialog.Builder(
                this);
        //alertadd.setTitle("Android";

        LayoutInflater factory = LayoutInflater.from(this);
        final View view = factory.inflate(R.layout.alert, null, false);
        alertadd.setView(view);
      //  ImageView image= (ImageView) view.findViewById(R.id.img_dialog_logo);
        TextView text= (TextView) view.findViewById(R.id.txt_dialog_1);
        text.setText("Submitted");
        TextView text_msg= (TextView) view.findViewById(R.id.text_dialog_msg);
        text_msg.setText("Thanks you for submitting your availability.You will receive a confirmation email shortly") ;
         
//        TextView text2= (TextView) view.findViewById(R.id.txt_dialog_2);
//        text.setText("Unlock all features of the App and remove Ads forever";

      
        
        
       
       
        alertadd.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertadd.show();


    }

	
	
	public static void UpdateText(int indexss, int top,int pos) {
		// TODO Auto-generated method stub
		//Toast.makeText(coontext, "helosss", Toast.LENGTH_SHORT).show();
		
		final int posii=pos,indexxx=indexss,topp=top;
		final Dialog Alert_dialogg;
		Alert_dialogg = new Dialog(coontext);
		Alert_dialogg.requestWindowFeature(Window.FEATURE_NO_TITLE);
		Alert_dialogg.setContentView(R.layout.dialog_selecttime);
		Alert_dialogg.setCancelable(false);
		 final String timee;
		 String heree="";
		 
		 final EditText edt_hr=(EditText)Alert_dialogg.findViewById(R.id.edt_hr);
		 final EditText edt_min=(EditText)Alert_dialogg.findViewById(R.id.edt_min);
		 final EditText edt_hr2=(EditText)Alert_dialogg.findViewById(R.id.edt_hr2);
		 final EditText edt_min2=(EditText)Alert_dialogg.findViewById(R.id.edt_min2);
		 final Spinner strspin_ampm2=(Spinner)Alert_dialogg.findViewById(R.id.strspin_ampm2);
		 final Spinner strspin_ampm=(Spinner)Alert_dialogg.findViewById(R.id.strspin_ampm);
		 Button btn_okk=(Button)Alert_dialogg.findViewById(R.id.btn_adokk);

		 Alert_dialogg.show();
		 

		 
		 btn_okk.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String str_hr=edt_hr.getText().toString();
				String str_mn=edt_min.getText().toString();
				String stp_hr=edt_hr2.getText().toString();
				String stp_mn=edt_min2.getText().toString();
				String ampm1=strspin_ampm.getSelectedItem().toString();
				String ampm2=strspin_ampm2.getSelectedItem().toString();
				String start_tm=edt_hr.getText().toString()+":"+edt_min.getText().toString();
				String end_tm=edt_hr2.getText().toString()+":"+edt_min2.getText().toString();
				
				
				
				int sttt=Integer.parseInt(str_hr);
				int smtt=Integer.parseInt(str_mn);
				int ettt=Integer.parseInt(stp_hr);
				int emtt=Integer.parseInt(stp_mn);
				
				/*if(sttt>12  &&  smtt>60 && ettt>12  &&  emtt>60){
					//Toast.makeText(coontext, "Invalid input ", Toast.LENGTH_SHORT).show();
				}else{
					
					// operation
					
				}*/
				Alert_dialogg.dismiss();
				String time =""+start_tm+" "+ampm1+" - "+end_tm+" "+ampm2;
				
				System.out.println(""+start_tm+" "+ampm1+" - "+end_tm+" "+ampm2 );
				
				
				SelectAdvAvailb.adv_time.set(posii, time);
				
				adapter.notifyDataSetChanged();
				listadv.setAdapter(adapter);
				listadv.setSelectionFromTop(indexxx, topp);
				
				
				//timee=stp_tm+"-"+stp_tm;
			//heree=timee;
			}
		});
	}
	

	
}
