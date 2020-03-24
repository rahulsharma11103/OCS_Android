package com.aeon.adapters;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.aeon.adapters.AvailibQAdapter.ViewHolderr;
import com.aeon.constants.AvailabilityDateQ;
import com.aeon.myoncall.R;
import com.aeon.myoncall.SelectAdvAvailb;
import com.aeon.myoncall.SelectAvailibilty;
import com.aeon.myoncall.SelectTimeClass;
import com.aeon.myoncall.TabMore;

	public class AvailAdvAdapter extends BaseAdapter {


		ArrayList<AvailabilityDateQ> _data=new ArrayList<AvailabilityDateQ>();
		Context _c;
		public static String select_refresh2="";
		public AvailAdvAdapter(ArrayList<AvailabilityDateQ> data,Context context)
		{
			_data=data;
			_c=context;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return _data.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return _data.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		
	   
	    

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			
			ViewHolder viewHolder = null;
			//convertView=null;
			View v = convertView;

				LayoutInflater vi = (LayoutInflater) _c
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.itm_adv_availb, null);
			
			  viewHolder = new ViewHolder();
				RelativeLayout lay_l1=(RelativeLayout)v.findViewById(R.id.lay_adl1);
				RelativeLayout lay_l2=(RelativeLayout)v.findViewById(R.id.lay_adl2);
				RelativeLayout lay_l3=(RelativeLayout)v.findViewById(R.id.lay_adl3);
				RelativeLayout lay_l4=(RelativeLayout)v.findViewById(R.id.lay_adl4);
				RelativeLayout lay_l5=(RelativeLayout)v.findViewById(R.id.lay_adl5);

				int remain_one=0;
				int remm2=0;
				 if(SelectAdvAvailb.lay_width>500){
					 lay_l1.getLayoutParams().width=175;
					 lay_l2.getLayoutParams().width=175;
						remain_one=SelectAdvAvailb.lay_width - 350;
						remm2=remain_one/3;
						lay_l3.getLayoutParams().width=remm2;
						lay_l4.getLayoutParams().width=remm2;
						lay_l5.getLayoutParams().width=remm2;

					}else if(SelectAdvAvailb.lay_width<=500){
						lay_l1.getLayoutParams().width=150;
						lay_l2.getLayoutParams().width=150;
						remain_one=SelectAdvAvailb.lay_width - 300;
						remm2=remain_one/3;
						lay_l3.getLayoutParams().width=remm2;
						lay_l4.getLayoutParams().width=remm2;
						lay_l5.getLayoutParams().width=remm2;
					}
				
				 viewHolder.text_date = (TextView) v.findViewById(R.id.adtxtV1);	
				 viewHolder.text_time = (TextView) v.findViewById(R.id.adtxtV2);	
				 viewHolder.adchq_one = (CheckBox) v.findViewById(R.id.adcheckBox1);	
				 viewHolder.adchq_two = (CheckBox) v.findViewById(R.id.adcheckBox2);
				 viewHolder.adchq_three = (CheckBox) v.findViewById(R.id.adcheckBox3);
				 viewHolder.text_time.setSelected(true);
				 
				 
				 
	        	// select client please
				AvailabilityDateQ msg = _data.get(position);
				
				viewHolder.text_date.setText(msg.getDate_to());

				if(SelectAdvAvailb.openation2.equalsIgnoreCase("apply_fav")){

					if(!SelectAdvAvailb.fav_adv_time.get(position).equalsIgnoreCase("")){
						
						SelectAdvAvailb.adv_dates.set(position, SelectAdvAvailb.fav_adv_check_onee.get(position));
					}
					if(SelectAdvAvailb.fav_adv_check_onee.get(position).equalsIgnoreCase("A/Night")){
						viewHolder.adchq_one.setChecked(true);
						SelectAdvAvailb.adv_check_onee.set(position, "A/Night");
					}
					if(SelectAdvAvailb.fav_adv_chhck_twoo.get(position).equalsIgnoreCase("S/O")){
						viewHolder.adchq_two.setChecked(true);
						SelectAdvAvailb.adv_chhck_twoo.set(position, "S/O");
					}
					if(SelectAdvAvailb.fav_adv_check_three.get(position).equalsIgnoreCase("N/A")){
						viewHolder.adchq_three.setChecked(true);
						SelectAdvAvailb.adv_check_three.set(position, "N/A");
					}
					
				}
				
				if(select_refresh2.equalsIgnoreCase("refresh")){

					try {

						    	System.out.println("i call");
						    	if(!SelectAdvAvailb.adv_time.get(position).equalsIgnoreCase("")){
						    		viewHolder.text_time.setText(SelectAdvAvailb.adv_time.get(position));
						    		SelectAdvAvailb.adv_time.set(position, SelectAdvAvailb.adv_time.get(position));
								}else{
									//ViewHolderr.chq_four.setChecked(false);
									SelectAdvAvailb.adv_time.set(position, "SELECT");

								}
								if(SelectAdvAvailb.adv_check_onee.get(position).equalsIgnoreCase("A/Night")){
									viewHolder.adchq_one.setChecked(true);
									SelectAdvAvailb.adv_check_onee.set(position, "A/Night");
								}else{
									viewHolder.adchq_one.setChecked(false);
									
								}
								if(SelectAdvAvailb.adv_chhck_twoo.get(position).equalsIgnoreCase("S/O")){
									viewHolder.adchq_two.setChecked(true);
									SelectAdvAvailb.adv_chhck_twoo.set(position, "S/O");
								}else{
									viewHolder.adchq_two.setChecked(false);
								}
								if(SelectAdvAvailb.adv_check_three.get(position).equalsIgnoreCase("N/A")){
									viewHolder.adchq_three.setChecked(true);
									SelectAdvAvailb.adv_check_three.set(position, "N/A");
								}else{
									viewHolder.adchq_three.setChecked(false);

								}
								
								
		
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}

				viewHolder.adchq_one.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						// TODO Auto-generated method stub
					//	Object tag=viewHolder.adchq_one.getTag();
				        if ( isChecked ) 
				        { 
				            System.out.println("cehck 0ne--"+position);
				            SelectAdvAvailb.adv_check_onee.set(position, "A/Night");
				        } 
				        else
				        {
				        	System.out.println("cehck remove 0ne--");
				            Log.d("Checkbox","removed "+position);
				            SelectAdvAvailb.adv_check_onee.set(position, "");
				        }
					}
				 });
				
				viewHolder.adchq_two.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						// TODO Auto-generated method stub
					//	Object tag=viewHolder.adchq_two.getTag();
				        if ( isChecked ) 
				        { 
				            System.out.println("cehck 0ne--"+position);
				            SelectAdvAvailb.adv_chhck_twoo.set(position, "S/O");
				        } 
				        else
				        {
				        	System.out.println("cehck remove 0ne--");
				            Log.d("Checkbox","removed "+position);
				            SelectAdvAvailb.adv_chhck_twoo.set(position, "");
				        }
					}
				 });
				
				viewHolder.adchq_three.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						// TODO Auto-generated method stub
					//	Object tag=viewHolder.adchq_three.getTag();
				        if ( isChecked ) 
				        { 
				            System.out.println("cehck 0ne--"+position);
				            SelectAdvAvailb.adv_check_three.set(position, "N/A");
				            SelectAdvAvailb.adv_check_onee.set(position, "");
				            SelectAdvAvailb.adv_chhck_twoo.set(position, "");
				            SelectAdvAvailb.adv_time.set(position, "-");
				            select_refresh2="refresh";
				            int index = SelectAdvAvailb.listadv.getFirstVisiblePosition();
				            View v = SelectAdvAvailb.listadv.getChildAt(0);
				            int top = (v == null) ? 0 : v.getTop();
				            SelectAdvAvailb.RefreshUi(index,top);
				            
				        } 
				        else
				        {
				        	System.out.println("cehck remove 0ne--");
				            Log.d("Checkbox","removed "+position);
				            SelectAdvAvailb.adv_check_three.set(position, "");
				            SelectAdvAvailb.adv_time.set(position, "SELECT");
				            select_refresh2="refresh";
				            int index = SelectAdvAvailb.listadv.getFirstVisiblePosition();
				            View v = SelectAdvAvailb.listadv.getChildAt(0);
				            int top = (v == null) ? 0 : v.getTop();
				            SelectAdvAvailb.RefreshUi(index,top);
				        }
					}
				 });
				viewHolder.text_time.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						select_refresh2="refresh";
			            int indexss = SelectAdvAvailb.listadv.getFirstVisiblePosition();
			            View v33 = SelectAdvAvailb.listadv.getChildAt(0);
			            int top = (v33 == null) ? 0 : v33.getTop();
			            SelectAdvAvailb.UpdateText(indexss,top,position);
						
					}
				});
				
			
				
			//	viewHolder = (ViewHolder) convertView.getTag();
				v.setTag(viewHolder);
			

/*			ViewHolder.text_date.setTag(position);
			ViewHolder.text_time.setTag(position);
			ViewHolder.adchq_one.setTag(position);
			ViewHolder.adchq_two.setTag(position);
			ViewHolder.adchq_three.setTag(position);
			v.setTag(viewHolder);*/
			
				return v;
		}
		 static class ViewHolder {
		    	
		         TextView text_date;
		         TextView text_time;
		         CheckBox adchq_one;
		         CheckBox adchq_two;
		         CheckBox adchq_three;
		        
		    }

	}
