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
import com.aeon.constants.AvailabilityDateQ;
import com.aeon.myoncall.SelectAvailibilty;

public class GetNextDates extends AsyncTask<String, Integer, String> {

	public static ArrayList<AvailabilityDateQ> arrayy = new ArrayList<AvailabilityDateQ>();

	private StringBuffer readbuffer = new StringBuffer();
	Context context;

	public GetNextDates(Context context2) {
		// TODO Auto-generated constructor stub
		this.context = context2;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		SelectAvailibilty.check_onee.clear();
		SelectAvailibilty.chhck_twoo.clear();
		SelectAvailibilty.check_three.clear();
		SelectAvailibilty.check_four.clear();
		SelectAvailibilty.check_five.clear();
		
		if (arrayy != null)
			arrayy.clear();
		
		SelectAvailibilty.dates.clear();

		if (SelectAvailibilty.openation.equalsIgnoreCase("apply_fav")) {

		} else {
			SelectAvailibilty.fav_check_onee.add("");
			SelectAvailibilty.fav_chhck_twoo.add("");
			SelectAvailibilty.fav_check_three.add("");
			SelectAvailibilty.fav_check_four.add("");
			SelectAvailibilty.fav_check_five.add("");
		}
	}

	@Override
	protected String doInBackground(String... inputObj) {
		try {

			for (int i = 0; i < 14; i++) {

				AvailabilityDateQ ard = new AvailabilityDateQ();
				Calendar calendar = Calendar.getInstance();
				Date today = calendar.getTime();

				calendar.add(Calendar.DAY_OF_YEAR, i);
				Date tomorrow = calendar.getTime();

				DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

				String todayAsString = dateFormat.format(tomorrow);
				ard.setDate_to(todayAsString);
				arrayy.add(ard);
				String datess = ard.toString();
				System.out.println("-->>" + todayAsString);
				SelectAvailibilty.dates.add(i, todayAsString);

				SelectAvailibilty.check_onee.add("");
				SelectAvailibilty.chhck_twoo.add("");
				SelectAvailibilty.check_three.add("");
				SelectAvailibilty.check_four.add("");
				SelectAvailibilty.check_five.add("");

				if (SelectAvailibilty.openation.equalsIgnoreCase("apply_fav")) {

				} else {
					SelectAvailibilty.fav_check_onee.add("");
					SelectAvailibilty.fav_chhck_twoo.add("");
					SelectAvailibilty.fav_check_three.add("");
					SelectAvailibilty.fav_check_four.add("");
					SelectAvailibilty.fav_check_five.add("");
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
			SelectAvailibilty.getAdapter().notifyDataSetChanged();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Toast.makeText(context, "No Places Returns", Toast.LENGTH_SHORT).show();
		}
	}
}