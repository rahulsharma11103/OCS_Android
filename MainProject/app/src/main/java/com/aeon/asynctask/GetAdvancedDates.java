package com.aeon.asynctask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.aeon.adapters.AvailAdvAdapter;
import com.aeon.constants.AvailabilityDateQ;
import com.aeon.myoncall.SelectAdvAvailb;
import com.aeon.myoncall.SelectAvailibilty;

public class GetAdvancedDates extends AsyncTask<String, Integer, String> {

	 public static ArrayList<AvailabilityDateQ> arrayy2=new ArrayList<AvailabilityDateQ>();
	
	 private StringBuffer readbuffer = new StringBuffer();
   Context context;
   public GetAdvancedDates(Context context2) {
   	// TODO Auto-generated constructor stub
   	this.context=context2;
	}
   
	 
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			arrayy2.clear();
			SelectAdvAvailb.adv_dates.clear();
			SelectAdvAvailb.adv_time.clear();
			SelectAdvAvailb.adv_check_onee.clear();
			SelectAdvAvailb.adv_chhck_twoo.clear();
			SelectAdvAvailb.adv_check_three.clear();

			
		}
	@Override
   protected String doInBackground(String... inputObj) {
       try {

       	for (int i = 0; i < 42; i++) {
       		
       		AvailabilityDateQ ard=new AvailabilityDateQ();
       		Calendar calendar = Calendar.getInstance();
	        	Date today = calendar.getTime();
	        	
	        	calendar.add(Calendar.DAY_OF_YEAR, i);
	        	Date tomorrow = calendar.getTime();
	        	
	        	DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

	        	String todayAsString = dateFormat.format(tomorrow);
	        	ard.setDate_to(todayAsString);
	        	arrayy2.add(ard);
	        	SelectAdvAvailb.adv_dates.add(todayAsString);
	        	SelectAdvAvailb.adv_time.add("SELECT");
				SelectAdvAvailb.adv_check_onee.add("");
				SelectAdvAvailb.adv_chhck_twoo.add("");
				SelectAdvAvailb.adv_check_three.add("");
	        	
				
				if(SelectAvailibilty.openation.equalsIgnoreCase("apply_fav")){
	        		
	        	}else{
	        		SelectAdvAvailb.fav_adv_time.add("SELECT");
	        		SelectAdvAvailb.fav_adv_check_onee.add("");
	        		SelectAdvAvailb.fav_adv_chhck_twoo.add("");
	        		SelectAdvAvailb.fav_adv_check_three.add("");	

	        	}
	        	

			}

       } catch (Exception e) {
           Log.d("", e.toString());
       }
       return readbuffer.toString();
   }

   @Override
   protected void onPostExecute(String result) {
		
   	try {

   		SelectAdvAvailb.listadv.setAdapter(new AvailAdvAdapter(arrayy2,context));	
	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Toast.makeText(context, "No Places Returns", Toast.LENGTH_SHORT).show();
		}
   }
}